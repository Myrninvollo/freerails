/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.type.StationImprovement;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class AddOrRemoveStationImprovementMove extends AbstractStationMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public boolean create;
/**
 * Represents ...

 */

    public StationImprovement improvement;

  ///////////////////////////////////////
  //access methods for attributes

    public boolean getCreate() {
        return create;
    }
    public void setCreate(boolean create) {
        this.create = create;
    }
    public StationImprovement getImprovement() {
        return improvement;
    }
    public void setImprovement(StationImprovement improvement) {
        this.improvement = improvement;
    }


}





