package jfreerails.client.view;

import jfreerails.client.common.UserMessageLogger;
import jfreerails.client.renderer.ViewLists;
import jfreerails.controller.MoveChainFork;
import jfreerails.controller.ServerControlInterface;
import jfreerails.controller.StationBuilder;
import jfreerails.controller.TrackMoveProducer;
import jfreerails.controller.UntriedMoveReceiver;
import jfreerails.world.top.KEY;
import jfreerails.world.top.ReadOnlyWorld;


/**
 * Central point for accessing control models and common UI-independent services
 */
public final class ModelRoot {
    private TrackBuildModel trackBuildModel;
    private TrackMoveProducer trackMoveProducer;
    private StationBuildModel stationBuildModel;
    private  UntriedMoveReceiver moveReceiver;
    private  MoveChainFork moveFork;
    private MapCursor cursor = null;
	protected ServerControlModel serverControls = new ServerControlModel(null);
	private ReadOnlyWorld world;
    
    public ModelRoot() {        
    }    	

    public MoveChainFork getMoveChainFork() {
        return moveFork;
    }

    public UntriedMoveReceiver getReceiver() {
        return moveReceiver;
    }

    public void setCursor(MapCursor c) {
        cursor = c;
    }

    public MapCursor getCursor() {
        return cursor;
    }

    /**
     * The default message logger logs to stderr
     */
    private UserMessageLogger messageLogger = new UserMessageLogger() {
            public void println(String s) {
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
        if (world.size(KEY.TRACK_RULES) > 0) {
            trackMoveProducer = new TrackMoveProducer(world, receiver);
            trackBuildModel = new TrackBuildModel(trackMoveProducer, world, vl);
            stationBuildModel = new StationBuildModel(new StationBuilder(
                        receiver, world), world, vl);
        }
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
    
	/**
		* Not all clients may return a valid object - access to the server controls
		* is at the discretion of the server.
		*/
	   public ServerControlModel getServerControls() {
		   return serverControls;
	   }

	   public void setServerControls(ServerControlInterface controls) {
		   serverControls.setServerControlInterface(controls);
	   }
	public ReadOnlyWorld getWorld() {
		return world;
	}

	public void setWorld(ReadOnlyWorld world) {
		this.world = world;
	}

	public void setMoveFork(MoveChainFork moveFork) {
		this.moveFork = moveFork;
	}

	public void setMoveReceiver(UntriedMoveReceiver moveReceiver) {
		this.moveReceiver = moveReceiver;
	}

}