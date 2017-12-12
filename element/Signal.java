/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.element.container.TrainContainer;
import jfreerails.type.SignalValue;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class Signal implements TrainContainer {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private SignalValue signalValue;

   ///////////////////////////////////////
   // associations

    public SignalTower signalTower;
    public TrackSection trackSection;
    public SignalBlock signalBlock;


  ///////////////////////////////////////
  //access methods for attributes

    public SignalValue getSignalValue() {
        return signalValue;
    }
    public void setSignalValue(SignalValue signalValue) {
        this.signalValue = signalValue;
    }

   ///////////////////////////////////////
   // access methods for associations


    public SignalTower getSignalTower() {
        return signalTower;
    }
    public void setSignalTower(SignalTower signalTower) {
        if (this.signalTower != signalTower) {
            this.signalTower = signalTower;
            if (signalTower != null)
                signalTower.setSignal(this);  
        }      
    } 

    public TrackSection getTrackSection() {
        return trackSection;
    }
    public void setTrackSection(TrackSection trackSection) {
        if (this.trackSection != trackSection) {
            this.trackSection = trackSection;
            if (trackSection != null)
                trackSection.setSignal(this);  
        }      
    } 

    public SignalBlock getSignalBlock() {
        return signalBlock;
    }
    public void setSignalBlock(SignalBlock signalBlock) {
        if (this.signalBlock != signalBlock) {
            this.signalBlock = signalBlock;
            if (signalBlock != null)
                signalBlock.setSignal(this);  
        }      
    } 



}





