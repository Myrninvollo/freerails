package jfreerails.world.track;

import java.awt.Dimension;
import java.awt.Point;

import jfreerails.world.misc.PointOnTile;
import jfreerails.world.misc.TrackSectionVector;
public class TrackSectionVectorImpl implements TrackSectionVector {
    public Point getPosition(int distanceTravelled, Dimension tileSize){
   throw new java.lang.UnsupportedOperationException("Method not implemented yet!");}
    
    public PointOnTile getEnd() {
        return null;
    }
    
    public PointOnTile getStart() {
        return null;
    }
    
    public int getLength() {
        return 0;
    }
    
    private PointOnTile start;
    private PointOnTile end;
}