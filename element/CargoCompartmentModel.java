/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.element.container.CargoContainer;
import jfreerails.type.CargoCompartmentType;
import jfreerails.type.CargoType;
import java.util.Iterator;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class CargoCompartmentModel implements CargoContainer {

   ///////////////////////////////////////
   // associations

    public Vector railwayVehicleModel = new Vector();
    public CargoCompartmentType cargoCompartmentType;


   ///////////////////////////////////////
   // access methods for associations


    public Vector getRailwayVehicleModel() {
        return railwayVehicleModel;
    }
    public void addRailwayVehicleModel(RailwayVehicleModel railwayVehicleModel) {
        if (! this.railwayVehicleModel.contains(railwayVehicleModel)) {     
            this.railwayVehicleModel.addElement(railwayVehicleModel);  
            railwayVehicleModel.setCargoCompartmentModel(this);
        }
    }
    public void removeRailwayVehicleModel(RailwayVehicleModel railwayVehicleModel) {
        if (this.railwayVehicleModel.removeElement(railwayVehicleModel)) {      
            railwayVehicleModel.setCargoCompartmentModel(null);
        }
    }

    public CargoCompartmentType getCargoCompartmentType() {
        return cargoCompartmentType;
    }
    public void setCargoCompartmentType(CargoCompartmentType cargoCompartmentType) {
        if (this.cargoCompartmentType != cargoCompartmentType) {
            if (this.cargoCompartmentType != null) 
                this.cargoCompartmentType.removeCargoCompartmentModel(this);     
            this.cargoCompartmentType = cargoCompartmentType;
            if (cargoCompartmentType != null)
                cargoCompartmentType.addCargoCompartmentModel(this);  
        }
    } 


  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @param type ...
 * @return A boolean with ...
 */

    public boolean canHold(CargoType type) {
        return false;
    }
/**
 * Does ...
 * 
 * @param bundle ...
 */

    public void addCargo(CargoBundle bundle) {
    }
/**
 * Does ...
 * 
 * @param bundle ...
 * @return A CargoBundle with ...
 */

    public CargoBundle removeCargo(CargoBundle bundle) {
        return null;
    }
/**
 * Does ...
 * 
 * @return A Iterator with ...
 */

    public Iterator getCargoBatchIterator() {
        return null;
    }
/**
 * Does ...
 * 
 * @param bundle ...
 * @return A boolean with ...
 */

    public boolean contains(CargoBundle bundle) {
        return false;
    }



}





