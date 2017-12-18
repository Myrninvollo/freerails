package freerails.world.train;

import freerails.util.FreerailsIntIterator;
import freerails.world.Constants;
import freerails.world.common.FreerailsPathIterator;
import freerails.world.common.IntLine;
import freerails.world.common.PositionOnTrack;

/**
 * Exposes the path of a train. TODO needs better comment
 *
 * @author Luke Lindsay
 */
public class TrainPathIterator implements FreerailsPathIterator {
    private static final long serialVersionUID = 3256999977816502584L;
    private static final int tileSize = Constants.TILE_SIZE;
    private final FreerailsIntIterator intIterator;
    private final PositionOnTrack p1 = new PositionOnTrack();
    private final PositionOnTrack p2 = new PositionOnTrack();

    public TrainPathIterator(FreerailsIntIterator i) {
        intIterator = i;
        p2.setValuesFromInt(intIterator.nextInt());
    }

    public boolean hasNext() {
        return intIterator.hasNextInt();
    }

    public void nextSegment(IntLine line) {
        p1.setValuesFromInt(p2.toInt());
        line.x1 = p1.getX() * tileSize + tileSize / 2;
        line.y1 = p1.getY() * tileSize + tileSize / 2;
        p2.setValuesFromInt(intIterator.nextInt());
        line.x2 = p2.getX() * tileSize + tileSize / 2;
        line.y2 = p2.getY() * tileSize + tileSize / 2;
    }
}