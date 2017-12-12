/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.type.StationType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ChangeStationTypeMove extends AbstractStationMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public StationType oldType;
/**
 * Represents ...

 */

    public StationType newType;

  ///////////////////////////////////////
  //access methods for attributes

    public StationType getOldType() {
        return oldType;
    }
    public void setOldType(StationType oldType) {
        this.oldType = oldType;
    }
    public StationType getNewType() {
        return newType;
    }
    public void setNewType(StationType newType) {
        this.newType = newType;
    }


}





