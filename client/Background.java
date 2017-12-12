
/*
* Background.java
*
* Created on 06 August 2001, 17:21
*/
package jfreerails.client;
import jfreerails.client.tileview.TileViewList;
import jfreerails.client.trackview.TrackPieceViewList;
import jfreerails.common.FreerailsMap.TerrainMap;
import jfreerails.common.FreerailsMap.TrackMap;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;

/** This object encapsulates the objects that make-up and paint the
* background of the map view.  At present it is composed of two layers:
* the terrain layer and the track layer.
*
*
* @author Luke Lindsay
* @version  1
*/


public class Background implements jfreerails.client.MapView {

    protected jfreerails.client.Background.TerrainLayer terrainLayer;

    protected jfreerails.client.Background.TrackLayer trackLayer;

    private Dimension tileSize = new Dimension( 30, 30 );

    private Dimension mapSize;
    
    /** This innner class represents the track on the map.
    */
    

    public class TrackLayer implements MapViewLayer {

        private TrackMap trackMap;

        private TrackPieceViewList trackPieceViewList;
        
        public Dimension getTileSize() {
            return tileSize;
        }
        
        /**
        */
        
        public TrackLayer( TrackMap trackMap, TrackPieceViewList trackPieceViewList ) {
            this.trackPieceViewList = trackPieceViewList;
            this.trackMap = trackMap;
        }
        
        /** Returns true if there is a tile at this map coordinate.
        * @param mapCoordinate Map coordinate in tiles.
        * @return True if there is a tile here.
        */
        
        public boolean aTileIsHere( Point mapCoordinate ) {
            if( null != trackMap.getTrackNode( mapCoordinate ) ) {
                return true;
            }
            else {
                return false;
            }
        }
        
        public void paintTile( Graphics g, Point tileMapCoordinate, Point screenOrigin ) {
            
            /*Since track tiles overlap the adjacent terrain tiles, we create a temporary Graphics
            *object that only lets us draw on the selected tile.
            */
            Graphics  tempG = g.create( screenOrigin.x, screenOrigin.y, tileSize.width, tileSize.height );
            try {
                
                /*Since track tiles overlap adjacent tiles by half a tile, we need to
                *draw each of the surrounding tiles aswell as the current tile.
                */
                for( int  x = -1;x < 2;x++ ) {
                    for( int  y = -1;y < 2;y++ ) {
                        Point  tempPoint = new Point( tileMapCoordinate.x + x, tileMapCoordinate.y + y );
                        if( ( tempPoint.x >= 0 ) && ( tempPoint.x < mapSize.width ) && ( tempPoint.y >= 0 ) && ( tempPoint.y < mapSize.height ) ) {
                            int  graphicsNumber = trackMap.getTrackGraphicNumber( tempPoint );
                            int  ruleNumber = trackMap.getTrackTypeNumber( tempPoint );
                            jfreerails.client.trackview.TrackPieceView  trackPieceView = trackPieceViewList.getTrackPieceView( ruleNumber );
                            java.awt.Image  icon = trackPieceView.getTrackPieceIcon( graphicsNumber );
                            int  drawX = x * tileSize.width - tileSize.width / 2;
                            int  drawY = y * tileSize.height - tileSize.height / 2;
                            if( null != icon ) {
                                tempG.drawImage( icon, drawX, drawY, null );
                            }
                        }
                    }
                }
            }
            catch( jfreerails.common.exception.FreerailsException fe ) {
                fe.printStackTrace();
            }
            tempG.dispose();
        }
    }
    
    /** This inner class represents the terrain  of the map.
    */
    

    public class TerrainLayer implements MapViewLayer {

        private TileViewList tiles;

        private TerrainMap terrainMap;
        
        public Dimension getTileSize() {
            return tileSize;
        }
        
        public TerrainLayer( TerrainMap terrainMap, TileViewList tiles ) {
            this.terrainMap = terrainMap;
            this.tiles = tiles;
        }
        
        public boolean aTileIsHere( Point mapCoordinate ) {
            return true;
        }
        
        public void paintTile( Graphics g, Point tileMapCoordinate, Point screenPosition ) {
            int  rgb = terrainMap.getTerrainTileType( tileMapCoordinate.x, tileMapCoordinate.y );
            if( ( tileMapCoordinate.x >= 0 ) && ( tileMapCoordinate.x < mapSize.width ) && ( tileMapCoordinate.y >= 0 ) && ( tileMapCoordinate.y < mapSize.height ) ) {
                tiles.getTileViewWithRGBValue( rgb ).renderTile( g, screenPosition, tileMapCoordinate, terrainMap );
            }
        }
    }
    
    public Dimension getMapSizeInTiles() {
        return mapSize;
    }
    
    public Dimension getTileSize() {
        return tileSize;
    }
    
    public void paintTile( Graphics g, Point tileMapCoordinate, Point screenOrigin ) {
        terrainLayer.paintTile( g, tileMapCoordinate, screenOrigin );
        trackLayer.paintTile( g, tileMapCoordinate, screenOrigin );
    }
    
    public void updateTiles( Rectangle tileMapRectangle ) {
        System.out.println( "method not yet implemented!" );
    }
    
    public void paintTiles( Graphics g, Point tileMapCoordinate, Rectangle tilesToPaint ) {
        System.out.println( "method not yet implemented!" );
    }
    
    public Dimension getMapSizeInPixels() {
        return new Dimension( tileSize.width * mapSize.width, tileSize.height * mapSize.height );
    }
    
    public void updateTile( Point tileMapCoordinate ) {
        System.out.println( "method not yet implemented!" );
    }
    
    /** Creates new Background
    * @param map The non-visual map object.
    * @param tiles An object holding a list of tileView objects, which
    * represent the visual properites of terrain tiles.
    * @param trackPieceViewList The object holding the set of objects representing the visual
    * properties of track-pieces.
    */
    
    public Background( jfreerails.common.FreerailsMap map, TileViewList tiles, TrackPieceViewList trackPieceViewList ) {
        trackLayer = new jfreerails.client.Background.TrackLayer( map.trackMap, trackPieceViewList );
        terrainLayer = new jfreerails.client.Background.TerrainLayer( map.terrainMap, tiles );
        mapSize = new Dimension( map.terrainMap.getWidth(), map.terrainMap.getHeight() );
    }
}
