package jfreerails.controller;

import java.util.Random;

import jfreerails.move.ChangeTrainPositionMove;
import jfreerails.world.common.FreerailsPathIterator;
import jfreerails.world.top.KEY;
import jfreerails.world.top.World;
import jfreerails.world.train.PathWalker;
import jfreerails.world.train.PathWalkerImpl;
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
	
	final World w;
	
	double trainSpeed;

	public TrainMover(FreerailsPathIterator from, FreerailsPathIterator to, World world, int trainNo) {
					
		trainSpeed = rand.nextDouble()+1;			
		
		this.trainNumber=trainNo;
		this.w=world;
		
		setInitialTrainPosition(from);
		walker = new PathWalkerImpl(to);				

	}

	private void setInitialTrainPosition(
		FreerailsPathIterator from) {
		TrainModel train = (TrainModel)w.get(KEY.TRAINS, trainNumber);
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
		walker.stepForward(distance);
		return ChangeTrainPositionMove.generate(w, walker, trainNumber);		
	}



	/**
	 * Returns the trainSpeed.
	 * @return int
	 */
	public double getTrainSpeed() {
		return trainSpeed;
	}

	/**
	 * Sets the trainSpeed.
	 * @param trainSpeed The trainSpeed to set
	 */
	public void setTrainSpeed(double trainSpeed) {
		this.trainSpeed = trainSpeed;
	}

}
