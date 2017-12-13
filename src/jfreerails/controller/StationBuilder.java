/**
 * @author Luke Lindsay 08-Nov-2002
 *
 * Updated 12th April 2003 by Scott Bennett to include nearest city names.
 *
 * Class to build a station at a given point, names station after nearest
 * city. If that name is taken then a "Junction" or "Siding" is added to
 * the name.
 */
package jfreerails.controller;

import java.awt.Point;
import jfreerails.move.AddStationMove;
import jfreerails.move.ChangeTrackPieceCompositeMove;
import jfreerails.move.ChangeTrackPieceMove;
import jfreerails.move.Move;
import jfreerails.move.MoveStatus;
import jfreerails.world.player.FreerailsPrincipal;
import jfreerails.world.top.ReadOnlyWorld;
import jfreerails.world.top.SKEY;
import jfreerails.world.track.FreerailsTile;
import jfreerails.world.track.NullTrackType;
import jfreerails.world.track.TrackPiece;
import jfreerails.world.track.TrackRule;


public class StationBuilder {
    private UntriedMoveReceiver moveReceiver;
    private ReadOnlyWorld w;
    private int ruleNumber;
    private TrackMoveTransactionsGenerator transactionsGenerator;
    private final FreerailsPrincipal principal;

    public StationBuilder(UntriedMoveReceiver moveReceiver,
        ReadOnlyWorld world, FreerailsPrincipal p) {
        this.moveReceiver = moveReceiver;
        this.principal = p;
        w = world;

        TrackRule trackRule;

        int i = -1;

        do {
            i++;
            trackRule = (TrackRule)w.get(SKEY.TRACK_RULES, i);
        } while (!trackRule.isStation());

        ruleNumber = i;
        transactionsGenerator = new TrackMoveTransactionsGenerator(w, p);
    }

    public boolean canBuiltStationHere(Point p) {
        FreerailsTile oldTile = w.getTile(p.x, p.y);

        return !oldTile.getTrackRule().equals(NullTrackType.getInstance());
    }

    public MoveStatus buildStation(Point p) {
        FreerailsTile oldTile = w.getTile(p.x, p.y);

        //Only build a station if there is track at the specified point.
        if (canBuiltStationHere(p)) {
            String cityName;
            String stationName;

            TrackPiece before = (TrackPiece)w.getTile(p.x, p.y);
            TrackRule trackRule = (TrackRule)w.get(SKEY.TRACK_RULES,
                    this.ruleNumber);

            int owner = ChangeTrackPieceCompositeMove.getOwner(this.principal, w);
            TrackPiece after = trackRule.getTrackPiece(before.getTrackConfiguration(),
                    owner);
            ChangeTrackPieceMove upgradeTrackMove = new ChangeTrackPieceMove(before,
                    after, p);

            //Check whether we can upgrade the track to a station here.
            MoveStatus statusa = moveReceiver.tryDoMove(upgradeTrackMove);

            if (!statusa.ok) {
                System.err.println("Cannot upgrade this track to a station!");

                return statusa;
            }

            Move move;

            if (!oldTile.getTrackRule().isStation()) {
                //There isn't already a station here, we need to pick a name and add an entry
                //to the station list.
                CalcNearestCity cNC = new CalcNearestCity(w, p.x, p.y);
                cityName = cNC.findNearestCity();

                VerifyStationName vSN = new VerifyStationName(w, cityName);
                stationName = vSN.getName();

                if (stationName == null) {
                    //there are no cities, this should never happen
                    stationName = "Central Station";
                }

                //check the terrain to see if we can build a station on it...
                move = AddStationMove.generateMove(w, stationName, p,
                        upgradeTrackMove, principal);

                move = transactionsGenerator.addTransactions(move);
            } else {
                //Upgrade an existing station.
                move = AddStationMove.upgradeStation(upgradeTrackMove);
            }

            MoveStatus status = moveReceiver.tryDoMove(move);

            if (status.isOk()) {
                this.moveReceiver.processMove(move);
            } else {
            }

            return status;
        } else {
            String message = "Can't build station since there is no track here!";
            System.err.println(message);

            return MoveStatus.moveFailed(message);
        }
    }

    public void setStationType(int ruleNumber) {
        this.ruleNumber = ruleNumber;
    }
}