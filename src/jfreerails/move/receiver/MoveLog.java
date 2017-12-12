/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */

package jfreerails.move.receiver;
import java.util.Vector;
import jfreerails.move.Move;
import jfreerails.move.status.MoveStatus;




/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class MoveLog implements MoveReceiver {

   ///////////////////////////////////////
   // associations

    public MoveReceiver moveReceiver;
    public Vector move = new Vector();


   ///////////////////////////////////////
   // access methods for associations


    public MoveReceiver getMoveReceiver() {
        return moveReceiver;
    }
    public void setMoveReceiver(MoveReceiver moveReceiver) {
            this.moveReceiver = moveReceiver;
    } 

    public Vector getMove() {
        return move;
    }
    public void addMove(Move move) {
        if (! this.move.contains(move)) {     
            this.move.addElement(move);  
        }
    }
    public void removeMove(Move move) {    
        this.move.removeElement(move);        
    }


  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @param Move ...
 * @return A MoveStatus with ...
 */

    public MoveStatus processMove(Move Move) {
        return null;
    }



}





