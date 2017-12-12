/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class SignalTower {

   ///////////////////////////////////////
   // associations

    public StandardTrackNode standardTrackNode;
    public Signal signal;


   ///////////////////////////////////////
   // access methods for associations


    public StandardTrackNode getStandardTrackNode() {
        return standardTrackNode;
    }
    public void setStandardTrackNode(StandardTrackNode standardTrackNode) {
        if (this.standardTrackNode != standardTrackNode) {
            this.standardTrackNode = standardTrackNode;
            if (standardTrackNode != null)
                standardTrackNode.setSignalTower(this);  
        }      
    } 

    public Signal getSignal() {
        return signal;
    }
    public void setSignal(Signal signal) {
        if (this.signal != signal) {
            this.signal = signal;
            if (signal != null)
                signal.setSignalTower(this);  
        }      
    } 



}





