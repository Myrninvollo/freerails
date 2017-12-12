package jfreerails.world.std_cargo;

import jfreerails.type.CargoType;
import jfreerails.world.std_cargo.AbstCargoBatch;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class FreshCargoBatch extends AbstCargoBatch {

	public boolean hasTravelled() {
		return false;
	}

	final private CargoType type;
	
	
	public FreshCargoBatch(CargoType ct){
		type=ct;
	}
}