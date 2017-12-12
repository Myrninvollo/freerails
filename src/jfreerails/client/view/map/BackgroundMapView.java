
/*
 *  BackgroundMapView.java
 *
 *  Created on 06 August 2001, 17:21
 */
package jfreerails.client.view.map;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import jfreerails.client.tileview.TileViewList;
import jfreerails.client.trackview.TrackPieceViewList;
import jfreerails.world.std_track.TrackAndTerrainTileMap;
import jfreerails.world.std_track.TrackTileMap;
import jfreerails.world.terrain.TerrainMap;


/** This class encapsulates the objects that make-up and paint the background
 * of the map view. At present it is composed of two layers: the terrain layer
 * and the track layer.
 *
 * @author Luke Lindsay
 * @created 21 September 2001
 * @version 1
 */

final public class BackgroundMapView implements NewMapView {
    
    /** The terrain layer.
     */
    protected TerrainLayer terrainLayer;
    
    /** The track layer.
     */
    protected TrackLayer trackLayer;
    
    private Dimension tileSize = new Dimension(30, 30);
    
    private Dimension mapSize;
    
    /*Used to avoid having to create a new rectangle for each call to
     *the paint methods.
     */
    private Rectangle clipRectangle=new Rectangle();
    
    
    /**
     *  This innner class represents a view of the track on the map.
     *
     *@author     Luke Lindsay
     *@created    21 September 2001
     */
    
    final public class TrackLayer implements NewMapView {
        
        private TrackTileMap trackMap;
        
        private TrackPieceViewList trackPieceViewList;
        
        
        /** Returns true if there is a tile at this map
         * coordinate.
         *
         * @param mapCoordinate  Map coordinate in tiles.
         * @return True if there is a tile here.
         */
        
        public boolean aTileIsHere(Point mapCoordinate) {
            if (null != trackMap.getTrackPiece(mapCoordinate)) {
                return true;
            } else {
                return false;
            }
        }
         public Dimension getTileSize() {
            return tileSize;
        }
        
