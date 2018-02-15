/*
 * FreeRails
 * Copyright (C) 2000-2018 The FreeRails Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

/*
 *
 */
package freerails.move.premove;

import freerails.controller.explorer.FlatTrackExplorer;
import freerails.controller.explorer.GraphExplorer;
import freerails.controller.pathfinding.PathNotFoundException;
import freerails.controller.pathfinding.PathOnTrackFinder;
import freerails.move.*;
import freerails.util.ImmutableList;
import freerails.util.Vector2D;
import freerails.model.ActivityIterator;
import freerails.model.world.FullWorldDiffs;
import freerails.model.KEY;
import freerails.model.world.ReadOnlyWorld;
import freerails.model.cargo.CargoBatchBundle;
import freerails.model.game.GameTime;
import freerails.model.player.FreerailsPrincipal;
import freerails.model.station.Station;
import freerails.model.terrain.FullTerrainTile;
import freerails.model.terrain.TileTransition;
import freerails.model.track.NoTrackException;
import freerails.model.track.TrackPiece;
import freerails.model.track.TrackSection;
import freerails.model.train.*;
import freerails.model.train.motion.CompositeMotion;
import freerails.model.train.motion.ConstantAccelerationMotion;
import freerails.model.train.motion.Motion;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generates moves for changes in train position and stops at stations.
 */
public class MoveTrainPreMove implements PreMove {

    private static final long serialVersionUID = 3545516188269491250L;
    private static final Logger logger = Logger.getLogger(MoveTrainPreMove.class.getName());
    // TODO Performance cache must be cleared if track on map is build ! make a change listener!
    private static final Map<Integer, HashMap<Integer, TileTransition>> pathCache = new HashMap<>();
    private static int cacheCleared = 0;
    private static int cacheHit = 0;
    private static int cacheMiss = 0;
    private final FreerailsPrincipal principal;
    private final int trainID;
    private final OccupiedTracks occupiedTracks;

    /**
     * @param id
     * @param p
     * @param occupiedTracks
     */
    public MoveTrainPreMove(int id, FreerailsPrincipal p, OccupiedTracks occupiedTracks) {
        trainID = id;
        principal = p;
        this.occupiedTracks = occupiedTracks;
    }

    /**
     * Uses static method to make testing easier.
     *
     * @throws NoTrackException if no track
     */
    public static TileTransition findNextStep(ReadOnlyWorld world, PositionOnTrack currentPosition, Vector2D target) {
        int startPos = PositionOnTrack.toInt(currentPosition.getLocation());
        int endPos = PositionOnTrack.toInt(target);
        HashMap<Integer, TileTransition> destPaths = pathCache.get(endPos);
        TileTransition nextTileTransition;
        if (destPaths != null) {
            nextTileTransition = destPaths.get(startPos);
            if (nextTileTransition != null) {
                cacheHit++;
                return nextTileTransition;
            }
        } else {
            destPaths = new HashMap<>();
            pathCache.put(endPos, destPaths);
        }
        cacheMiss++;
        PathOnTrackFinder pathFinder = new PathOnTrackFinder(world);

        try {
            pathFinder.setupSearch(currentPosition.getLocation(), target);
            pathFinder.search(-1);
            TileTransition[] pathAsVectors = pathFinder.pathAsVectors();
            List<Integer> pathAsInts = pathFinder.pathAsInts();
            for (int i = 0; i < pathAsInts.size() - 1; i++) {
                int calcPos = pathAsInts.get(i) & (PositionOnTrack.MAX_COORDINATE | (PositionOnTrack.MAX_COORDINATE << PositionOnTrack.BITS_FOR_COORDINATE));
                destPaths.put(calcPos, pathAsVectors[i + 1]);
            }
            nextTileTransition = pathAsVectors[0];
            return nextTileTransition;
        } catch (PathNotFoundException e) {
            // The pathfinder couldn't find a path so we go in any legal direction.
            GraphExplorer explorer = new FlatTrackExplorer(world, currentPosition);
            explorer.nextEdge();
            int next = explorer.getVertexConnectedByEdge();
            PositionOnTrack nextPosition = new PositionOnTrack(next);
            return nextPosition.cameFrom();
        }
    }

    /**
     *
     */
    public static void clearCache() {
        pathCache.clear();
        cacheCleared++;
        // System.out.println("CH:"+cacheHit+" CM:"+cacheMiss+"
        // CC:"+cacheCleared);
    }

    private static double acceleration(int wagons) {
        return 0.5d / (wagons + 1);
    }

    private static double topSpeed(int wagons) {
        return 10 / (wagons + 1);
    }

