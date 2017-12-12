package jfreerails.world.cargo;

import java.util.Iterator;
import java.util.Vector;


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