
/*
* SquareTileBackgroundPainter.java
*
* Created on 31 July 2001, 16:36
*/
package jfreerails.client;
import java.awt.*;
import java.awt.Dimension;

/** This class stores a buffer containing the terrain and track layers
* of current visible rectangle of the map.  It is responsible of painting
* these layers and updating the buffer when the map scrolls or tiles
* are updated.
*
* @author Luke Lindsay
* @version 1.0
*/


public class SquareTileBackgroundPainter extends jfreerails.client.TiledBackgroundPainter {

    private MapView mapView;
    
    /** Creates new SquareTileBackgroundPainter
    * @param mapView Object that has access to the map and paints individual
    * tiles.
    */
    
    public SquareTileBackgroundPainter( MapView mapView ) {
        this.mapView = mapView;
    }
    
    public synchronized void paintRect( java.awt.Graphics g, int x, int y, int width, int height ) {
        Dimension  tileSize = mapView.getTileSize();
        Dimension  mapSize = mapView.getMapSizeInTiles();
        int  tileWidth = tileSize.width;
        int  tileHeight = tileSize.height;
        int  mapXOffset = bufferRect.x / tileWidth;
        int  mapYOffset = bufferRect.y / tileHeight;
        int  xOffset = bufferRect.x % tileWidth;
        int  yOffset = bufferRect.y % tileHeight;
        Point  bufferOrigin = bufferRect.getLocation();
        for( int  i = x / tileWidth;i < ( ( x + width ) / tileWidth + 2 );i++ ) {
            for( int  j = y / tileHeight;j < ( ( y + height ) / tileHeight + 2 );j++ ) {
                if( ( i >= 0 ) && ( j >= 0 ) && ( i < mapSize.width ) && ( j < mapSize.height ) ) {
                    
                    //mapView.paintTile( g, mapXOffset + i, mapYOffset + j, tileWidth * i - xOffset, tileHeight * j - yOffset );
                    Point  mapPosition = new Point( mapXOffset + i, mapYOffset + j );
                    Point  screenPosition = new Point( tileWidth * i - xOffset, tileHeight * j - yOffset );
                    mapView.paintTile( g, mapPosition, screenPosition );
                }
            }
        }
    }
    
    /** Updates a tile of the backgound buffer.  It needs to
    * be called when a visilbe tile's properties change,
    * e.g. track is built on the tile.
    * @param mapCoord map coordinate of in tiles.
    */
    
    public void updateTile( Point mapCoord ) {
        Dimension  tileSize = mapView.getTileSize();
        Dimension  mapSize = mapView.getMapSizeInTiles();
        java.awt.Rectangle  rectToResfresh = new java.awt.Rectangle( ( mapCoord.x - 1 ) * tileSize.width, ( mapCoord.y - 1 ) * tileSize.height, tileSize.width * 3, tileSize.height * 3 );
        if( bufferRect.intersects( rectToResfresh ) ) {
            int  tileWidth = tileSize.width;
            int  tileHeight = tileSize.height;
            int  mapXOffset = bufferRect.x / tileWidth;
            int  mapYOffset = bufferRect.y / tileHeight;
            int  xOffset = bufferRect.x % tileWidth;
            int  yOffset = bufferRect.y % tileHeight;
            for( int  x = mapCoord.x - 1;x < mapCoord.x + 2;x++ ) {
                for( int  y = mapCoord.y - 1;y < mapCoord.y + 2;y++ ) {
                    Point  tileMapCoordinate = new Point( x, y );
                    Point  screenPosition = new Point( ( x - mapXOffset ) * tileWidth - xOffset, ( y - mapYOffset ) * tileWidth - yOffset );
                    mapView.paintTile( bg, tileMapCoordinate, screenPosition );
                }
            }
        }
    }
    
    public synchronized void paintBufferRectangle( int x, int y, int width, int height ) {
        paintRect( bg, x, y, width, height );
    }
}
