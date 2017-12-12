package jfreerails.world.financial;

import jfreerails.controller.TrackBuilderRules;
import jfreerails.list.StationList;
import jfreerails.list.TrainList;

public class Railroad {
	public TrackBuilderRules getTrackBuilderRules() {
		return trackBuilderRules;
	}

	public void setTrackBuilderRules(TrackBuilderRules trackBuilderRules) {
		this.trackBuilderRules = trackBuilderRules;
	}

	public StationList getStationList() {
		return stationList;
	}

	public void setStationList(StationList stationList) {
		this.stationList = stationList;
	}

	public TrainList getTrainList() {
		return trainList;
	}

	public void setTrainList(TrainList trainList) {
		this.trainList = trainList;
	}

	public TrackBuilderRules trackBuilderRules;
	public StationList stationList;
	public TrainList trainList;
}