
/*
* TerrainMapSquareTiledView.java
*
* Created on 01 August 2001, 06:39
*/
package jfreerails.client;
import java.awt.*;
import java.awt.Dimension;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class TerrainMapSquareTiledView implements jfreerails.client.MapView {

    private Dimension tileSize;

    private jfreerails.map.TerrainMap terrainMap;

    private jfreerails.client.tileview.TileView[] terrainTileViewList;
    
    public void updateTile( Point tileMapCoordinate ) {
        
    }
    
    public boolean aTileIsHere( Point mapCoordinate ) {
        if( ( mapCoordinate.x >= 0 ) && ( mapCoordinate.x < terrainMap.getWidth() ) && ( mapCoordinate.y >= 0 ) && ( mapCoordinate.y < terrainMap.getHeight() ) ) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public Dimension getMapSizeInPixels() {
        return new Dimension( terrainMap.getWidth() * tileSize.width, terrainMap.getWidth() * tileSize.height );
    }
    
    public void updateTiles( java.awt.Rectangle tileMapRectangle ) {
        
    }
    
    public void paintTiles( java.awt.Graphics g, Point tileMapCoordinate, java.awt.Rectangle screenOrigin ) {
        
    }
    
    public Dimension getMapSizeInTiles() {
        return new Dimension( terrainMap.getWidth(), terrainMap.getWidth() );
    }
    
    public Dimension getTileSize() {
        return tileSize;
    }
    
    public void paintTile( java.awt.Graphics g, Point tileMapCoordinate, Point screenOrigin ) {
       throw new java.lang.UnsupportedOperationException("Method not yet implemented");
    }
    public void paintTile( java.awt.Graphics g, Point tileMapCoordinate ){
        throw new java.lang.UnsupportedOperationException("Method not yet implemented");  
    }
    
    /** Creates new TerrainMapSquareTiledView */
    
    public TerrainMapSquareTiledView( jfreerails.map.TerrainMap terrainMap, jfreerails.client.tileview.TileView[] terrainTileViewList ) {
        this.terrainMap = terrainMap;
        this.terrainTileViewList = terrainTileViewList;
        this.tileSize = new Dimension();
    }
         public  void paintRect(java.awt.Graphics g)
         {
         throw new java.lang.UnsupportedOperationException("Method not yet implemented");  
         }
         
         public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint) {
             throw new java.lang.UnsupportedOperationException("Method not yet implemented");  
         }
         
         public void paintTile(Graphics g, int x, int y) {
         }         
       
         
}
