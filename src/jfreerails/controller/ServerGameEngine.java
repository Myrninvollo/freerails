package jfreerails.controller;

import java.util.ArrayList;
import java.util.Iterator;

import jfreerails.lib.GameModel;
import jfreerails.move.ChangeTrainPositionMove;
import jfreerails.world.World;


/**
 * @author Luke Lindsay 05-Nov-2002
 *
 */
public class ServerGameEngine implements GameModel {

	final World world;

	long lastTime = System.currentTimeMillis();

	ArrayList trainMovers = new ArrayList();

	public ServerGameEngine(World w) {
		this.world = w;

	}

	/**
	 * @see jfreerails.lib.GameModel#update()
	 */
	public void update() {
		long now = System.currentTimeMillis();
		long deltaTime = now - lastTime;
		int deltaDistance = (int) (0.15 * deltaTime);
		lastTime = now;
		ChangeTrainPositionMove m = null;

		Iterator i = trainMovers.iterator();

		while (i.hasNext()) {
			Object o = i.next();
			TrainMover trainMover = (TrainMover)o; 
			m = trainMover.update(deltaDistance);
			m.doMove(world.getTrainList());
		}

	}
	
	public void addTrainMover(TrainMover m){
		trainMovers.add(m);
		
	}

}
