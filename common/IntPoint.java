/*
 * SimplePoint.java
 *
 * Created on 17 July 2001, 19:49
 */

package jfreerails.common;
import java.awt.Point;

/**
 *
 * @author  Luke Lindsay
 * @version 
 */
public class IntPoint extends java.lang.Object {

    private int x;
    
    private int y;
    
    /** Creates new SimplePoint */
    public IntPoint(int x,int y) {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
}
    
public int getY() {
    return y;
}

public Point getAWTPoint() {
    return new Point(x,y);
}

}
