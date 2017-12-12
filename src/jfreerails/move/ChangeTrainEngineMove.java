package jfreerails.move;

import jfreerails.world.train.EngineModelPublic;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class ChangeTrainEngineMove {

	private final EngineModelPublic oldEngine;

	private final EngineModelPublic newEngine;

	public EngineModelPublic getOldEngine() {
		return oldEngine;
	}

	public EngineModelPublic getNewEngine() {
		return newEngine;
	}

	public ChangeTrainEngineMove(
		EngineModelPublic before,
		EngineModelPublic after) {
		oldEngine = before;
		newEngine = after;
	}
}