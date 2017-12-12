/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import java.awt.Point;
import jfreerails.type.EngineType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class AddOrRemoveTrainMove extends AbstractRRmove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public Point location;
/**
 * Represents ...

 */

    public EngineType engine;

  ///////////////////////////////////////
  //access methods for attributes

    public Point getLocation() {
        return location;
    }
    public void setLocation(Point location) {
        this.location = location;
    }
    public EngineType getEngine() {
        return engine;
    }
    public void setEngine(EngineType engine) {
        this.engine = engine;
    }


}





