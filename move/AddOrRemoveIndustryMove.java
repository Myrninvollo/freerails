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
import jfreerails.type.IndustryType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class AddOrRemoveIndustryMove extends AbstractRRmove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public int id;
/**
 * Represents ...

 */

    public Point location;
/**
 * Represents ...

 */

    public IndustryType industry;
/**
 * Represents ...

 */

    public IndustryType oldType;

  ///////////////////////////////////////
  //access methods for attributes

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Point getLocation() {
        return location;
    }
    public void setLocation(Point location) {
        this.location = location;
    }
    public IndustryType getIndustry() {
        return industry;
    }
    public void setIndustry(IndustryType industry) {
        this.industry = industry;
    }
    public IndustryType getOldType() {
        return oldType;
    }
    public void setOldType(IndustryType oldType) {
        this.oldType = oldType;
    }


}





