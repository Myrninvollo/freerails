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
import jfreerails.type.TrackRule;
import jfreerails.type.StationType;
import java.lang.String;
import jfreerails.element.*;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class AddOrRemoveStationMove extends AbstractRRmove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public TrackNode trackNode;
/**
 * Represents ...

 */

    public TrackRule oldType;
/**
 * Represents ...

 */

    public StationType newType;
/**
 * Represents ...

 */

    public String name;

  ///////////////////////////////////////
  //access methods for attributes

    public TrackNode getTrackNode() {
        return trackNode;
    }
    public void setTrackNode(TrackNode trackNode) {
        this.trackNode = trackNode;
    }
    public TrackRule getOldType() {
        return oldType;
    }
    public void setOldType(TrackRule oldType) {
        this.oldType = oldType;
    }
    public StationType getNewType() {
        return newType;
    }
    public void setNewType(StationType newType) {
        this.newType = newType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}





