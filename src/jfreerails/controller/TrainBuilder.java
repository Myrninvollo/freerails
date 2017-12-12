package jfreerails.controller;

import java.awt.Point;

import jfreerails.misc.NewFlatTrackExplorer;
import jfreerails.misc.OldFlatTrackExplorer;
import jfreerails.misc.TrainPathFinder;
import jfreerails.world.World;
import jfreerails.world.misc.FreerailsPathIterator;
import jfreerails.world.track.NullTrackType;
import jfreerails.world.track.PositionOnTrack;
import jfreerails.world.track.RandomPathFinder;
import jfreerails.world.track.TrackTileMap;
import jfreerails.world.train.EngineModel;
import jfreerails.world.train.TrainList;
import jfreerails.world.train.TrainModel;
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

	public void buildTrain(Point p) {
		TrackTileMap trackMap = world.getMap();
		if (NullTrackType.NULL_TRACK_TYPE_RULE_NUMBER != trackMap.getTrackTypeNumber(p)) {

			//Add train to train list.

			TrainModel train = new TrainModel(new EngineModel(), null);

			TrainList trainList = world.getTrainList();

			trainList.addTrain(train);

			int trainNumber = trainList.size() - 1;

			TrainMover trainMover = new TrainMover(getPathToFollow(p), getPathToFollow(p), trainList, trainNumber);

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

	public FreerailsPathIterator getPathToFollow(Point p) {
		boolean useNewFlatTrackExplorer = true;

		if (useNewFlatTrackExplorer) {

			PositionOnTrack pot = NewFlatTrackExplorer.getPossiblePositions(world.getMap(), p)[0];

			NewFlatTrackExplorer explorer = new NewFlatTrackExplorer(world.getMap(), pot);

			FreerailsPathIterator it;

			//it = new TrainPathIterator(new TrainPathIntIterator(explorer));
			it = new TrainPathIterator(new TrainPathFinder(explorer));
			//it = new TrainPathFinder(explorer);
			return it;
		} else {

			OldFlatTrackExplorer explorer = new OldFlatTrackExplorer(world.getMap(), p);

			FreerailsPathIterator it = new RandomPathFinder(explorer);

			return it;
		}
	}
}
