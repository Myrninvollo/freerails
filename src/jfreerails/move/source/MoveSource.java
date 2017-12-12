package jfreerails.move.source;
import jfreerails.move.receiver.MoveReceiver;



/**
 * Defining operations expected of ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public interface MoveSource {

  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @param mr ...
 */

    public void attachMoveReceiver(MoveReceiver mr);
/**
 * Does ...
 * 
 * @param mr ...
 */

    public void detachMoveReceiver(MoveReceiver mr);

}

