/**
 * The tabbed panel that sits in the lower right hand corner of the screen
 */

/*
 * $Id: TrainsJTabPane.java,v 1.9 2004/03/13 15:46:46 lindsal Exp $
 */
package jfreerails.client.view;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import jfreerails.client.renderer.ViewLists;
import jfreerails.world.top.ReadOnlyWorld;


public class TrainsJTabPane extends JTabbedPane implements CursorEventListener {
    private TerrainInfoJPanel terrainInfoPanel;
    private StationInfoJPanel stationInfoPanel;
    private TrainListJPanel trainListPanel;
    private ReadOnlyWorld world;
    private BuildJPane buildJPane;

    public TrainsJTabPane() {
        /* set up trainsJTabbedPane */
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        terrainInfoPanel = new TerrainInfoJPanel();
		trainListPanel = new TrainListJPanel();
		trainListPanel.removeButtons();		
        URL terrainInfoIconUrl = getClass().getResource("/jfreerails/client/graphics/icons/terrain_info.png");
		ImageIcon terrainInfoIcon = new ImageIcon(terrainInfoIconUrl);
		
		URL stationInfoIconUrl = getClass().getResource("/jfreerails/client/graphics/icons/station_info.png");
		ImageIcon stationInfoIcon = new ImageIcon(stationInfoIconUrl);
				
		URL buildTrackIconUrl = getClass().getResource("/jfreerails/client/graphics/icons/track_new.png");
		ImageIcon buildTrackIcon = new ImageIcon(buildTrackIconUrl);
		
		URL trainListIconUrl = getClass().getResource("/jfreerails/client/graphics/icons/train_list.png");
		ImageIcon trainListIcon = new ImageIcon(trainListIconUrl);
		
		//Note titles set to null so only the icon appears at the top of the top.
        addTab(null, terrainInfoIcon, terrainInfoPanel, "Terrain Info");
        stationInfoPanel = new StationInfoJPanel();
        addTab(null, stationInfoIcon, stationInfoPanel, "Station Info");
        buildJPane = new BuildJPane();
		trainListPanel.setTrainViewHeight(20);
        addTab(null, buildTrackIcon, buildJPane, "Build Track");
		addTab(null, trainListIcon, trainListPanel, "Train List");		
    }

    public void setMapCursor(MapCursor mapCursor) {
        stationInfoPanel.setMapCursor(mapCursor);
    }

    public void setup(ReadOnlyWorld w, ViewLists vl, final ModelRoot modelRoot) {
        world = w;
        terrainInfoPanel.setup(world, vl);
        stationInfoPanel.setup(modelRoot, null);
        buildJPane.setup(vl, modelRoot);
		ActionListener showTrain = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int id = trainListPanel.getSelectedTrainID();
				modelRoot.getDialogueBoxController().showTrainOrders(id);
			}			
		};                
		trainListPanel.setShowTrainDetailsActionListener(showTrain);
		trainListPanel.setup(modelRoot, null);		
    }

    private void updateTerrainInfo(CursorEvent e) {
        Point p = e.newPosition;
        terrainInfoPanel.setTerrainType(world.getTile(p.x, p.y)
                                             .getTerrainTypeNumber());
    }

    /**
     * Implements {CursorEventListener#cursorOneTileMove}
     */
    public void cursorOneTileMove(CursorEvent e) {
        updateTerrainInfo(e);
    }

    /**
     * Implements {CursorEventListener#cursorJumped}
     */
    public void cursorJumped(CursorEvent e) {
        updateTerrainInfo(e);
    }

    /**
     * Implements {CursorEventListener#cursorKeyPressed}
     */
    public void cursorKeyPressed(CursorEvent e) {
        // do nothing
    }
}