package jfreerails.move.source;

import java.awt.Point;

import jfreerails.world.World;
import jfreerails.world.flat.FlatTrackExplorer;
import jfreerails.world.std_track.NullTrackType;
import jfreerails.world.std_track.TrackTileMap;
import jfreerails.world.track.RandomPathFinder;
import jfreerails.world.train.FreerailsPathIterator;
import jfreerails.world.train.PathWalker;
import jfreerails.world.train.PathWalkerImpl;
import jfreerails.world.train.Snake;
import jfreerails.world.train.SnakeImpl;
import jfreerails.world.train.TrainModelPublic;

/**
 * @author Luke Lindsay 13-Oct-2002
 *
 */
public class TrainBuilder {

	World world;

	public TrainBuilder(World w) {
		world = w;
	}

	public void buildTrain(Point p) {
		TrackTileMap trackMap = world.getMap();
		if (NullTrackType.NULL_TRACK_TYPE_RULE_NUMBER != trackMap.getTrackTypeNumber(p)) {

			TrainModelPublic train = new TrainModelPublic();
			
			FlatTrackExplorer explorer = new FlatTrackExplorer(world.getMap(), p);
			
			FreerailsPathIterator it = new RandomPathFinder(explorer);
			
			PathWalker pw = new PathWalkerImpl(it);
			
			pw.stepForward(400); 	
			
			Snake s = new SnakeImpl(pw);
			
			train.setPosition(s);

			/*
			int x = p.x * 30 + 15;
			int y = p.y * 30 + 15;

			s.moveHead(x, y);
			s.moveTail(x - 400, y - 400);
			*/

			world.getTrainList().addTrain(train);
			System.out.println("Build train at: "+p.x+", "+p.y);
		} else {
			System.out.println("No track here so cannot build train");
		}
	}
}
