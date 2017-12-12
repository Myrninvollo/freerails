package jfreerails.world.std_track;

import java.awt.Dimension;
import java.awt.Point;
import jfreerails.world.tilemap.PointOnTile;
public interface TrackSectionVector {
    PointOnTile getEnd();

    PointOnTile getStart();

    int getLength();

    Point getPosition(int distanceTravelled, Dimension tileSize
        );
}
