/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */

package jfreerails.move.receiver;
import jfreerails.move.Move;
import jfreerails.move.source.MoveProducer;
import jfreerails.move.status.MoveStatus;
import jfreerails.world.World;






/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class GUI implements MoveReceiver {

   ///////////////////////////////////////
   // associations

    public MoveProducer moveProducer;
    public World world;


   ///////////////////////////////////////
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





