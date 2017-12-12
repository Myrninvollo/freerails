package jfreerails.world.train;

import java.awt.Point;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/* This class stores a series of points.  Points can
 * be added and removed from either end, and the coordinates
 * of the point at either end can be changed.  It is intended
 * to be used to represent the position of a train.
 */

public class SnakeImpl implements Snake {
    
    private final LinkedList linkedList = new LinkedList();
    
    public SnakeImpl() {
        linkedList.add(new Point(0, 0));
        linkedList.add(new Point(0, 0));
    }
    
    public void addToHead(int x, int y) {
        Point p=new Point(x,y);
        linkedList.addFirst(p);
    }
    
    public void removeFromHead() {
        if(linkedList.size()<3){
            throw new IllegalStateException("The snake must have at least two points");
        }        
        linkedList.removeFirst();        
    }
    
    public void moveHead(int x, int y) {
        Point p=(Point)linkedList.getFirst();
        p.x=x;
        p.y=y;
    }
    
    public void addToTail(int x, int y) {
        Point p=new Point(x,y);
        linkedList.addLast(p);
    }
    
    public void removeFromTail() {
        if(linkedList.size()<3){
            throw new IllegalStateException("The snake must have at least two points");
        }        
        linkedList.removeLast();        
    }
    
    public void moveTail(int x, int y) {
        Point p=(Point)linkedList.getLast();
        p.x=x;
        p.y=y;
    }
    
    
    public FreerailsPathIterator pathIterator() {
       return new FreerailsPathIteratorImpl(linkedList);
    }
    
}
