/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.element.StationModelPrivate;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public abstract class AbstractStationMove extends AbstractRRmove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public StationModelPrivate station;

  ///////////////////////////////////////
  //access methods for attributes

    public StationModelPrivate getStation() {
        return station;
    }
    public void setStation(StationModelPrivate station) {
        this.station = station;
    }


}





