/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.type.CargoType;
import jfreerails.massnoun.CargoBatch;
import java.util.Iterator;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class CargoBundle {

   ///////////////////////////////////////
   // associations

    public CargoType cargoType;
    public Vector cargoBatch = new Vector();


   ///////////////////////////////////////
   // access methods for associations


    public CargoType getCargoType() {
        return cargoType;
    }
    public void setCargoType(CargoType cargoType) {
        if (this.cargoType != cargoType) {
            if (this.cargoType != null) 
                this.cargoType.removeCargoBundle(this);     
            this.cargoType = cargoType;
            if (cargoType != null)
                cargoType.addCargoBundle(this);  
        }
    } 

    public Vector getCargoBatch() {
        return cargoBatch;
    }
    public void addCargoBatch(CargoBatch cargoBatch) {
        if (! this.cargoBatch.contains(cargoBatch)) {     
            this.cargoBatch.addElement(cargoBatch);  
            cargoBatch.setCargoBundle(this);
        }
    }
    public void removeCargoBatch(CargoBatch cargoBatch) {
        if (this.cargoBatch.removeElement(cargoBatch)) {      
            cargoBatch.setCargoBundle(null);
        }
    }


  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @param bundle ...
 * @return A CargoBundle with ...
 */

    public CargoBundle addCargo(CargoBundle bundle) {
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



}





