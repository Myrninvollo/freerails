/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.type;

import java.util.*;

import java.lang.String;
import jfreerails.element.StationModelPublic;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class StationType {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private String typeName;
/**
 * Represents ...

 */

    private String passengersCanUse;

   ///////////////////////////////////////
   // associations

    public Vector stationModelPublic = new Vector();


  ///////////////////////////////////////
  //access methods for attributes

    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getPassengersCanUse() {
        return passengersCanUse;
    }
    public void setPassengersCanUse(String passengersCanUse) {
        this.passengersCanUse = passengersCanUse;
    }

   ///////////////////////////////////////
   // access methods for associations


    public Vector getStationModelPublic() {
        return stationModelPublic;
    }
    public void addStationModelPublic(StationModelPublic stationModelPublic) {
        if (! this.stationModelPublic.contains(stationModelPublic)) {     
            this.stationModelPublic.addElement(stationModelPublic);  
            stationModelPublic.setStationType(this);
        }
    }
    public void removeStationModelPublic(StationModelPublic stationModelPublic) {
        if (this.stationModelPublic.removeElement(stationModelPublic)) {      
            stationModelPublic.setStationType(null);
        }
    }



}





