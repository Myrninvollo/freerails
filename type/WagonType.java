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
import jfreerails.element.WagonModel;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class WagonType extends RailwayVehicleType {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private String wagonTypeName;

   ///////////////////////////////////////
   // associations

    public WagonModel wagonModel;


  ///////////////////////////////////////
  //access methods for attributes

    public String getWagonTypeName() {
        return wagonTypeName;
    }
    public void setWagonTypeName(String wagonTypeName) {
        this.wagonTypeName = wagonTypeName;
    }

   ///////////////////////////////////////
   // access methods for associations


    public WagonModel getWagonModel() {
        return wagonModel;
    }
    public void setWagonModel(WagonModel wagonModel) {
        if (this.wagonModel != wagonModel) {
            if (this.wagonModel != null) 
                this.wagonModel.removeWagonType(this);     
            this.wagonModel = wagonModel;
            if (wagonModel != null)
                wagonModel.addWagonType(this);  
        }
    } 



}





