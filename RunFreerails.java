
/*
* RunFreerails.java
*
* Created on 04 July 2001, 03:54
*/
package jfreerails;
import jfreerails.lib.ImageSplitter;
import java.net.URL;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class RunFreerails extends java.lang.Object {
    
    /** Creates new RunFreerails */
    
    public RunFreerails() {
        
    }
    
    public static void main( String args[] ) {
        String  map_name;
        jfreerails.lib.TerminalIO  my_io = new jfreerails.lib.TerminalIO();
        map_name = "testmap.png";
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
            jfreerails.common.TileFactory  tileFactory = new jfreerails.common.TileFactory( tiles_xml_url, terrain );
            
            //Get tile images from the picture as specified by the xml file.        
            jfreerails.client.tileview.TileViewList  tiles = tileFactory.getTileViewList();
            java.awt.Point  tilesSize = tileFactory.getTileSize();
            jfreerails.common.TerrainTileTypesList  terrainTileTypesList = tileFactory.getTerrainTileTypesList();
            
            //Load the track graphics and create the trackset
            URL  track_tiles_url = RunFreerails.class.getResource( "/jfreerails/data/track.png" );
            URL  track_xml_url = RunFreerails.class.getResource( "/jfreerails/data/track.xml" );
            ImageSplitter  track = new ImageSplitter( track_tiles_url );
            jfreerails.common.trackmodel.TrackSetFactory  trackSetFactory = new jfreerails.common.trackmodel.TrackSetFactory( track_xml_url );
            
            //Get the track graphics and track rules.
            jfreerails.client.trackview.TrackPieceViewList  trackPieceViewList = trackSetFactory.getTrackViewList( track );
            jfreerails.common.trackmodel.TrackRuleList  trackRules = trackSetFactory.getTrackRuleList();
            jfreerails.common.trackmodel.TrackRule  trackRule = trackRules.getTrackRule( 0 );
            jfreerails.client.trackview.TrackPieceView  trackPieceView = trackPieceViewList.getTrackPieceView( 0 );
            
            //Load the terrain map
            URL  map_url = RunFreerails.class.getResource( "/jfreerails/data/" + mapName );
            jfreerails.common.FreerailsMap  map = new jfreerails.common.FreerailsMap( map_url, terrainTileTypesList );
            
            //Create the object that controls building track.
            jfreerails.common.trackmodel.TrackBuilder  trackBuilder = new jfreerails.common.trackmodel.TrackBuilder( map, trackRule );
            
            //Create the visual components to add to the client window.
            jfreerails.client.MapView  mapView = new jfreerails.client.Background( map, tiles, trackPieceViewList );
            jfreerails.client.MapViewJComponent  mapViewJComponent = new jfreerails.client.MapViewJComponentConcrete( mapView, trackBuilder );
            jfreerails.client.BuildMenu  buildMenu = new jfreerails.client.BuildMenu( trackRules, trackBuilder );
            
            //Add the mapview and build menu to an extended JFrame.
            jfreerails.client.ClientJFrame  client = new jfreerails.client.ClientJFrame( mapViewJComponent, buildMenu );
            client.show();
        }
        catch( jfreerails.common.exception.FreerailsException fe ) {
            fe.printStackTrace();
        }
    }
}
