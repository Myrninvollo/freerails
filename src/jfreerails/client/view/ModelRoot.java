package jfreerails.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import jfreerails.client.common.UserMessageLogger;
import jfreerails.client.renderer.ViewLists;
import jfreerails.controller.MoveChainFork;
import jfreerails.controller.ServerControlInterface;
import jfreerails.controller.StationBuilder;
import jfreerails.controller.TrackMoveProducer;
import jfreerails.controller.UntriedMoveReceiver;
import jfreerails.world.player.FreerailsPrincipal;
import jfreerails.world.top.ReadOnlyWorld;
import jfreerails.world.top.SKEY;


/**
 * Central point for accessing control models and common UI-independent services
 */
public final class ModelRoot {
    private TrackBuildModel trackBuildModel;
    private TrackMoveProducer trackMoveProducer;
    private StationBuildModel stationBuildModel;
    private UntriedMoveReceiver moveReceiver;
    private MoveChainFork moveFork;
    private MapCursor cursor = null;
    private DialogueBoxController dialogueBoxController = null;
    private BuildTrainDialogAction buildTrainDialogAction = new BuildTrainDialogAction();
    private FreerailsPrincipal playerPrincipal;
    private ViewLists viewLists;
    private ArrayList listeners = new ArrayList();
    protected ServerControlModel serverControls = new ServerControlModel(null);
    private ReadOnlyWorld world;

    public void addModelRootListener(ModelRootListener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }

    /**
     * @return the principal corresponding to the player this client is acting
     * for
     */
    public FreerailsPrincipal getPlayerPrincipal() {
		if(null == playerPrincipal) throw new NullPointerException();
        return playerPrincipal;
    }

    /**
     * set the principal corresponding to the player this client is acting for
     */
    public void setPlayerPrincipal(FreerailsPrincipal p) {
        assert p != null;
        playerPrincipal = p;
    }

    private class BuildTrainDialogAction extends AbstractAction {
        public BuildTrainDialogAction() {
            super("Build Train");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
            putValue(SHORT_DESCRIPTION, "Build a new train");
        }

        public void actionPerformed(ActionEvent e) {
            if (dialogueBoxController != null) {
                dialogueBoxController.showSelectEngine();
            }
        }
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
            public void showMessage(String msg) {
              println(msg);
            }
            public void hideMessage() {}
        };

    /**
     * Updates the ModelRoot with those properties which are dependent upon the
     * world model.
     * Call this when the world model is changed (e.g. new map is loaded)
     */
    public void setWorld(ReadOnlyWorld world, UntriedMoveReceiver receiver,
        ViewLists vl) {
        viewLists = vl;
        this.world = world;

        if(null == world) throw new NullPointerException();

        if (world.size(SKEY.TRACK_RULES) > 0) {
            assert playerPrincipal != null;
            trackMoveProducer = new TrackMoveProducer(world, receiver,
                    playerPrincipal);
            trackBuildModel = new TrackBuildModel(trackMoveProducer, world, vl);
            stationBuildModel = new StationBuildModel(new StationBuilder(
                        receiver, world, playerPrincipal), world, vl);
        }

        synchronized (listeners) {
            for (int i = 0; i < listeners.size(); i++) {
                ((ModelRootListener)listeners.get(i)).modelRootChanged();
            }
        }
    }

    public TrackBuildModel getTrackBuildModel() {
        return trackBuildModel;
    }

    public StationBuildModel getStationBuildModel() {
        return stationBuildModel;
    }

    public Action getBuildTrainDialogAction() {
        return buildTrainDialogAction;
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

    public void setDialogueBoxController(
        DialogueBoxController dialogueBoxController) {
        this.dialogueBoxController = dialogueBoxController;
    }

    public ViewLists getViewLists() {
        return viewLists;
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

    public void setMoveFork(MoveChainFork moveFork) {
        this.moveFork = moveFork;
    }

    public void setMoveReceiver(UntriedMoveReceiver moveReceiver) {
        this.moveReceiver = moveReceiver;
    }
}