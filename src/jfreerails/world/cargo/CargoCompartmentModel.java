package jfreerails.world.cargo;

import java.util.Iterator;


/**
 *
 *
 *
 * @author lindsal
 */

public class CargoCompartmentModel implements CargoContainer {

	public CargoCompartmentType cargoCompartmentType;

	public CargoCompartmentType getCargoCompartmentType() {
		return cargoCompartmentType;
	}


	public boolean canHold(CargoType type) {
		return false;
	}

	public void addCargo(CompositeCargoBundle bundle) {
	}

	public CompositeCargoBundle removeCargo(CompositeCargoBundle bundle) {
		return null;
	}

	public boolean contains(CompositeCargoBundle bundle) {
		return false;
	}
	public CargoBundle getBundle(CargoType type) {

		return null;
	}

	public Iterator getBundleIterator() {
		return null;
	}

	public boolean addBundle(CargoBundle bundle) {
		return false;
	}

	public boolean removeBundle(CargoBundle bundle) {
		return false;
	}

}