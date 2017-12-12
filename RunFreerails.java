
/*
* RunFreerails.java
*
* Created on 04 July 2001, 03:54
*/
package jfreerails;

/**
*
* @author  Luke Lindsay
* @version 
*/
import java.awt.Point;
import java.net.URL;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import jfreerails.common.TerrainMap;
import jfreerails.client.IconMap;
import jfreerails.client.MainviewJComponentFactory;
import jfreerails.client.tileview.TileView;
import jfreerails.common.TileFactory;
import jfreerails.client.ClientJFrame;
import jfreerails.lib.TerminalIO;
import jfreerails.lib.ImageSplitter;
import jfreerails.common.exception.FreerailsException;
import jfreerails.client.trackview.TrackPieceView;
import jfreerails.client.tileview.TileRenderer;
import jfreerails.client.tileview.TileEventListener;
import jfreerails.client.MessengerBoy;
import jfreerails.common.OneTileMoveVector;
import jfreerails.common.trackmodel.*;
import jfreerails.common.IntPoint;
import jfreerails.client.trackview.TrackPieceViewList;
import jfreerails.common.trackmodel.TrackRuleList;
import jfreerails.client.BuildMenu;
import jfreerails.common.TerrainTileTypesList;


public class RunFreerails extends java.lang.Object {
    
    /** Creates new RunFreerails */
    
    public RunFreerails() {
        
    }
    
    /**
    * @param args the command line arguments
    */
    
    public static void main( String args[] ) {
        String  map_name;
        TerminalIO  my_io = new TerminalIO();
        map_name = "testmap.png"; //my_io.my_read_line("Enter map filename (e.g. test.map)");
        while( !map_name.equalsIgnoreCase( "exit" ) ) {
            createClient( map_name );
            map_name = my_io.my_read_line( "\nEnter map filename or type exit (Note, maps are now png files - try testmap.png)" );
        }
        System.out.println( "GoodBye.." );
        System.exit( 0 );
    }
    
    private static void createClient( String mapName ) {
        try {
            
            //Load the picture containing the tile graphics.
            URL  tiles_url = RunFreerails.class.getResource( "/jfreerails/data/freerails_tiles.PNG" );
            ImageSplitter  terrain = new ImageSplitter( tiles_url );
            
            //Load the xml file specifying terrain types.
            URL  tiles_xml_url = RunFreerails.class.getResource( "/jfreerails/data/Tiles.xml" );
            TileFactory  tileFactory = new TileFactory( tiles_xml_url );
            
            //Get tile images from the picture as specified by the xml file.        
            HashMap  tiles = tileFactory.getTileViewHashMap( terrain );
            Point  tilesSize = tileFactory.getTileSize();
            TerrainTileTypesList  terrainTileTypesList = tileFactory.getTerrainTileTypesList();
            
            //Load the terrain map
            URL  map_url = RunFreerails.class.getResource( "/jfreerails/data/" + mapName );
            TerrainMap  terrain_map = new TerrainMap( map_url, terrainTileTypesList );
            
            //Create a track map the same size as the terrain map.
            TrackMap  trackMap = new TrackMap( terrain_map.getWidth(), terrain_map.getHeight() );
            
            //Load the track graphics and create the trackset
            URL  track_tiles_url = RunFreerails.class.getResource( "/jfreerails/data/track.png" );
            URL  track_xml_url = RunFreerails.class.getResource( "/jfreerails/data/track.xml" );
            ImageSplitter  track = new ImageSplitter( track_tiles_url );
            TrackSetFactory  trackSetFactory = new TrackSetFactory( track_xml_url );
            
            //Get the track graphics and track rules.
            TrackPieceViewList  trackPieceViewList = trackSetFactory.getTrackViewList( track );
            TrackRuleList  trackRules = trackSetFactory.getTrackRuleList();
            TrackRule  trackRule = trackRules.getTrackRule( 0 );
            TrackPieceView  trackPieceView = trackPieceViewList.getTrackPieceView( 0 );
            
            //Create the object that controls building track.
            TrackBuilder  trackBuilder = new TrackBuilder( trackMap, trackRule, terrain_map );
            
            //Create table model that controls which terrain and track graphics are drawn
            IconMap  iconMap = new IconMap( terrain_map, tiles, trackMap, trackPieceViewList );
            
            //Prepare a JTable with a custom cell renderer to display the array of tile images.
            MainviewJComponentFactory  mainviewJComponentFactory = new MainviewJComponentFactory( tilesSize );
            JTable  mainviewJTable = mainviewJComponentFactory.newMainviewJTable( iconMap, trackBuilder );
            BuildMenu  buildMenu = new BuildMenu( trackRules, trackBuilder );
            
            //Add this extended JTable to a JFrame and display it.
            ClientJFrame  client = new ClientJFrame( mainviewJTable, buildMenu );
            client.show();
        }
        catch( FreerailsException fe ) {
            fe.printStackTrace();
        }
    }
}
