package jfreerails.world.train;

import java.util.Iterator;

import jfreerails.type.CargoType;
import jfreerails.world.cargo.CargoBundle;
import jfreerails.world.cargo.CargoContainer;

abstract public class RailwayVehicleModelPrivate implements CargoContainer {
	
	public boolean canHold(CargoType type) {
		return false;
	}

	public CargoBundle getBundle(CargoType type) {
		return null;
	}

	public boolean addBundle(CargoBundle bundle) {
		return false;
	}

	public boolean removeBundle(CargoBundle bundle) {
		return false;
	}
	public Iterator getBundleIterator() {
		return null;
	}
}