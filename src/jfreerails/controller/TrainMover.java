package jfreerails.controller;

import jfreerails.move.ChangeTrainPositionMove;
import jfreerails.world.misc.FreerailsPathIterator;
import jfreerails.world.train.PathWalker;
import jfreerails.world.train.PathWalkerImpl;
import jfreerails.world.train.TrainList;
import jfreerails.world.train.TrainModel;
import jfreerails.world.train.TrainPosition;

/**
 * @author Luke Lindsay 27-Oct-2002
 *
 */
public class TrainMover {

	//final FreerailsPathIterator path;

	final PathWalker walker;

	final int trainNumber;
	
	final TrainList trainList;

	

	public TrainMover(FreerailsPathIterator from, FreerailsPathIterator to, TrainList trains, int trainNo) {
		
		this.trainNumber=trainNo;
		this.trainList=trains;
		
		setInitialTrainPosition(from);
		walker = new PathWalkerImpl(to);
		
		//Set current train position using from path and length of train.

	}

	private void setInitialTrainPosition(
		FreerailsPathIterator from) {
		TrainModel train = trainList.getTrain(trainNumber);
		int trainLength = train.getLength();
		PathWalker fromPathWalker = new PathWalkerImpl(from);
		fromPathWalker.stepForward(trainLength);
		TrainPosition initialPosition = TrainPosition.createInSameDirectionAsPath(fromPathWalker);
		train.setPosition(initialPosition);
		
	}
	
	public PathWalker getWalker(){
		return walker;	
	}
	
	
	public ChangeTrainPositionMove update(int distanceTravelled) {
		walker.stepForward(distanceTravelled);
		
		return ChangeTrainPositionMove.generate(trainList, walker, trainNumber);		
	}



}
