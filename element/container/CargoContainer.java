package jfreerails.element.container;

import java.util.*;

import jfreerails.type.CargoType;
import jfreerails.element.CargoBundle;
import java.util.Iterator;

/**
 * Defining operations expected of ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public interface CargoContainer {

  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @param type ...
 * @return A boolean with ...
 */

    public boolean canHold(CargoType type);
/**
 * Does ...
 * 
 * @param bundle ...
 */

    public void addCargo(CargoBundle bundle);
/**
 * Does ...
 * 
 * @param bundle ...
 * @return A CargoBundle with ...
 */

    public CargoBundle removeCargo(CargoBundle bundle);
/**
 * Does ...
 * 
 * @return A Iterator with ...
 */

    public Iterator getCargoBatchIterator();
/**
 * Does ...
 * 
 * @param bundle ...
 * @return A boolean with ...
 */

    public boolean contains(CargoBundle bundle);

}

