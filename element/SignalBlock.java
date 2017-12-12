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

public class SignalBlock {

   ///////////////////////////////////////
   // associations

    public Vector trackSection = new Vector();
    public TrainModelPublic trainModelPublic;
    public Signal signal;


   ///////////////////////////////////////
   // access methods for associations


    public Vector getTrackSection() {
        return trackSection;
    }
    public void addTrackSection(TrackSection trackSection) {
        if (! this.trackSection.contains(trackSection)) {     
            this.trackSection.addElement(trackSection);  
            trackSection.addSignalBlock(this);
        }
    }
    public void removeTrackSection(TrackSection trackSection) {
        if (this.trackSection.removeElement(trackSection)) {      
            trackSection.removeSignalBlock(this);
        }
    }

    public TrainModelPublic getTrainModelPublic() {
        return trainModelPublic;
    }
    public void setTrainModelPublic(TrainModelPublic trainModelPublic) {
        if (this.trainModelPublic != trainModelPublic) {
            if (this.trainModelPublic != null) 
                this.trainModelPublic.removeSignalBlock(this);     
            this.trainModelPublic = trainModelPublic;
            if (trainModelPublic != null)
                trainModelPublic.addSignalBlock(this);  
        }
    } 

    public Signal getSignal() {
        return signal;
    }
    public void setSignal(Signal signal) {
        if (this.signal != signal) {
            this.signal = signal;
            if (signal != null)
                signal.setSignalBlock(this);  
        }      
    } 



}





