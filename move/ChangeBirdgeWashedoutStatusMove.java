/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.element.TrackNode;
import jfreerails.element.*;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ChangeBirdgeWashedoutStatusMove extends AbstractRRmove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public TrackNode trackNode;
/**
 * Represents ...

 */

    public boolean oldwashoutvalue;
/**
 * Represents ...

 */

    public boolean newWashoutValue;

  ///////////////////////////////////////
  //access methods for attributes

    public TrackNode getTrackNode() {
        return trackNode;
    }
    public void setTrackNode(TrackNode trackNode) {
        this.trackNode = trackNode;
    }
    public boolean getOldwashoutvalue() {
        return oldwashoutvalue;
    }
    public void setOldwashoutvalue(boolean oldwashoutvalue) {
        this.oldwashoutvalue = oldwashoutvalue;
    }
    public boolean getNewWashoutValue() {
        return newWashoutValue;
    }
    public void setNewWashoutValue(boolean newWashoutValue) {
        this.newWashoutValue = newWashoutValue;
    }


}





