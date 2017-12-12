package jfreerails.client.view;

import jfreerails.client.common.UserMessageLogger;
import jfreerails.client.renderer.ViewLists;
import jfreerails.world.top.ReadOnlyWorld;
import jfreerails.controller.StationBuilder;
import jfreerails.controller.TrackMoveProducer;
import jfreerails.controller.UntriedMoveReceiver;

/**
 * Central point for accessing control models and common UI-independent services
 */
public final class ModelRoot {

    private TrackBuildModel trackBuildModel;
    private TrackMoveProducer trackMoveProducer;
    private StationBuildModel stationBuildModel;

    /**
     * The default message logger logs to stderr
     */
    private UserMessageLogger messageLogger = new UserMessageLogger() {
	public void println (String s) {
	    System.err.println(s);
	}
    };
    
    /**
     * Updates the ModelRoot with those properties which are dependent upon the
     * world model.
     * Call this when the world model is changed (e.g. new map is loaded)
     */
    public void setWorld(ReadOnlyWorld world, UntriedMoveReceiver receiver,
    ViewLists vl) {
	trackMoveProducer = new TrackMoveProducer(world, receiver);
	trackBuildModel = new TrackBuildModel(trackMoveProducer, world, vl);
	stationBuildModel = new StationBuildModel(new StationBuilder(receiver,
	world), world, vl);
    }

    public TrackBuildModel getTrackBuildModel() {
	return trackBuildModel;
    }

    public StationBuildModel getStationBuildModel() {
	return stationBuildModel;
    }

    public TrackMoveProducer getTrackMoveProducer() {
	return trackMoveProducer;
    }

    public UserMessageLogger getUserMessageLogger() {
	return messageLogger;
    }

    public void setUserMessageLogger(UserMessageLogger m) {
	messageLogger = m;
    }
}
