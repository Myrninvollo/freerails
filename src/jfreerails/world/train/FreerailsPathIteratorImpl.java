/*
 * FreerailsPathIteratorImpl.java
 *
 * Created on 23 September 2002, 20:41
 */

package jfreerails.world.train;
import java.awt.Point;
import java.util.*;
/**
 *
 * @author  Luke Lindsay
 * @version
 */
public class FreerailsPathIteratorImpl implements FreerailsPathIterator {
    
    /** Creates new FreerailsPathIteratorImpl */
    public FreerailsPathIteratorImpl(List l) {
        points=l;
    }
    
    List points;
    
    int position = 0;
    
    public boolean hasNext() {
        return (position + 1) < points.size();
    }
    
    public void nextSegment(IntLine line) {
        if (hasNext()) {
            position++;
            Point a = (Point) points.get(position - 1);
            Point b = (Point) points.get(position);
            line.x1 = a.x;
            line.y1 = a.y;
            line.x2 = b.x;
            line.y2 = b.y;
            
        } else {
            throw new NoSuchElementException();
        }
    }
    
    
}
