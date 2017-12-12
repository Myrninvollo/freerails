package jfreerails.move;

import jfreerails.misc.TrainPositionVector;
import jfreerails.world.train.TrainContainer;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class ChangeTrainPositionVectorMove {

	private final TrainContainer oldContainer;

	private final TrainContainer newContainer;

	private final TrainPositionVector oldPositionVector;

	private final TrainPositionVector newPositionVector;

	public TrainContainer getOldContainer() {
		return oldContainer;
	}

	public TrainContainer getNewContainer() {
		return newContainer;
	}

	public TrainPositionVector getOldPositionVector() {
		return oldPositionVector;
	}

	public TrainPositionVector getNewPositionVector() {
		return newPositionVector;
	}

	public ChangeTrainPositionVectorMove(
		TrainPositionVector vbefore,
		TrainPositionVector vafter,
		TrainContainer cbefore,
		TrainContainer cafter) {
		oldContainer = cbefore;
		newContainer = cafter;
		oldPositionVector = vbefore;
		newPositionVector = vafter;
	}
}