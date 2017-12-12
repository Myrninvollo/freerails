package jfreerails.world.misc;
import java.awt.Dimension;
import java.awt.Point;
public class PointOnTile {
    public int getHeight() {
        return height;
    }

    public Point getPoint(Dimension tilesSize) {
           throw new java.lang.UnsupportedOperationException("Method not implemented yet!");
    }

    private final int height;
    private final Point point;
    
    public PointOnTile(int h, Point p){
        height=h;
        point=new Point(p);
    }
}
