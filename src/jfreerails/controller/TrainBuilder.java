package jfreerails.controller;

import java.awt.Point;



import jfreerails.controller.pathfinder.FlatTrackExplorer;
import jfreerails.controller.pathfinder.TrainPathFinder;
import jfreerails.world.common.FreerailsPathIterator;
import jfreerails.world.common.PositionOnTrack;
import jfreerails.world.top.KEY;
import jfreerails.world.top.NonNullElements;
import jfreerails.world.top.World;
import jfreerails.world.top.WorldIterator;
import jfreerails.world.track.FreerailsTile;
import jfreerails.world.track.NullTrackType;
import jfreerails.world.track.TrackRule;
import jfreerails.world.train.Schedule;
import jfreerails.world.train.TrainModel;
import jfreerails.world.train.TrainOrdersModel;
import jfreerails.world.train.TrainPathIterator;

/**
 * @author Luke Lindsay 13-Oct-2002
 *
 */
public class TrainBuilder {

	World world;
	ServerGameEngine gameEngine;

	public TrainBuilder(World w, ServerGameEngine gm) {
		world = w;
		gameEngine = gm;
	}

	public void buildTrain(int engineType, int[]wagons, Point p) {
		
		FreerailsTile tile = (FreerailsTile)world.getTile(p.x, p.y); 
		
		TrackRule tr = tile.getTrackRule();
	
		if (NullTrackType.NULL_TRACK_TYPE_RULE_NUMBER
			!= tr.getRuleNumber()) {

			//Add train to train list.
			WorldIterator wi = new NonNullElements(KEY.STATIONS, world);
			
			Schedule s = new Schedule();
			for(int i = 0; i< 5 ;i++){
				if(!wi.next()){
					wi.reset();
					wi.next();
				}
				TrainOrdersModel orders = new TrainOrdersModel(wi.getIndex(), new int[i], false);
				s.setOrder(i, orders);
			}									

			TrainModel train = new TrainModel(engineType, wagons, null, s);
			System.out.println("Build train with engine type: "+engineType+" and wagons: "+wagons.toString());
			

			world.add(KEY.TRAINS, train);

			int trainNumber = world.size(KEY.TRAINS) - 1;

			TrainMover trainMover =
				new TrainMover(
					getPathToFollow(p, world, trainNumber),
					getPathToFollow(p, world, trainNumber),
					world,
					trainNumber);

			gameEngine.addTrainMover(trainMover);
			//FreerailsPathIterator it = getPath(p);
			//PathWalker pw = new PathWalkerImpl(it);

			//pw.stepForward(400); 	

			//TrainPosition s = TrainPosition.createInSameDirectionAsPath(pw);

			/*
			int x = p.x * 30 + 15;
			int y = p.y * 30 + 15;
			
			s.moveHead(x, y);
			s.moveTail(x - 400, y - 400);
			*/

			System.out.println("Build train at: " + p.x + ", " + p.y);
		} else {
			System.out.println("No track here so cannot build train");
		}
	}

	public FreerailsPathIterator getPathToFollow(Point p, World w, int trainNumber) {

		PositionOnTrack pot =
			FlatTrackExplorer.getPossiblePositions(
				world,
				p)[0];

		//NewFlatTrackExplorer explorer =new NewFlatTrackExplorer(world.getMap(), pot);
		FlatTrackExplorer explorer = new FlatTrackExplorer(pot, world);

		FreerailsPathIterator it;

		it = new TrainPathIterator(new TrainPathFinder(explorer, w, trainNumber));

		return it;

	}
}
