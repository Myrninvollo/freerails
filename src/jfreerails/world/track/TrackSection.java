
package jfreerails.world.track;


import java.awt.Point;

import jfreerails.world.misc.FreerailsPathIterator;


public interface TrackSection {

  
    PositionOnTrack getStart(Point p);

    PositionOnTrack getEnd(Point p);

    FreerailsPathIterator getPath();

    int getLength();

}

