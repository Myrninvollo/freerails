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

public class CargoTypeList {

   ///////////////////////////////////////
   // associations

    public Vector cargoType = new Vector();


   ///////////////////////////////////////
   // access methods for associations


    public Vector getCargoType() {
        return cargoType;
    }
    public void addCargoType(CargoType cargoType) {
        if (! this.cargoType.contains(cargoType)) {     
            this.cargoType.addElement(cargoType);  
            cargoType.setCargoTypeList(this);
        }
    }
    public void removeCargoType(CargoType cargoType) {
        if (this.cargoType.removeElement(cargoType)) {      
            cargoType.setCargoTypeList(null);
        }
    }



}





