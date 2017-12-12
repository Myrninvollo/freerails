/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.massnoun;

import java.util.*;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class TravelledCargoBatch extends CargoBatch {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public GameTime pickupTime;

  ///////////////////////////////////////
  //access methods for attributes

    public GameTime getPickupTime() {
        return pickupTime;
    }
    public void setPickupTime(GameTime pickupTime) {
        this.pickupTime = pickupTime;
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





