package jfreerails.controller;

import java.util.Random;

import jfreerails.move.ChangeTrainPositionMove;
import jfreerails.world.common.FreerailsPathIterator;
import jfreerails.world.top.KEY;
import jfreerails.world.top.World;
import jfreerails.world.train.PathWalker;
import jfreerails.world.train.PathWalkerImpl;
import jfreerails.world.train.TrainModel;
import jfreerails.world.train.TrainPositionOnMap;

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
	

	public TrainMover(FreerailsPathIterator from, FreerailsPathIterator to, World world, int trainNo) {							
		
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
		TrainPositionOnMap initialPosition = TrainPositionOnMap.createInSameDirectionAsPath(fromPathWalker);
		train.setPosition(initialPosition);
		
	}
	
	public PathWalker getWalker(){
		return walker;	
	}
	
	
	public ChangeTrainPositionMove update(int distanceTravelled) {
		double distanceTravelledAsDouble = distanceTravelled;
		double  distance = distanceTravelledAsDouble * getTrainSpeed();					
		walker.stepForward(distance);
		return ChangeTrainPositionMove.generate(w, walker, trainNumber);		
	}

	
	public double getTrainSpeed() {
		TrainModel train = (TrainModel)w.get(KEY.TRAINS, trainNumber);
		int trainLength = train.getNumberOfWagons();
		//For now train speeds are hard coded.
		switch(trainLength){
			case 0:
				return 1;
			case 1:
				return 0.8;
			case 2:
				return 0.6;
			case 3:
				return 0.4;
			case 4:
				return 0.3;
			default:
				return 0.2;				
		}
	}	

}
