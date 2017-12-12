/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.type.EngineType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class EngineModel extends RailwayVehicleModel {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private boolean hasPassedMaintenanceShopThisPeriod;

   ///////////////////////////////////////
   // associations

    public Vector engineType = new Vector();
    public TrainModelPublic trainModelPublic;


  ///////////////////////////////////////
  //access methods for attributes

    public boolean getHasPassedMaintenanceShopThisPeriod() {
        return hasPassedMaintenanceShopThisPeriod;
    }
    public void setHasPassedMaintenanceShopThisPeriod(boolean hasPassedMaintenanceShopThisPeriod) {
        this.hasPassedMaintenanceShopThisPeriod = hasPassedMaintenanceShopThisPeriod;
    }

   ///////////////////////////////////////
   // access methods for associations


    public Vector getEngineType() {
        return engineType;
    }
    public void addEngineType(EngineType engineType) {
        if (! this.engineType.contains(engineType)) {     
            this.engineType.addElement(engineType);  
            engineType.setEngineModel(this);
        }
    }
    public void removeEngineType(EngineType engineType) {
        if (this.engineType.removeElement(engineType)) {      
            engineType.setEngineModel(null);
        }
    }

    public TrainModelPublic getTrainModelPublic() {
        return trainModelPublic;
    }
    public void setTrainModelPublic(TrainModelPublic trainModelPublic) {
        if (this.trainModelPublic != trainModelPublic) {
            if (this.trainModelPublic != null) 
                this.trainModelPublic.removeEngineModel(this);     
            this.trainModelPublic = trainModelPublic;
            if (trainModelPublic != null)
                trainModelPublic.addEngineModel(this);  
        }
    } 



}





