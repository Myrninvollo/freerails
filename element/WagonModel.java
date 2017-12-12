/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.type.WagonType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class WagonModel extends RailwayVehicleModel {

   ///////////////////////////////////////
   // associations

    public Vector wagonType = new Vector();
    public TrainModelPublic trainModelPublic;


   ///////////////////////////////////////
   // access methods for associations


    public Vector getWagonType() {
        return wagonType;
    }
    public void addWagonType(WagonType wagonType) {
        if (! this.wagonType.contains(wagonType)) {     
            this.wagonType.addElement(wagonType);  
            wagonType.setWagonModel(this);
        }
    }
    public void removeWagonType(WagonType wagonType) {
        if (this.wagonType.removeElement(wagonType)) {      
            wagonType.setWagonModel(null);
        }
    }

    public TrainModelPublic getTrainModelPublic() {
        return trainModelPublic;
    }
    public void setTrainModelPublic(TrainModelPublic trainModelPublic) {
        if (this.trainModelPublic != trainModelPublic) {
            if (this.trainModelPublic != null) 
                this.trainModelPublic.removeWagonModel(this);     
            this.trainModelPublic = trainModelPublic;
            if (trainModelPublic != null)
                trainModelPublic.addWagonModel(this);  
        }
    } 



}





