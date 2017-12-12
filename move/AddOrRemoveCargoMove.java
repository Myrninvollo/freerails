/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.element.CargoBundle;
import jfreerails.massnoun.CargoBatch;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class AddOrRemoveCargoMove extends AbstractRRmove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public CargoBundle oldBundle;
/**
 * Represents ...

 */

    public CargoBundle newBundle;

   ///////////////////////////////////////
   // associations

    public Vector cargoBatch = new Vector();


  ///////////////////////////////////////
  //access methods for attributes

    public CargoBundle getOldBundle() {
        return oldBundle;
    }
    public void setOldBundle(CargoBundle oldBundle) {
        this.oldBundle = oldBundle;
    }
    public CargoBundle getNewBundle() {
        return newBundle;
    }
    public void setNewBundle(CargoBundle newBundle) {
        this.newBundle = newBundle;
    }

   ///////////////////////////////////////
   // access methods for associations


    public Vector getCargoBatch() {
        return cargoBatch;
    }
    public void addCargoBatch(CargoBatch cargoBatch) {
        if (! this.cargoBatch.contains(cargoBatch)) {     
            this.cargoBatch.addElement(cargoBatch);  
        }
    }
    public void removeCargoBatch(CargoBatch cargoBatch) {    
        this.cargoBatch.removeElement(cargoBatch);        
    }



}





