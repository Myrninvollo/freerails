package jfreerails.world.train;

import jfreerails.lib.FreerailsSerializable;



public interface Snake extends FreerailsSerializable {
	
	void addToHead(int x, int y);
	
	void removeFromHead();
	
	void moveHead(int x, int y);
	
	void addToTail(int x, int y);
	
	void removeFromTail();
	
	void moveTail(int x, int y);
	
	FreerailsPathIterator pathIterator();
}
