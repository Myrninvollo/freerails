package jfreerails.move;

import jfreerails.world.train.EngineModel;

/**
 *
 *
 *
 * @author lindsal
 */

final public class ChangeTrainEngineMove {

	private final EngineModel oldEngine;

	private final EngineModel newEngine;

	public EngineModel getOldEngine() {
		return oldEngine;
	}

	public EngineModel getNewEngine() {
		return newEngine;
	}

	public ChangeTrainEngineMove(
		EngineModel before,
		EngineModel after) {
		oldEngine = before;
		newEngine = after;
	}
}