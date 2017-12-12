package jfreerails.world.train;

import java.util.Vector;

import jfreerails.world.type.WaitUntilFullOrders;
import jfreerails.world.station.StationModel;

final public class TrainRoute {

	private final StationModel stationModel;
	private final WaitUntilFullOrders waitUntilFullOrders;
	private final Vector schedule = new Vector();
	private final Vector schedule_1 = new Vector();

	public StationModel getStationModel() {
		return stationModel;
	}

	public WaitUntilFullOrders getWaitUntilFullOrders() {
		return waitUntilFullOrders;
	}

	public Vector getSchedule() {
		return schedule;
	}

	public void removeSchedule(Schedule schedule) {
		if (this.schedule.removeElement(schedule)) {
			//schedule.setTrainRoute(null);
		}
	}

	public Vector getSchedule_1() {
		return schedule_1;
	}

	public void removeSchedule_1(Schedule schedule) {
		if (this.schedule_1.removeElement(schedule)) {
			//schedule.setTrainRoute_1(null);
		}
	}
	
	public TrainRoute(){
		waitUntilFullOrders=null;
		stationModel=null;
	}
		

}