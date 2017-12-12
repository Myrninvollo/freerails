/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.type;

import java.util.*;

import jfreerails.element.CargoCompartmentModel;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class CargoCompartmentType {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public int capacity;

   ///////////////////////////////////////
   // associations

    public Vector cargoCompartmentModel = new Vector();
    public RailwayVehicleType railwayVehicleType;


  ///////////////////////////////////////
  //access methods for attributes

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

   ///////////////////////////////////////
   // access methods for associations


    public Vector getCargoCompartmentModel() {
        return cargoCompartmentModel;
    }
    public void addCargoCompartmentModel(CargoCompartmentModel cargoCompartmentModel) {
        if (! this.cargoCompartmentModel.contains(cargoCompartmentModel)) {     
            this.cargoCompartmentModel.addElement(cargoCompartmentModel);  
            cargoCompartmentModel.setCargoCompartmentType(this);
        }
    }
    public void removeCargoCompartmentModel(CargoCompartmentModel cargoCompartmentModel) {
        if (this.cargoCompartmentModel.removeElement(cargoCompartmentModel)) {      
            cargoCompartmentModel.setCargoCompartmentType(null);
        }
    }

    public RailwayVehicleType getRailwayVehicleType() {
        return railwayVehicleType;
    }
    public void setRailwayVehicleType(RailwayVehicleType railwayVehicleType) {
        if (this.railwayVehicleType != railwayVehicleType) {
            this.railwayVehicleType = railwayVehicleType;
            if (railwayVehicleType != null)
                railwayVehicleType.setCargoCompartmentType(this);  
        }      
    } 



}





