/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.type.TrainClass;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ChangeTrainClass extends AbstractTrainMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public TrainClass oldClass;
/**
 * Represents ...

 */

    public TrainClass newClass;

  ///////////////////////////////////////
  //access methods for attributes

    public TrainClass getOldClass() {
        return oldClass;
    }
    public void setOldClass(TrainClass oldClass) {
        this.oldClass = oldClass;
    }
    public TrainClass getNewClass() {
        return newClass;
    }
    public void setNewClass(TrainClass newClass) {
        this.newClass = newClass;
    }


}





