package jfreerails.world.std_cargo;

import jfreerails.misc.GameTime;
import jfreerails.type.CargoType;

final public class TravelledCargoBatch extends AbstCargoBatch {

	private final GameTime pickupTime;
	final private CargoType type;

	public GameTime getPickupTime() {
		return pickupTime;
	}

	public boolean hasTravelled() {
		return false;
	}

	public TravelledCargoBatch(CargoType ct, GameTime t) {

		super();
		pickupTime = t;
		type=ct;
	}

}