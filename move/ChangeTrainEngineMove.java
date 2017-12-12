/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.element.EngineModel;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ChangeTrainEngineMove extends AbstractTrainMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public EngineModel oldEngine;
/**
 * Represents ...

 */

    public EngineModel newEngine;

  ///////////////////////////////////////
  //access methods for attributes

    public EngineModel getOldEngine() {
        return oldEngine;
    }
    public void setOldEngine(EngineModel oldEngine) {
        this.oldEngine = oldEngine;
    }
    public EngineModel getNewEngine() {
        return newEngine;
    }
    public void setNewEngine(EngineModel newEngine) {
        this.newEngine = newEngine;
    }


}





