/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */

package jfreerails.move;
import jfreerails.move.status.MoveStatus;
import jfreerails.world.World;






/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class LeafMove implements Move {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public int oldPop;
/**
 * Represents ...

 */

    public int newPop;

  ///////////////////////////////////////
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

  ///////////////////////////////////////
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





