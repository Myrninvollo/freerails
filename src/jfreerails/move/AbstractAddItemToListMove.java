/*
 * Created on 13-Apr-2003
 * 
 */
package jfreerails.move;

import jfreerails.world.common.FreerailsSerializable;
import jfreerails.world.top.KEY;
import jfreerails.world.top.World;

/**
 * All moves that add an item to a list should extend this class.
 * 
 * @author Luke
 * 
 */
public abstract class AbstractAddItemToListMove implements Move {
	
	private final KEY listKey;
	
	private final int index;
	
	private final FreerailsSerializable item;
	
	protected AbstractAddItemToListMove(KEY key, int i, FreerailsSerializable item){
		this.listKey=key;
		this.index=i;
		this.item=item;
	}

	public MoveStatus tryDoMove(World w) {
		if(w.size(listKey)!=index){
			return MoveStatus.moveFailed("Expected size of list is "+index+" but actual size is "+w.size(listKey));
		}	
		FreerailsSerializable item2remove = w.get(listKey, index);
		if(!item2remove.equals(item)){
			return MoveStatus.moveFailed("The item at the end of the list ("+item2remove.toString()+") is not the expected item ("+item.toString()+").");
		}
		return MoveStatus.MOVE_OK;
	}

	public MoveStatus tryUndoMove(World w) {
		if(w.size(listKey)!=(index+1)){
			return MoveStatus.moveFailed("Expected size of list is "+(index+1)+" but actual size is "+w.size(listKey));
		}	
		return MoveStatus.MOVE_OK;
	}

	public MoveStatus doMove(World w) {
		MoveStatus ms = tryDoMove(w);
		if(ms.isOk()){
			w.add(listKey, this.item);			
		}
		return ms;
	}

	public MoveStatus undoMove(World w) {
		MoveStatus ms = tryDoMove(w);
		if(ms.isOk()){
			w.add(listKey, this.item);			
		}
		return ms;
	}
}
