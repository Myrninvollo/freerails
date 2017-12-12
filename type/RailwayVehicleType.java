/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.type;

import java.util.*;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class RailwayVehicleType {

   ///////////////////////////////////////
   // associations

    public Vector onTrainFacility = new Vector();
    public CargoCompartmentType cargoCompartmentType;


   ///////////////////////////////////////
   // access methods for associations


    public Vector getOnTrainFacility() {
        return onTrainFacility;
    }
    public void addOnTrainFacility(OnTrainFacility onTrainFacility) {
        if (! this.onTrainFacility.contains(onTrainFacility)) {     
            this.onTrainFacility.addElement(onTrainFacility);  
            onTrainFacility.addRailwayVehicleType(this);
        }
    }
    public void removeOnTrainFacility(OnTrainFacility onTrainFacility) {
        if (this.onTrainFacility.removeElement(onTrainFacility)) {      
            onTrainFacility.removeRailwayVehicleType(this);
        }
    }

    public CargoCompartmentType getCargoCompartmentType() {
        return cargoCompartmentType;
    }
    public void setCargoCompartmentType(CargoCompartmentType cargoCompartmentType) {
        if (this.cargoCompartmentType != cargoCompartmentType) {
            this.cargoCompartmentType = cargoCompartmentType;
            if (cargoCompartmentType != null)
                cargoCompartmentType.setRailwayVehicleType(this);  
        }      
    } 



}





