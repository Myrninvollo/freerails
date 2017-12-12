
package jfreerails.world.cargo;

import java.util.Vector;



final public class CargoTypeList {

  

    private final Vector cargoType = new Vector();



    public Vector getCargoType() {
        return cargoType;
    }
    public void addCargoType(CargoType cargoType) {
        if (! this.cargoType.contains(cargoType)) {     
            this.cargoType.addElement(cargoType);  
            
        }
    }
    public void removeCargoType(CargoType cargoType) {
        if (this.cargoType.removeElement(cargoType)) {      
           
        }
    }



}





