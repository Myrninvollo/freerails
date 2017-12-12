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

public class MoveTester implements MoveReceiver {


   // associations

    public World world;



   // access methods for associations


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





