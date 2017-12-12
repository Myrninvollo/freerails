/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */

package jfreerails.move.source;
import jfreerails.move.receiver.GUI;
import jfreerails.move.receiver.MoveReceiver;



/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class MoveProducer {

   ///////////////////////////////////////
   // associations

    public GUI gUI;
    public MoveReceiver moveReceiver;


   ///////////////////////////////////////
   // access methods for associations


    public GUI getGUI() {
        return gUI;
    }
    public void setGUI(GUI gUI) {
        if (this.gUI != gUI) {
            this.gUI = gUI;
            if (gUI != null)
                gUI.setMoveProducer(this);  
        }      
    } 

    public MoveReceiver getMoveReceiver() {
        return moveReceiver;
    }
    public void setMoveReceiver(MoveReceiver moveReceiver) {
            this.moveReceiver = moveReceiver;
    } 



 
  
}





