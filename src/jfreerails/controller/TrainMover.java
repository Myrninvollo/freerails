package jfreerails.controller;

import java.util.Random;

import jfreerails.move.ChangeTrainPositionMove;
import jfreerails.server.FreerailsServerSerializable;
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
public class TrainMover  implements FreerailsServerSerializable{

	//final FreerailsPathIterator path;
	
	static Random rand = new Random(System.currentTimeMillis());

	final PathWalker walker;

	final int trainNumber;
	
	final TrainList trainList;
	
	int trainSpeed;

	double remainder; 

	public TrainMover(FreerailsPathIterator from, FreerailsPathIterator to, TrainList trains, int trainNo) {
		
		
		
		trainSpeed = rand.nextInt(100)+50;
		
		System.out.println("Train speed = "+trainSpeed);
		
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
		double  distance = distanceTravelled * trainSpeed;
		distance = distance/100+remainder;
		
		int intDistance =(int)distance;
		remainder = distance - intDistance;		
		walker.stepForward(intDistance);
		return ChangeTrainPositionMove.generate(trainList, walker, trainNumber);		
	}



	/**
	 * Returns the trainSpeed.
	 * @return int
	 */
	public int getTrainSpeed() {
		return trainSpeed;
	}

	/**
	 * Sets the trainSpeed.
	 * @param trainSpeed The trainSpeed to set
	 */
	public void setTrainSpeed(int trainSpeed) {
		this.trainSpeed = trainSpeed;
	}

}
