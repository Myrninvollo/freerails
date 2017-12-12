
/*
* TerrainMapSquareTiledView.java
*
* Created on 01 August 2001, 06:39
*/
package jfreerails.client;
import java.awt.Point;
import java.awt.Dimension;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class TerrainMapSquareTiledView implements jfreerails.client.MapView {

    private jfreerails.common.FreerailsMap.TerrainMap terrainMap;

    private jfreerails.client.tileview.TileView[] terrainTileViewList;

    private Dimension tileSize;
    
    public void paintTile( java.awt.Graphics g, Point tileMapCoordinate, Point screenOrigin ) {
        
    }
    
    public void paintTiles( java.awt.Graphics g, Point tileMapCoordinate, java.awt.Rectangle screenOrigin ) {
        
    }
    
    public boolean aTileIsHere( Point mapCoordinate ) {
        if( ( mapCoordinate.x >= 0 ) && ( mapCoordinate.x < terrainMap.getWidth() ) && ( mapCoordinate.y >= 0 ) && ( mapCoordinate.y < terrainMap.getHeight() ) ) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public Dimension getTileSize() {
        return tileSize;
    }
    
    /** Creates new TerrainMapSquareTiledView */
    
    public TerrainMapSquareTiledView( jfreerails.common.FreerailsMap.TerrainMap terrainMap, jfreerails.client.tileview.TileView[] terrainTileViewList ) {
        this.terrainMap = terrainMap;
        this.terrainTileViewList = terrainTileViewList;
        this.tileSize = new Dimension();
    }
    
    public void updateTiles( java.awt.Rectangle tileMapRectangle ) {
        
    }
    
    public Dimension getMapSizeInPixels() {
        return new Dimension( terrainMap.getWidth() * tileSize.width, terrainMap.getWidth() * tileSize.height );
    }
    
    public Dimension getMapSizeInTiles() {
        return new Dimension( terrainMap.getWidth(), terrainMap.getWidth() );
    }
    
    public void updateTile( Point tileMapCoordinate ) {
        
    }
}
