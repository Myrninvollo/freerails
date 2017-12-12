/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;
import jfreerails.element.*;



/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class StandardTrackNode extends TrackNode {

   ///////////////////////////////////////
   // associations

    public SignalTower signalTower;


   ///////////////////////////////////////
   // access methods for associations


    public SignalTower getSignalTower() {
        return signalTower;
    }
    public void setSignalTower(SignalTower signalTower) {
        if (this.signalTower != signalTower) {
            this.signalTower = signalTower;
            if (signalTower != null)
                signalTower.setStandardTrackNode(this);  
        }      
    } 



}





