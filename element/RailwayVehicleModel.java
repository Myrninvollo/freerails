/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.list.RailwayVehicleList;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public abstract class RailwayVehicleModel {

   ///////////////////////////////////////
   // associations

    public CargoCompartmentModel cargoCompartmentModel;
    public RailwayVehicleList railwayVehicleList;


   ///////////////////////////////////////
   // access methods for associations


    public CargoCompartmentModel getCargoCompartmentModel() {
        return cargoCompartmentModel;
    }
    public void setCargoCompartmentModel(CargoCompartmentModel cargoCompartmentModel) {
        if (this.cargoCompartmentModel != cargoCompartmentModel) {
            if (this.cargoCompartmentModel != null) 
                this.cargoCompartmentModel.removeRailwayVehicleModel(this);     
            this.cargoCompartmentModel = cargoCompartmentModel;
            if (cargoCompartmentModel != null)
                cargoCompartmentModel.addRailwayVehicleModel(this);  
        }
    } 

    public RailwayVehicleList getRailwayVehicleList() {
        return railwayVehicleList;
    }
    public void setRailwayVehicleList(RailwayVehicleList railwayVehicleList) {
        if (this.railwayVehicleList != railwayVehicleList) {
            this.railwayVehicleList = railwayVehicleList;
            if (railwayVehicleList != null)
                railwayVehicleList.setRailwayVehicleModel(this);  
        }      
    } 



}





