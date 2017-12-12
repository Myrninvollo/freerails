package jfreerails.world.train;

import java.awt.Point;

import jfreerails.world.misc.FreerailsPathIterator;
import jfreerails.world.misc.FreerailsSerializable;



public interface Snake extends FreerailsSerializable {
	
	void addToHead(int x, int y);
	
	void removeFromHead();
	
	void moveHead(int x, int y);
	
	void addToTail(int x, int y);
	
	void removeFromTail();
	
	void moveTail(int x, int y);
	
	void getHead(Point p);
	
	void getTail(Point p);
	
	FreerailsPathIterator pathIterator();
	
	FreerailsPathIterator reversePathIterator();
}
