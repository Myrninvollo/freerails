/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.type.TrainType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ChangeTrainType extends AbstractTrainMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public TrainType oldTrainType;
/**
 * Represents ...

 */

    public TrainType newTrainType;

  ///////////////////////////////////////
  //access methods for attributes

    public TrainType getOldTrainType() {
        return oldTrainType;
    }
    public void setOldTrainType(TrainType oldTrainType) {
        this.oldTrainType = oldTrainType;
    }
    public TrainType getNewTrainType() {
        return newTrainType;
    }
    public void setNewTrainType(TrainType newTrainType) {
        this.newTrainType = newTrainType;
    }


}





