/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */

package jfreerails.move.receiver;
import java.util.*;
import jfreerails.move.*;
import jfreerails.move.status.*;




/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ConnectionToServer implements MoveReceiver {

   ///////////////////////////////////////
   // associations

    public MoveReceiver moveReceiver;


   ///////////////////////////////////////
   // access methods for associations


    public MoveReceiver getMoveReceiver() {
        return moveReceiver;
    }
    public void setMoveReceiver(MoveReceiver moveReceiver) {
            this.moveReceiver = moveReceiver;
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





