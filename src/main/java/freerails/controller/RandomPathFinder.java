package freerails.controller;

import freerails.world.Constants;
import freerails.world.common.FreerailsPathIterator;
import freerails.world.common.IntLine;
import freerails.world.common.PositionOnTrack;

/**
 * Returns a random path along the track.
 *
 * @author Luke Lindsay 13-Oct-2002
 */
public class RandomPathFinder implements FreerailsPathIterator {
    private static final long serialVersionUID = 3832906571880608313L;
    private static final int tileSize = Constants.TILE_SIZE;
    private final FlatTrackExplorer trackExplorer;
    private final PositionOnTrack p1 = new PositionOnTrack();
    private final PositionOnTrack p2 = new PositionOnTrack();

    /**
     *
     * @param tx
     */
    public RandomPathFinder(FlatTrackExplorer tx) {
        trackExplorer = tx;
    }

    public boolean hasNext() {
        return trackExplorer.hasNextEdge();
    }

    public void nextSegment(IntLine line) {
        p1.setValuesFromInt(trackExplorer.getPosition());
        line.x1 = p1.getX() * tileSize + tileSize / 2;
        line.y1 = p1.getY() * tileSize + tileSize / 2;
        trackExplorer.nextEdge();
        trackExplorer.moveForward();
        p2.setValuesFromInt(trackExplorer.getPosition());
        line.x2 = p2.getX() * tileSize + tileSize / 2;
        line.y2 = p2.getY() * tileSize + tileSize / 2;
    }
}