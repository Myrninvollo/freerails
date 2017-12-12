
package jfreerails.world.track;


import java.awt.Point;


public interface TrackSection {

  
    PositionOnTrack getStart(Point p);

    PositionOnTrack getEnd(Point p);

    PositionOnTrack getLocation(Point p, int distance);

    int getLength();

}

