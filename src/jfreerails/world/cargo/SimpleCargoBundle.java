package jfreerails.world.cargo;

import java.util.Iterator;
import java.util.Vector;


public class SimpleCargoBundle implements CargoBundle {
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

	private final CargoBatch batch;
	private final int quantity;

	public SimpleCargoBundle(CargoBatch b, int amount) {
		batch = b;
		quantity = amount;

	}
}