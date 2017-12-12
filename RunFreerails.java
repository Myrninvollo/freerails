	
/*
 *  RunFreerails.java
 *
 *  Created on 04 July 2001, 03:54
 */
package jfreerails;
import java.net.URL;

import javax.swing.JFrame;
import jfreerails.client.ClientJFrame;
import jfreerails.client.tileview.TileViewList;
import jfreerails.client.view.map.BackgroundMapView;
import jfreerails.client.view.map.MapViewJComponent;
import jfreerails.client.view.map.MapViewJComponentConcrete;
import jfreerails.client.view.map.MapViewJComponentContainer;
import jfreerails.client.view.map.NewMapView;
import jfreerails.client.view.map.ZoomedOutMapView;
import jfreerails.lib.ImageSplitter;
import jfreerails.move.receiver.MoveChainFork;
import jfreerails.move.receiver.MoveReceiver;
import jfreerails.move.receiver.TrackMoveExecutor;
import jfreerails.move.source.TrackMoveProducer;
import jfreerails.world.World;
import jfreerails.world.WorldImpl;

/**
 *@author     Luke Lindsay
 *@created    09 October 2001
 *@version
 */

public class RunFreerails extends java.lang.Object {

    /**
     *  Creates new RunFreerails
     */

    public RunFreerails() { }


    /**
     *  Description of the Method
     *
     *@param  args  Description of Parameter
     */

    public static void main(String[] args) {
        String map_name;
        jfreerails.lib.TerminalIO my_io = new jfreerails.lib.TerminalIO();
        map_name = "testmap.png";
        while (!map_name.equalsIgnoreCase("exit")) {
            createClient(map_name);
            map_name = my_io.my_read_line("\nEnter map filename or type exit (Note, maps are now png files - try testmap.png)");
        }
        System.out.println("GoodBye..");
        System.exit(0);
    }


    /**
     *  Description of the Method
     *
     *@param  mapName  The filename of the map to load.
     */

    private static void createClient(String mapName) {
        try {

            //Load the picture containing the tile graphics.
            URL tiles_url = RunFreerails.class.getResource("/jfreerails/data/freerails_tiles.PNG");
            ImageSplitter terrain = new ImageSplitter(tiles_url);

            //Load the xml file specifying terrain types.
            URL tiles_xml_url = RunFreerails.class.getResource("/jfreerails/data/Tiles.xml");
            jfreerails.factory.TileFactory tileFactory = new jfreerails.factory.TileFactoryImpl(tiles_xml_url, terrain);

            //Get tile images from the picture as specified by the xml file.
            TileViewList tiles = tileFactory.getTileViewList();
            java.awt.Point tilesSize = tileFactory.getTileSize();
            jfreerails.list.TerrainTileTypesList terrainTileTypesList = tileFactory.getTerrainTileTypesList();

            //Load the track graphics and create the trackset
            URL track_tiles_url = RunFreerails.class.getResource("/jfreerails/data/track.png");
            URL track_xml_url = RunFreerails.class.getResource("/jfreerails/data/track.xml");
            ImageSplitter track = new ImageSplitter(track_tiles_url);
            jfreerails.factory.TrackSetFactory trackSetFactory = new jfreerails.factory.TrackSetFactoryImpl(track_xml_url);

            //Get the track graphics and track rules.
            jfreerails.client.trackview.TrackPieceViewList trackPieceViewList = trackSetFactory.getTrackViewList(track);
            jfreerails.list.TrackRuleList trackRules = trackSetFactory.getTrackRuleList();
            jfreerails.client.trackview.TrackPieceView trackPieceView = trackPieceViewList.getTrackPieceView(0);

            //Load the terrain map
            URL map_url = RunFreerails.class.getResource("/jfreerails/data/" + mapName);
            jfreerails.map.FreerailsMap map = new jfreerails.map.FreerailsMapImpl(map_url, terrainTileTypesList, trackRules);
            
            World world=new WorldImpl( tileFactory, trackSetFactory, map);

            //Create the object that controls building track.
            MoveReceiver trackMoveExecutor=new TrackMoveExecutor(map.getTrackMap());
            
            //Create the visual components to add to the client window.
			//NewMapView mapView = new BackgroundMapView(map, tiles, trackPieceViewList);
			
			
			NewMapView mainMap, overviewMap;
			overviewMap=new ZoomedOutMapView(map);
			mainMap=new BackgroundMapView(map, tiles, trackPieceViewList);
			
			MoveReceiver moveFork=new MoveChainFork(trackMoveExecutor, (MoveReceiver)overviewMap);
			
			TrackMoveProducer trackBuilder = new TrackMoveProducer(map.getTrackMap(), moveFork);
			
			
			
			//NewMapView mapView = 
			
            //MapViewJComponent mapViewJComponent = new MapViewJComponentConcrete(mapView, trackBuilder);
            MapViewJComponent mapViewJComponent = new MapViewJComponentConcrete(mainMap, trackBuilder);
            
            MapViewJComponentContainer mainMapContainer=MapViewJComponentContainer.generateMainmapComponent(mapViewJComponent);
			MapViewJComponentContainer overviewMapContainer=MapViewJComponentContainer.generateOverviewMapComponent(overviewMap, mainMapContainer);
            
            
           // MapViewJComponent mapViewJComponent = new MapViewJComponentConcrete(mainMapContainer, trackBuilder);
            jfreerails.client.BuildMenu buildMenu = new jfreerails.client.BuildMenu(trackRules, trackBuilder);

            //Add the mapview and build menu to an extended JFrame.
            //jfreerails.client.ClientJFrame client = new jfreerails.client.SimpleClientJFrame(mapViewJComponent, buildMenu);
            
            
            JFrame client=new  ClientJFrame( mainMapContainer,  overviewMapContainer, buildMenu);
            
            client.show();
        } catch (jfreerails.common.exception.FreerailsException fe) {
            fe.printStackTrace();
        }
    }
}
