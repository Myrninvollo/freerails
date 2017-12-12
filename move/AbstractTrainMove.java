/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.element.TrainModelPublic;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public abstract class AbstractTrainMove extends AbstractRRmove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public TrainModelPublic train;

  ///////////////////////////////////////
  //access methods for attributes

    public TrainModelPublic getTrain() {
        return train;
    }
    public void setTrain(TrainModelPublic train) {
        this.train = train;
    }


}





