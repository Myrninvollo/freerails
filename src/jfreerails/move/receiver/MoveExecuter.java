
package jfreerails.move.receiver;
import jfreerails.move.Move;
import jfreerails.move.status.MoveStatus;
import jfreerails.world.World;





/**
 * Represents ...
 * 
 * 
 * @see OtherClasses
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





