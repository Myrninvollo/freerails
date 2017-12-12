package jfreerails.move;

import java.util.Vector;

import jfreerails.world.cargo.CompositeCargoBundle;

/**
 *
 *
 *
 * @author lindsal
 */

final public class AddOrRemoveCargoMove {

	private final CompositeCargoBundle oldBundle;

	private final CompositeCargoBundle newBundle;

	private final Vector cargoBatch;

	public CompositeCargoBundle getOldBundle() {
		return oldBundle;
	}

	public CompositeCargoBundle getNewBundle() {
		return newBundle;
	}

	public Vector getCargoBatch() {
		return cargoBatch;
	}

	public AddOrRemoveCargoMove(CompositeCargoBundle old, CompositeCargoBundle newC, Vector batch) {
		oldBundle = old;
		newBundle = newC;
		cargoBatch = (Vector) batch.clone();
	}
}