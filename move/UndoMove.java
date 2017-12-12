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

public class UndoMove implements Move {

   ///////////////////////////////////////
   // associations

    public Move move;


   ///////////////////////////////////////
   // access methods for associations


    public Move getMove() {
        return move;
    }
    public void setMove(Move move) {
            this.move = move;
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