        /** Paints the specified tile onto the supplied
         * graphics context.
         *
         * @param x X coordinate measued in tiles.
         * @param y Y coordinate measued in tiles.
         * @param g The graphics context to paint on.  It is
         * assumed that the location of the graphics
         * context is the origin of the map.
         */
        public void paintTile(Graphics g, Point tile) {
            
            /*
             *  Since track tiles overlap the adjacent terrain tiles, we create a temporary Graphics
             *  object that only lets us draw on the selected tile.
             */
            
            paintRectangleOfTiles( g, new Rectangle(tile.x,tile.y,1,1));
            
        }
        /** Paints a rectangle of tiles onto the supplied
         * graphics context.
         * @param g The graphics context on which the tiles
         * get painted.
         * @param tilesToPaint The rectangle, measured in tiles, to
         * paint.
         */        
        public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint) {
            
            /*  Track can overlap the adjacent terrain tiles by half a tile.  This means
             *that we need to paint the track from the tiles bordering the specified rectangle
             *of tiles (tilesToPaint).  To prevent unnecessay painting, we set the clip to expose only the
             *rectangle of tilesToPaint.
             */
            
            Graphics tempG = g;
            Point tile=new Point();
            
                for (tile.x = tilesToPaint.x-1; tile.x < (tilesToPaint.x + tilesToPaint.width +1); tile.x++) {
                    for (tile.y = tilesToPaint.y-1; tile.y < (tilesToPaint.y + tilesToPaint.height +1); tile.y++)  {
                        
                        if ((tile.x >= 0) && (tile.x < mapSize.width) && (tile.y >= 0) && (tile.y < mapSize.height)) {
                            int graphicsNumber = trackMap.getTrackGraphicNumber(tile);
                            int ruleNumber = trackMap.getTrackTypeNumber(tile);
                            jfreerails.client.trackview.TrackPieceView trackPieceView = trackPieceViewList.getTrackPieceView(ruleNumber);
                            trackPieceView.drawTrackPieceIcon(graphicsNumber, tempG, tile.x, tile.y, tileSize);
                        }
                    }
                }
           
            
        }
        
        
        /**
         *  Gets the tileSize attribute of the TrackLayer object
         *
         *@return    The tileSize value
         */
       
	
	public float getScale() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}

	
	public void setScale(float scale) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	
	
	

	
	public void paintRect(Graphics g, Rectangle visibleRect) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}
	

	
	public NewMapView getParentMapView() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}

	
	public void setParentMapView(NewMapView parent) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	public void refreshTile(Point tile) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	public void refresh() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	public Dimension getMapSizeInPixels() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}
	public Dimension getMapSizeInTiles(){
		throw new UnsupportedOperationException("Method not yet implemented.");
	}
	public void refreshTileAndNotifyParent(Point tile){
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}
	public void refreshAndNotifyParent(){
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}
        
        
        /** Creates a new tracklayer.
         * @param trackMap Description of the Parameter
         * @param trackPieceViewList Description of the Parameter
         */
        
        public TrackLayer(TrackTileMap trackMap, TrackPieceViewList trackPieceViewList) {
            this.trackPieceViewList = trackPieceViewList;
            this.trackMap = trackMap;
        }
    }
    
    
    /**
     *  This inner class represents the terrain of the map.
     *
     *@author     Luke Lindsay
     *@created    21 September 2001
     */
    
    final public class TerrainLayer implements NewMapView {
        
        private TileViewList tiles;
        
        private TerrainMap terrainMap;
        
        
        /** Description of the Method
         *
         * @param mapCoordinate  Description of the Parameter
         * @return Description of the Return Value
         **/
        public boolean aTileIsHere(Point mapCoordinate) {
            return true;
        }
        
        
        /** Paints the specified tile onto the supplied
         * graphics context.
         * @param x x coordinate
         * @param y y coordinate.
         * @param g The graphics context
         */
        public void paintTile(Graphics g, Point tile) {
            
            int screenX=tileSize.width*tile.x;
            int screenY=tileSize.height*tile.y;
            if ((tile.x >= 0) && (tile.x < mapSize.width) && (tile.y >= 0) && (tile.y < mapSize.height)) {
                int rgb = terrainMap.getTerrainTileType(tile.x, tile.y);
                tiles.getTileViewWithRGBValue(rgb).renderTile(g, screenX, screenY, tile.x, tile.y, terrainMap);
            }
        }
        
        /** Paints a rectangle of tiles on the supplied graphics
         * context.
         * @param g The grahics context.
         * @param tilesToPaint The rectangle, measued in tiles, to paint.
         */        
        public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint) {
        	Point tile=new Point();
            for(tile.x=tilesToPaint.x;tile.x<(tilesToPaint.x+tilesToPaint.width);tile.x++){
                for(tile.y=tilesToPaint.y;tile.y<(tilesToPaint.y+tilesToPaint.height);tile.y++){
                    terrainLayer.paintTile(g, tile);
                }
            }
            
        }
        public void paintRect(Graphics g, Rectangle visibleRect) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}
        
        
        /**
         *  Gets the tileSize attribute of the TerrainLayer object
         *
         *@return    The tileSize value
         */
        public Dimension getTileSize() {
            return tileSize;
        }
       
	
	public float getScale() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}

	
	public void setScale(float scale) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	

	
	
	
	
	public NewMapView getParentMapView() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}

	
	public void setParentMapView(NewMapView parent) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	public void refreshTile(Point tile) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	public void refresh() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	public Dimension getMapSizeInPixels() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}
	public Dimension getMapSizeInTiles(){
		throw new UnsupportedOperationException("Method not yet implemented.");
	}
	public void refreshTileAndNotifyParent(Point tile){
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}
	public void refreshAndNotifyParent(){
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}
        
        /** Constructor for the TerrainLayer object
         *
         * @param terrainMap  Description of the Parameter
         * @param tiles       Description of the Parameter
         */
        public TerrainLayer(TerrainMap terrainMap, TileViewList tiles) {
            this.terrainMap = terrainMap;
            this.tiles = tiles;
        }
    }
    
    
    /** Creates new BackgroundMapView
     *
     * @param map                 The non-visual map object.
     * @param tiles               An object holding a list of tileView objects,
     *     which represent the visual properites of terrain tiles.
     * @param trackPieceViewList The object holding the set of objects
     *     representing the visual properties of track-pieces.
     */
    
    public BackgroundMapView(TrackAndTerrainTileMap map, TileViewList tiles, TrackPieceViewList trackPieceViewList) {
        trackLayer = new TrackLayer(map, trackPieceViewList);
        terrainLayer = new TerrainLayer(map, tiles);
        mapSize = new Dimension(map.getWidth(), map.getHeight());
    }
    
    
    
    /** Paints a rectangle of the map on the specified
     * graphics context.
     * @param g Description of the Parameter
     * @param tilesToPaint Description of the Parameter
     */
    public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint) {
        terrainLayer.paintRectangleOfTiles( g,  tilesToPaint);
        trackLayer.paintRectangleOfTiles( g,  tilesToPaint);
    }
    
    /** Gets the size of the map in tiles.
     * @return The size of the map measured in tiles.
     */
    public Dimension getMapSizeInTiles() {
        return mapSize;
    }
   
    
    /** Gets the tileSize attribute of this BackgroundMapView object.
     *
     * @return The tileSize value
     */
    public Dimension getTileSize() {
        return tileSize;
    }
    
    
    /** Gets the mapSizeInPixels attribute of this
     * BackgroundMapView object.
     *
     * @return The mapSizeInPixels value
     */
    public Dimension getMapSizeInPixels() {
        return new Dimension(tileSize.width * mapSize.width, tileSize.height * mapSize.height);
    }
   
    
    /** Paints the specified tile onto the supplied
     * graphics context.
     *
     * @param mapX X coordinate measued in tiles.
     * @param mapY Y coordinate measured in tiles.
     * @param g The graphics context to paint on.  It is
     * assumed that the location of the graphics
     * context is the origin of the map.
     */
    
    public void paintTile(Graphics g, Point tile) {
        terrainLayer.paintTile(g, tile);
        trackLayer.paintTile(g,  tile);
    }
    
    /** Paints a rectangle of the background onto
     * the supplied grahics context.
     *
     * @param g The graphics context on which the
     * backgound gets painted.  Use its clip
     * rectangle to specify the rectangle to
     * paint.
     */    
    public void paintRect(Graphics g,  Rectangle visibleRect) {
        //Dimension tileSize = getTileSize();
        //Dimension mapSize = getMapSizeInTiles();
        int tileWidth = tileSize.width;
        int tileHeight = tileSize.height;
        
        clipRectangle=g.getClipBounds(clipRectangle);
        Rectangle rectToPaint=new Rectangle(clipRectangle.x / tileWidth,
        clipRectangle.y / tileHeight,
        (clipRectangle.width/ tileWidth) + 2,
        (clipRectangle.height) / tileHeight + 2);
        
        paintRectangleOfTiles(g, rectToPaint);
    }
    
	public float getScale() {
		return (float)tileSize.height;
	
	}

	
	public void setScale(float scale) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	

	
	

	
	public NewMapView getParentMapView() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}

	
	public void setParentMapView(NewMapView parent) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	public void refreshTile(Point tile) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	public void refresh() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	
	public void refreshTileAndNotifyParent(Point tile){
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}
	public void refreshAndNotifyParent(){
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}
}
