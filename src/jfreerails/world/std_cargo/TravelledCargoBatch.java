package jfreerails.world.std_cargo;

import jfreerails.world.type.CargoType;
import jfreerails.world.GameTime;

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