    /**
     * Returns true if an updated is due.
     */
    public boolean isUpdateDue(ReadOnlyWorld world) {
        GameTime currentTime = world.currentTime();
        TrainAccessor ta = new TrainAccessor(world, principal, trainID);
        ActivityIterator ai = world.getActivities(principal, trainID);
        ai.gotoLastActivity();

        double finishTime = ai.getFinishTime();
        double ticks = currentTime.getTicks();

        boolean hasFinishedLastActivity = Math.floor(finishTime) <= ticks;
        TrainState trainState = ta.getStatus(finishTime);
        if (trainState == TrainState.WAITING_FOR_FULL_LOAD) {
            // Check whether there is any cargo that can be added to the train.
            ImmutableList<Integer> spaceAvailable = ta.spaceAvailable();
            int stationId = ta.getStationId(ticks);
            if (stationId == -1) throw new IllegalStateException();

            Station station = (Station) world.get(principal, KEY.STATIONS, stationId);
            CargoBatchBundle cb = (CargoBatchBundle) world.get(principal, KEY.CARGO_BUNDLES, station.getCargoBundleID());

            for (int i = 0; i < spaceAvailable.size(); i++) {
                int space = spaceAvailable.get(i);
                int atStation = cb.getAmountOfType(i);
                if (space * atStation > 0) {

                    logger.debug("There is cargo to transfer!");

                    return true;
                }
            }

            return !ta.keepWaiting();
        }
        return hasFinishedLastActivity;
    }

    private Vector2D currentTrainTarget(ReadOnlyWorld world) {
        TrainAccessor ta = new TrainAccessor(world, principal, trainID);
        return ta.getTargetLocation();
    }

