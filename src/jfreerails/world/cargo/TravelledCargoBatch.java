package jfreerails.world.cargo;

import jfreerails.world.misc.GameTime;

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