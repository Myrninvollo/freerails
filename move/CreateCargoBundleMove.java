/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.element.CargoBundle;
import jfreerails.element.container.CargoContainer;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class CreateCargoBundleMove extends AbstractRRmove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public CargoBundle bundle;
/**
 * Represents ...

 */

    public CargoContainer oldContainer;

  ///////////////////////////////////////
  //access methods for attributes

    public CargoBundle getBundle() {
        return bundle;
    }
    public void setBundle(CargoBundle bundle) {
        this.bundle = bundle;
    }
    public CargoContainer getOldContainer() {
        return oldContainer;
    }
    public void setOldContainer(CargoContainer oldContainer) {
        this.oldContainer = oldContainer;
    }


}





