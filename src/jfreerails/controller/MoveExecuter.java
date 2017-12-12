
package jfreerails.controller;
import jfreerails.move.Move;
import jfreerails.move.MoveStatus;
import jfreerails.world.World;





/**
 *
 *
 *
 *
 * @author lindsal
 */

public class MoveExecuter implements MoveReceiver {



    public World world;


    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
            this.world = world;
    }



    public MoveStatus processMove(Move Move) {
        return null;
    }



}





