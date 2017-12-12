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
import jfreerails.type.TerrainType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ChangeTerrainTypeMove extends AbstractMapMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public Point location;
/**
 * Represents ...

 */

    public TerrainType oldType;
/**
 * Represents ...

 */

    public TerrainType newType;

  ///////////////////////////////////////
  //access methods for attributes

    public Point getLocation() {
        return location;
    }
    public void setLocation(Point location) {
        this.location = location;
    }
    public TerrainType getOldType() {
        return oldType;
    }
    public void setOldType(TerrainType oldType) {
        this.oldType = oldType;
    }
    public TerrainType getNewType() {
        return newType;
    }
    public void setNewType(TerrainType newType) {
        this.newType = newType;
    }


}





