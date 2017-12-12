/**
 *
 *
 *
 *
 *
 */

package jfreerails.move;
import jfreerails.world.World;






/**
 *
 *
 *
 * @author lindsal
 */

final public class LeafMove implements Move {




/**
 *

 */

    public int oldPop;
/**
 *

 */

    public int newPop;


  //access methods for attributes

    public int getOldPop() {
        return oldPop;
    }
    public void setOldPop(int oldPop) {
        this.oldPop = oldPop;
    }
    public int getNewPop() {
        return newPop;
    }
    public void setNewPop(int newPop) {
        this.newPop = newPop;
    }


  // operations

/**
 * Does ...
 *
 * @param world ...
 * @return A MoveStatus with ...
 */

    public MoveStatus tryDoMove(World world) {
        return null;
    }
/**
 * Does ...
 *
 * @param world ...
 * @return A MoveStatus with ...
 */

    public MoveStatus tryUndoMove(World world) {
        return null;
    }
/**
 * Does ...
 *
 * @param world ...
 * @return A MoveStatus with ...
 */

    public MoveStatus doMove(World world) {
        return null;
    }
/**
 * Does ...
 *
 * @param world ...
 * @return A MoveStatus with ...
 */

    public MoveStatus undoMove(World world) {
        return null;
    }



}





