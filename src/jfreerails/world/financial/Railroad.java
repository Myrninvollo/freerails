package jfreerails.world.financial;

import jfreerails.world.StationList;
import jfreerails.world.TrackBuilderRules;
import jfreerails.world.train.TrainList;

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