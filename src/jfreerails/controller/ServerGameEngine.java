package jfreerails.controller;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import jfreerails.move.ChangeTrainPositionMove;
import jfreerails.world.station.ProductionAtEngineShop;
import jfreerails.world.station.StationModel;
import jfreerails.world.top.KEY;
import jfreerails.world.top.World;

/**
 * @author Luke Lindsay 05-Nov-2002
 *
 */
public class ServerGameEngine implements GameModel {

	World world;
	
	TrainBuilder tb;

	long lastTime = System.currentTimeMillis();

	ArrayList trainMovers = new ArrayList();

	public ServerGameEngine(World w) {
		this.world = w;
		tb = new TrainBuilder(world, this);

	}

	
	public void update() {
		moveTrains();
		
		buildTrains();

	}

	/** Iterator over the stations  
	 * and build trains at any that have their production
	 * field set.
	 *
	 */
	private void buildTrains() {
		for(int i=0; i< world.size(KEY.STATIONS); i++){
			StationModel station = (StationModel)world.get(KEY.STATIONS, i);
			if(null !=station && null != station.getProduction()){
				ProductionAtEngineShop production = station.getProduction();
				Point p = new Point(station.x, station.y);
				tb.buildTrain(production.getEngineType(), production.getWagonTypes(), p);
				station.setProduction(null);	
			}			
		}
	}


	private void moveTrains() {
		long now = System.currentTimeMillis();
		long deltaTime = now - lastTime;
		int deltaDistance = (int) (0.15 * deltaTime);
		lastTime = now;
		ChangeTrainPositionMove m = null;
		
		Iterator i = trainMovers.iterator();
		
		while (i.hasNext()) {
			Object o = i.next();
			TrainMover trainMover = (TrainMover) o;
			m = trainMover.update(deltaDistance);
			m.doMove(world);
		}
	}

	public void addTrainMover(TrainMover m) {
		trainMovers.add(m);

	}

	public void saveGame() {

		try {

			System.out.print("Saving game..  ");
			FileOutputStream out = new FileOutputStream("freerails.sav");
			GZIPOutputStream zipout = new GZIPOutputStream(out);

			ObjectOutputStream objectOut = new ObjectOutputStream(zipout);
			objectOut.writeObject(trainMovers);
			objectOut.writeObject(getWorld());
			objectOut.flush();
			objectOut.close();

			System.out.println("done.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadGame() {

		try {

			System.out.print("Loading game..  ");
			FileInputStream in = new FileInputStream("freerails.sav");
			GZIPInputStream zipin = new GZIPInputStream(in);
			ObjectInputStream objectIn = new ObjectInputStream(zipin);

			this.trainMovers = (ArrayList) objectIn.readObject();

			this.world = (World) objectIn.readObject();
			
			System.out.println("done.");
			lastTime = System.currentTimeMillis();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public void newGame(World w) {

		this.world = w;

		trainMovers = new ArrayList();
		lastTime = System.currentTimeMillis();
	}

	/**
	 * Returns the world.
	 * @return World
	 */
	public World getWorld() {
		return world;
	}

}
