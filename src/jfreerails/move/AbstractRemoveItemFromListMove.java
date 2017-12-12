/*
 * Created on 13-Apr-2003
 * 
 */
package jfreerails.move;

import jfreerails.world.common.FreerailsSerializable;
import jfreerails.world.top.KEY;
import jfreerails.world.top.World;

/**
 * All moves that remove an item from a list should extend this class.
 * @author Luke
 * 
 */
public abstract class AbstractRemoveItemFromListMove implements Move {
	
	private final FreerailsSerializable item;
	
	private final KEY listKey;
	
	private final int index;
	
	protected AbstractRemoveItemFromListMove(KEY k, int i, FreerailsSerializable item){
		this.item=item;
		this.listKey=k;
		this.index=i;
	}
	

	public MoveStatus tryDoMove(World w) {
		FreerailsSerializable item2remove = w.get(listKey, index);
		if(!item2remove.equals(item)){						
			String reason = "The item at position "+index+" in the list ("+item2remove.toString()+") is not the expected item ("+item.toString()+").";
			System.out.println(reason);
			return MoveStatus.moveFailed(reason);
		}else{		
			return MoveStatus.MOVE_OK;
		}
	}

	public MoveStatus tryUndoMove(World w) {
		if(null != w.get(listKey, index)){
			String reason = "The item at position "+index+" in the list ("+w.get(listKey, index).toString()+") is not the expected item (null).";
			System.out.println(reason);
			return MoveStatus.moveFailed(reason);
		}else{		
			return MoveStatus.MOVE_OK;
		}
	}

	public MoveStatus doMove(World w) {		
		MoveStatus ms = tryDoMove(w);
		if(ms.isOk()){
			w.set(listKey, index, null);
			System.out.println("index="+index+"set to null");					
		}		
		return ms;
	}

	public MoveStatus undoMove(World w) {
		MoveStatus ms = tryUndoMove(w);
		if(ms.isOk()){
			w.set(listKey, index, this.item);			
		}		
		return ms;
	}
}
