/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.element.container.TrainContainer;
import jfreerails.misc.TrainPositionVector;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ChangeTrainPositionVectorMove extends AbstractTrainMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public TrainContainer oldContainer;
/**
 * Represents ...

 */

    public TrainContainer newContainer;
/**
 * Represents ...

 */

    public TrainPositionVector oldPositionVector;
/**
 * Represents ...

 */

    public TrainPositionVector newPositionVector;

  ///////////////////////////////////////
  //access methods for attributes

    public TrainContainer getOldContainer() {
        return oldContainer;
    }
    public void setOldContainer(TrainContainer oldContainer) {
        this.oldContainer = oldContainer;
    }
    public TrainContainer getNewContainer() {
        return newContainer;
    }
    public void setNewContainer(TrainContainer newContainer) {
        this.newContainer = newContainer;
    }
    public TrainPositionVector getOldPositionVector() {
        return oldPositionVector;
    }
    public void setOldPositionVector(TrainPositionVector oldPositionVector) {
        this.oldPositionVector = oldPositionVector;
    }
    public TrainPositionVector getNewPositionVector() {
        return newPositionVector;
    }
    public void setNewPositionVector(TrainPositionVector newPositionVector) {
        this.newPositionVector = newPositionVector;
    }


}





