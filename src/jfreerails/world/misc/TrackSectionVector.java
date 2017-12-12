package jfreerails.world.misc;

import java.awt.Dimension;
import java.awt.Point;
public interface TrackSectionVector {
   
    int getLength();

    Point getPosition(int distanceTravelled, Dimension tileSize
        );
}
