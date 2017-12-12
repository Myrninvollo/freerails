/**
 * The tabbed panel that sits in the lower right hand corner of the screen
 */

/*
 * $Id: TrainsJTabPane.java,v 1.2 2003/08/07 23:05:46 lindsal Exp $
 */

package jfreerails.client.view;

import java.awt.Point;

import javax.swing.JTabbedPane;

import jfreerails.client.renderer.ViewLists;
import jfreerails.world.top.World;

public class TrainsJTabPane extends JTabbedPane implements CursorEventListener {
    private TerrainInfoJPanel terrainInfoPanel;
    private StationInfoJPanel stationInfoPanel;
    private TrainScheduleJPanel trainSchedulePanel;

    private World world;

    public TrainsJTabPane() {
	/* set up trainsJTabbedPane */
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
	terrainInfoPanel = new TerrainInfoJPanel();
	addTab("Terrain Info", terrainInfoPanel);

	stationInfoPanel = new StationInfoJPanel();
	addTab("Station Info", stationInfoPanel);
        
        trainSchedulePanel = new TrainScheduleJPanel();
        addTab("Train Schedule", trainSchedulePanel);
        
    }
	
    public void setup(FreerailsCursor cursor, World w, ViewLists vl) {
	terrainInfoPanel.setup(w, vl);
	stationInfoPanel.setup(w, vl);
	trainSchedulePanel.setup(w, vl);
        
        trainSchedulePanel.displayFirst();
	stationInfoPanel.display();
        
	cursor.addCursorEventListener(this);
	world = w;

    }

    private void updateTerrainInfo(CursorEvent e) {
        System.out.println("Updating terrain Info");
	Point p = e.newPosition;
	terrainInfoPanel.setTerrainType(world.getTile(p.x,
		    p.y).getTerrainTypeNumber());
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