    // TODO optimize

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MoveTrainPreMove)) return false;

        final MoveTrainPreMove moveTrainPreMove = (MoveTrainPreMove) obj;

        if (trainID != moveTrainPreMove.trainID) return false;
        return principal.equals(moveTrainPreMove.principal);
    }

    /**
     * @param world
     * @return
     */
    public Move generateMove(ReadOnlyWorld world) {

        // Check that we can generate a move.
        if (!isUpdateDue(world)) {
            throw new IllegalStateException();
        }

        TrainAccessor ta = new TrainAccessor(world, principal, trainID);
        TrainMotion tm = ta.findCurrentMotion(Double.MAX_VALUE);

        TrainState activity = tm.getActivity();

        switch (activity) {
            case STOPPED_AT_STATION:
                return moveTrain(world, occupiedTracks);
            case READY: {
                // Are we at a station?
                TrainStopsHandler stopsHandler = new TrainStopsHandler(trainID, principal, new FullWorldDiffs(world));
                ta.getStationId(Integer.MAX_VALUE);
                PositionOnTrack positionOnTrack = tm.getFinalPosition();
                Vector2D p = positionOnTrack.getLocation();
                boolean atStation = stopsHandler.getStationID(p) >= 0;

                TrainMotion nextMotion;
                if (atStation) {
                    // We have just arrived at a station.
                    double durationOfStationStop = 10;

                    stopsHandler.arrivesAtPoint(p);

                    TrainState status = stopsHandler.isWaiting4FullLoad() ? TrainState.WAITING_FOR_FULL_LOAD : TrainState.STOPPED_AT_STATION;
                    PathOnTiles path = tm.getPath();
                    int lastTrainLength = tm.getTrainLength();
                    int currentTrainLength = stopsHandler.getTrainLength();

                    // If we are adding wagons we may need to lengthen the path.
                    if (lastTrainLength < currentTrainLength) {
                        path = TrainStopsHandler.lengthenPath(world, path, currentTrainLength);
                    }

                    nextMotion = new TrainMotion(path, currentTrainLength, durationOfStationStop, status);

                    // Create a new Move object.
                    Move trainMove = new NextActivityMove(nextMotion, trainID, principal);

                    Move cargoMove = stopsHandler.getMoves();
                    return new CompositeMove(trainMove, cargoMove);
                }
                return moveTrain(world, occupiedTracks);
            }
            case WAITING_FOR_FULL_LOAD: {
                TrainStopsHandler stopsHandler = new TrainStopsHandler(trainID, principal, new FullWorldDiffs(world));

                boolean waiting4fullLoad = stopsHandler.refreshWaitingForFullLoad();
                Move cargoMove = stopsHandler.getMoves();
                if (!waiting4fullLoad) {
                    Move trainMove = moveTrain(world, occupiedTracks);
                    if (null != trainMove) {
                        return new CompositeMove(trainMove, cargoMove);
                    } else {
                        return cargoMove;
                    }
                }
                stopsHandler.makeTrainWait(30);
                return cargoMove;
            }
            default:
                throw new UnsupportedOperationException(activity.toString());
        }
    }

    @Override
    public int hashCode() {
        int result;
        result = trainID;
        result = 29 * result + principal.hashCode();
        return result;
    }

    private TrainMotion lastMotion(ReadOnlyWorld world) {
        ActivityIterator ai = world.getActivities(principal, trainID);
        ai.gotoLastActivity();
        return (TrainMotion) ai.getActivity();
    }

    private Move moveTrain(ReadOnlyWorld world, OccupiedTracks occupiedTracks) {
        // Find the next vector.
        TileTransition nextVector = nextStep(world);

        TrainMotion motion = lastMotion(world);
        PositionOnTrack positionOnTrack = motion.getFinalPosition();
        TrackSection desiredTrackSection = new TrackSection(nextVector, positionOnTrack.getLocation());

        // Check whether the desired track section is single or double track.
        Vector2D tileA = desiredTrackSection.tileA();
        Vector2D tileB = desiredTrackSection.tileB();
        FullTerrainTile fta = (FullTerrainTile) world.getTile(tileA);
        FullTerrainTile ftb = (FullTerrainTile) world.getTile(tileB);
        TrackPiece tpa = fta.getTrackPiece();
        TrackPiece tpb = ftb.getTrackPiece();
        int tracks = 1;
        if (tpa.getTrackRule().isDouble() && tpb.getTrackRule().isDouble()) {
            tracks = 2;
        }
        Integer trains = occupiedTracks.occupiedTrackSections.get(desiredTrackSection);
        if (trains != null) {
            if (trains >= tracks) {
                // We need to wait for the track ahead to clear.
                occupiedTracks.stopTrain(trainID);
                return stopTrain(world);
            }
        }
        // Create a new train motion object.
        TrainMotion nextMotion = nextMotion(world, nextVector);
        return new NextActivityMove(nextMotion, trainID, principal);
    }

    private TrainMotion nextMotion(ReadOnlyWorld world, TileTransition tileTransition) {
        TrainMotion motion = lastMotion(world);

        Motion speeds = nextSpeeds(world, tileTransition);

        PathOnTiles currentTiles = motion.getTiles(motion.duration());
        PathOnTiles pathOnTiles = currentTiles.addStep(tileTransition);
        return new TrainMotion(pathOnTiles, currentTiles.steps(), motion.getTrainLength(), speeds);
    }

    public Motion nextSpeeds(ReadOnlyWorld world, TileTransition tileTransition) {
        TrainAccessor ta = new TrainAccessor(world, principal, trainID);
        TrainMotion lastMotion = lastMotion(world);

        double u = lastMotion.getSpeedAtEnd();
        double s = tileTransition.getLength();

        int wagons = ta.getTrain().getNumberOfWagons();
        double a0 = acceleration(wagons);
        double topSpeed = topSpeed(wagons);

        Motion newSpeeds;
        if (u < topSpeed) {
            double t = (topSpeed - u) / a0;
            Motion a = ConstantAccelerationMotion.fromSpeedAccelerationTime(u, a0, t);
            t = s / topSpeed + 1; // Slightly overestimate the time
            Motion b = ConstantAccelerationMotion.fromSpeedAccelerationTime(topSpeed, 0, t);
            newSpeeds = new CompositeMotion(a, b);
        } else {
            double t;
            t = s / topSpeed + 1; // Slightly overestimate the time
            newSpeeds = ConstantAccelerationMotion.fromSpeedAccelerationTime(topSpeed, 0, t);
        }

        return newSpeeds;
    }

    public TileTransition nextStep(ReadOnlyWorld world) {
        // Find current position.
        TrainMotion currentMotion = lastMotion(world);
        PositionOnTrack currentPosition = currentMotion.getFinalPosition();
        // Find targets
        Vector2D targetPoint = currentTrainTarget(world);
        return findNextStep(world, currentPosition, targetPoint);
    }

    /**
     * @param world
     * @return
     */
    public Move stopTrain(ReadOnlyWorld world) {
        TrainMotion motion = lastMotion(world);
        Motion stopped = ConstantAccelerationMotion.STOPPED;
        double duration = motion.duration();

        int trainLength = motion.getTrainLength();
        PathOnTiles tiles = motion.getTiles(duration);
        int engineDist = tiles.steps();
        TrainMotion nextMotion = new TrainMotion(tiles, engineDist, trainLength, stopped);
        return new NextActivityMove(nextMotion, trainID, principal);
    }
}
