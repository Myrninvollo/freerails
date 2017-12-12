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
import jfreerails.world.*;
import jfreerails.move.status.*;





/**
 * Represents ...
 * 
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class MoveExecuter implements MoveReceiver {

   ///////////////////////////////////////
   // associations

    public World world;


   ///////////////////////////////////////
   // access methods for associations


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





