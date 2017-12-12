/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.massnoun;

import java.util.*;

import java.awt.Point;
import jfreerails.element.StationModelPublic;
import jfreerails.type.CargoType;
import jfreerails.element.CargoBundle;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public abstract class CargoBatch {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private Point pointOfOrigin;
/**
 * Represents ...

 */

    private StationModelPublic stationOfOrigin;

   ///////////////////////////////////////
   // associations

    public CargoType cargoType;
    public CargoBundle cargoBundle;


  ///////////////////////////////////////
  //access methods for attributes

    public Point getPointOfOrigin() {
        return pointOfOrigin;
    }
    public void setPointOfOrigin(Point pointOfOrigin) {
        this.pointOfOrigin = pointOfOrigin;
    }
    public StationModelPublic getStationOfOrigin() {
        return stationOfOrigin;
    }
    public void setStationOfOrigin(StationModelPublic stationOfOrigin) {
        this.stationOfOrigin = stationOfOrigin;
    }

   ///////////////////////////////////////
   // access methods for associations


    public CargoType getCargoType() {
        return cargoType;
    }
    public void setCargoType(CargoType cargoType) {
        if (this.cargoType != cargoType) {
            if (this.cargoType != null) 
                this.cargoType.removeCargoBatch(this);     
            this.cargoType = cargoType;
            if (cargoType != null)
                cargoType.addCargoBatch(this);  
        }
    } 

    public CargoBundle getCargoBundle() {
        return cargoBundle;
    }
    public void setCargoBundle(CargoBundle cargoBundle) {
        if (this.cargoBundle != cargoBundle) {
            if (this.cargoBundle != null) 
                this.cargoBundle.removeCargoBatch(this);     
            this.cargoBundle = cargoBundle;
            if (cargoBundle != null)
                cargoBundle.addCargoBatch(this);  
        }
    } 


  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @return A boolean with ...
 */

    public boolean hasTravelled() {
        return false;
    }



}





