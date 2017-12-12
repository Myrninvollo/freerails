package jfreerails.controller;



/**
 * Defining operations expected of ...
 *
 *
 * @author lindsal
 */

public interface MoveSource {


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

