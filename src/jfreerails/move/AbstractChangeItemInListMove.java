/*
 * Created on 13-Apr-2003
 * 
 */
package jfreerails.move;

import jfreerails.world.common.FreerailsSerializable;
import jfreerails.world.top.KEY;
import jfreerails.world.top.World;

/**
 * All Moves that replace an item in a list with another should extend this class.
 * 
 * @author Luke
 * 
 */
public abstract class AbstractChangeItemInListMove implements Move {
	
	private final KEY listKey;
	
	private final int index;
	
	private final FreerailsSerializable before, after;
	
	protected AbstractChangeItemInListMove(KEY k, int index, FreerailsSerializable before, FreerailsSerializable after){
		this.before = before;
		this.after = after;
		this.index = index;
		this.listKey = k;
	}

	public MoveStatus tryDoMove(World w) {
		// TODO Auto-generated method stub
		return null;
	}

	public MoveStatus tryUndoMove(World w) {
		// TODO Auto-generated method stub
		return null;
	}

	public MoveStatus doMove(World w) {
		// TODO Auto-generated method stub
		return null;
	}

	public MoveStatus undoMove(World w) {
		// TODO Auto-generated method stub
		return null;
	}

}
