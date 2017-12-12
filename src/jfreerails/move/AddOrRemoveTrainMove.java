package jfreerails.move;

import jfreerails.world.top.World;
import jfreerails.world.train.TrainModel;

final public class AddOrRemoveTrainMove implements Move {

	private final int trainNumber;

	private final TrainModel train;

	public AddOrRemoveTrainMove(TrainModel t, int index) {
		this.train = t;
		this.trainNumber = index;
	}

	public MoveStatus tryDoMove(World w) {
		// TODO Auto-generated method stub
		return null;
	}

	public MoveStatus tryUndoMove(World w) {
		// TODO Auto-generated method stub
		return null;
	}

	public MoveStatus doMove(World w) {
		// TODO Auto-generated method stub
		return null;
	}

	public MoveStatus undoMove(World w) {
		// TODO Auto-generated method stub
		return null;
	}
}
