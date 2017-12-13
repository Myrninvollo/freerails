/**
 * The tabbed panel that sits in the lower right hand corner of the screen
 */

/*
 * $Id: TrainsJTabPane.java,v 1.7 2004/02/27 01:50:41 lindsal Exp $
 */

package jfreerails.client.view;

import java.awt.Point;

import javax.swing.JTabbedPane;

import jfreerails.client.renderer.ViewLists;
import jfreerails.world.top.ReadOnlyWorld;

public class TrainsJTabPane extends JTabbedPane implements CursorEventListener {
    private TerrainInfoJPanel terrainInfoPanel;
    private StationInfoJPanel stationInfoPanel;
    //private TrainScheduleJPanel trainSchedulePanel;
    private ReadOnlyWorld world;
    private BuildJPane buildJPane;

    public TrainsJTabPane() {
	/* set up trainsJTabbedPane */
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
	terrainInfoPanel = new TerrainInfoJPanel();
	addTab("Terrain Info", terrainInfoPanel);

	stationInfoPanel = new StationInfoJPanel();
	addTab("Station Info", stationInfoPanel);
        
       // trainSchedulePanel = new TrainScheduleJPanel();
       // addTab("Train Schedule", trainSchedulePanel);
        
	buildJPane = new BuildJPane();
 	addTab("Build", buildJPane);
    }
    
    public void setMapCursor(MapCursor mapCursor){
		stationInfoPanel.setMapCursor(mapCursor);
    }
	
    public void setup(ReadOnlyWorld w,  ViewLists vl, ModelRoot modelRoot) {	
	world = w;
	terrainInfoPanel.setup(world, vl);
	stationInfoPanel.setup(modelRoot, null);
	//trainSchedulePanel.setup(world, vl);
 	buildJPane.setup(vl, modelRoot);
        
    //    trainSchedulePanel.displayFirst();
	//stationInfoPanel.display();
        	
    }

    private void updateTerrainInfo(CursorEvent e) {
        
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

