package jfreerails.world.cargo;


/**
 *
 *
 *
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