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

public abstract class OnTrainFacility {

   ///////////////////////////////////////
   // associations

    public Vector railwayVehicleType = new Vector();


   ///////////////////////////////////////
   // access methods for associations


    public Vector getRailwayVehicleType() {
        return railwayVehicleType;
    }
    public void addRailwayVehicleType(RailwayVehicleType railwayVehicleType) {
        if (! this.railwayVehicleType.contains(railwayVehicleType)) {     
            this.railwayVehicleType.addElement(railwayVehicleType);  
            railwayVehicleType.addOnTrainFacility(this);
        }
    }
    public void removeRailwayVehicleType(RailwayVehicleType railwayVehicleType) {
        if (this.railwayVehicleType.removeElement(railwayVehicleType)) {      
            railwayVehicleType.removeOnTrainFacility(this);
        }
    }



}





