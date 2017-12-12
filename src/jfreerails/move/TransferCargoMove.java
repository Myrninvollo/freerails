
package jfreerails.move;


import jfreerails.world.std_cargo.CompositeCargoBundle;
import jfreerails.world.cargo.CargoContainer;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class TransferCargoMove {

    private final CompositeCargoBundle bundle;

    private final CargoContainer oldContainer;


    private final CargoContainer newContainer;

    public CompositeCargoBundle getBundle() {
        return bundle;
    }

    public CargoContainer getOldContainer() {
        return oldContainer;
    }

    public CargoContainer getNewContainer() {
        return newContainer;
    }
    
    public TransferCargoMove(CompositeCargoBundle b, CargoContainer before, CargoContainer after){
    	bundle=b;
    	oldContainer=before;
    	newContainer=after;
    	
    }
}





