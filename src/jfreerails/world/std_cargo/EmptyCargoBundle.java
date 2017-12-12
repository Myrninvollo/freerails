package jfreerails.world.std_cargo;

import java.util.Iterator;
import java.util.Vector;

import jfreerails.type.CargoType;
import jfreerails.world.cargo.CargoBundle;

public class EmptyCargoBundle implements CargoBundle {
	public Vector getCargoBatch() {
		return null;
	}

	public Iterator getCargoBatchIterator() {
		return null;
	}

	public CargoType getCargoType() {
		return null;
	}

	public int getTotalAmount() {
		return 0;
	}

	public int getTotalWeight() {
		return 0;
	}
}