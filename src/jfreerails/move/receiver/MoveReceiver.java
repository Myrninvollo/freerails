package jfreerails.move.receiver;
import jfreerails.move.Move;
import jfreerails.move.status.MoveStatus;




/**
 * Defining operations expected of ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public interface MoveReceiver {

   ///////////////////////////////////////
  // associations



  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @param Move ...
 * @return A MoveStatus with ...
 */

    MoveStatus processMove(Move Move);

}

