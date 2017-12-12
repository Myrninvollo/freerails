
/*
* FreeRailsMap.java
*
* Created on 08 August 2001, 14:01
*/
package jfreerails.common;
import jfreerails.common.trackmodel.TrackNode;
import java.awt.*;

/** This class encapsulates and provides access to the
* objects that compose the map.  E.g. the terrain map,
* the track map, and the train map.
*
* @author Luke Lindsay
* @version 1.0
*/


public class FreerailsMap {

    public TerrainMap terrainMap;

    public TrackMap trackMap;
    
    /** This inner class encapsulates the terrain map.  It is
    * composed of a 2D array of terrain tiles, and provides
    * methods to access them.
    */
    

    public class TrackMap extends java.lang.Object {

        private java.awt.Rectangle trackNodeMapRect;

        private TrackNode[][] trackNodeMap;
        
        public int getTrackGraphicNumber( Point point ) {
            TrackNode  trackNode = getTrackNode( point );
            if( trackNode == null ) {
                return 0;
            }
            else {
                return trackNode.getTrackGraphicNumber();
            }
        }
        
        public int toggleDoubleTrack( Point point ) {
            
            //TODO add code
            return 0;
        }
        
        public int upgradeBuilding( Point point, int buildingType ) {
            
            //TODO add code
            return 0;
        }
        
        public int removeBuilding( Point point ) {
            
            //TODO add code
            return 0;
        }
        
        public void removeTrackNode( Point point ) throws jfreerails.common.exception.FreerailsException {
            if( trackNodeMap[ point.x ][ point.y ] != null ) {
                trackNodeMap[ point.x ][ point.y ] = null;
            }
            else {
                throw new jfreerails.common.exception.FreerailsException( "Tried to remove a track node at" + point.x + ", " + point.x + " but there isn't one there!" );
            }
        }
        
        public TrackNode getTrackNode( Point point ) {
            if( trackNodeMapRect.contains( point ) ) {
                TrackNode  trackNode = trackNodeMap[ point.x ][ point.y ];
                return trackNode;
            }
            else {
                
                //  System.out.println("Tried to get track node from a point outside the map");
                return null;
            }
        }
        
        public int getTrackTypeNumber( Point point ) {
            TrackNode  trackNode = getTrackNode( point );
            if( trackNode == null ) {
                return 0;
            }
            else {
                return trackNode.getTrackTypeNumber();
            }
        }
        
        public int removeTrack( Point point, jfreerails.common.OneTileMoveVector direction ) {
            
            //TODO add code
            return 0;
        }
        
        public void addTrackNode( Point point, TrackNode trackNode ) throws jfreerails.common.exception.FreerailsException {
            if( trackNodeMap[ point.x ][ point.y ] == null ) {
                trackNodeMap[ point.x ][ point.y ] = trackNode;
            }
            else {
                throw new jfreerails.common.exception.FreerailsException( "Tried to add a track node at" + point.x + ", " + point.x + " but there is already one there" );
            }
        }
        
        /** Creates new TrackMap */
        
        public TrackMap( int x, int y ) {
            trackNodeMap = new TrackNode[ x ][ y ];
            trackNodeMapRect = new java.awt.Rectangle( 0, 0, x, y );
        }
        
        public int getHeight() {
            return trackNodeMap[ 0 ].length;
        }
        
        public int buildBuilding( Point point, int buildingType ) {
            
            //TODO add code
            return 0;
        }
        
        public int getWidth() {
            return trackNodeMap.length;
        }
    }
    
    /** This inner class encapsulates the track-map.  It is
    * composed of a 2D array of tracknodes and provides
    * methods to acesss individual nodes.   Tiles where
    * no track has been build are represented by a null
    * reference.
    */
    

    public class TerrainMap extends java.lang.Object {

        private TerrainTileTypesList terrainTileTypesList;

        private TileModel[][] tileMap;

        private java.awt.image.BufferedImage mapBufferedImage;

        private java.awt.Rectangle mapRect;
        
        /** Creates new TerrainMap */
        
        public TerrainMap( java.awt.image.BufferedImage map, TerrainTileTypesList terrainTileTypesList ) {
            this.mapBufferedImage = map;
            this.terrainTileTypesList = terrainTileTypesList;
        }
        
        public int getTerrainTileType( int x, int y ) {
            if( mapRect.contains( x, y ) ) {
                return mapBufferedImage.getRGB( x, y );
            }
            else {
                
                //   System.out.println("Tried to get tile from a point outside the map");
                return 0;
            }
        }
        
        public TerrainMap( java.net.URL url, TerrainTileTypesList terrainTileTypesList ) throws jfreerails.common.exception.FreerailsException {
            try {
                System.out.println( "\nLoading map " + url );
                java.awt.image.BufferedImage  map = javax.imageio.ImageIO.read( url );
                this.mapBufferedImage = map;
                this.mapRect = new java.awt.Rectangle( 0, 0, map.getWidth(), map.getHeight() );
                this.terrainTileTypesList = terrainTileTypesList;
                this.tileMap = new TileModel[ map.getWidth() ][ map.getHeight() ];
            }
            catch( Exception e ) {
                throw new jfreerails.common.exception.FreerailsException( "Error loading map: " + url );
            }
        }
        
        public String getTerrainTypeName( int x, int y ) {
            return terrainTileTypesList.getTerrainName( mapBufferedImage.getRGB( x, y ) );
        }
        
        public int getWidth() {
            return mapBufferedImage.getWidth();
        }
        
        public int getHeight() {
            return mapBufferedImage.getHeight();
        }
    }
    
    /** Creates new FreeRailsMap */
    
    public FreerailsMap() {
        
    }
    
    public FreerailsMap( java.net.URL url, TerrainTileTypesList terrainTileTypesList ) throws jfreerails.common.exception.FreerailsException {
        terrainMap = new jfreerails.common.FreerailsMap.TerrainMap( url, terrainTileTypesList );
        trackMap = new jfreerails.common.FreerailsMap.TrackMap( terrainMap.getWidth(), terrainMap.getHeight() );
    }
}
