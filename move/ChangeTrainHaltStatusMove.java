/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ChangeTrainHaltStatusMove extends AbstractTrainMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public boolean newHalt;
/**
 * Represents ...

 */

    public boolean oldHalt;

  ///////////////////////////////////////
  //access methods for attributes

    public boolean getNewHalt() {
        return newHalt;
    }
    public void setNewHalt(boolean newHalt) {
        this.newHalt = newHalt;
    }
    public boolean getOldHalt() {
        return oldHalt;
    }
    public void setOldHalt(boolean oldHalt) {
        this.oldHalt = oldHalt;
    }


}





