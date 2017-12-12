/**
 *
 *
 *
 *
 *
 */

package jfreerails.controller;
import jfreerails.move.Move;
import jfreerails.move.MoveStatus;
import jfreerails.world.World;






/**
 *
 *
 *
 * @author lindsal
 */

public class GUI implements MoveReceiver {


   // associations

    public MoveProducer moveProducer;
    public World world;



   // access methods for associations


    public MoveProducer getMoveProducer() {
        return moveProducer;
    }
    public void setMoveProducer(MoveProducer moveProducer) {
        if (this.moveProducer != moveProducer) {
            this.moveProducer = moveProducer;
            if (moveProducer != null)
                moveProducer.setGUI(this);
        }
    }

    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
            this.world = world;
    }



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





