package jfreerails.world.std_cargo;

import java.util.Iterator;

import jfreerails.world.type.CargoCompartmentType;
import jfreerails.world.type.CargoType;
import jfreerails.world.cargo.CargoBundle;
import jfreerails.world.cargo.CargoContainer;

/**
 * Represents ...
 * 
 * @see OtherClasses
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