package jfreerails.world.cargo;

import java.util.Iterator;
import java.util.Vector;


/**
 *
 *
 *
 * @author lindsal
 */

final public class CompositeCargoBundle implements CargoBundle {


    private final SimpleCargoBundle[] simpleBundles;


	public CompositeCargoBundle(SimpleCargoBundle[] scb){
		simpleBundles=scb;
	}

    public CargoType getCargoType() {
        return null;
    }

  public Vector getCargoBatch() {
        return null;
   }

    public Iterator getCargoBatchIterator() {
        return null;
    }

    public int getTotalAmount() {
    	return 0;
    }

    public int getTotalWeight() {
    	return 0;
    }
}





