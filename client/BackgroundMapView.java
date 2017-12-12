
/*
 *  BackgroundMapView.java
 *
 *  Created on 06 August 2001, 17:21
 */
package jfreerails.client;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import jfreerails.client.tileview.TileViewList;
import jfreerails.client.trackview.TrackPieceView;
import jfreerails.client.trackview.TrackPieceViewList;
import jfreerails.common.exception.FreerailsException;
import jfreerails.map.FreerailsMap;
import jfreerails.map.TerrainMap;
import jfreerails.map.TrackSystem;


/** This class encapsulates the objects that make-up and paint the background
 * of the map view. At present it is composed of two layers: the terrain layer
 * and the track layer.
 *
 * @author Luke Lindsay
 * @created 21 September 2001
 * @version 1
 */

public class BackgroundMapView implements MapView {
    
    /** The terrain layer.
     */
    protected jfreerails.client.BackgroundMapView.TerrainLayer terrainLayer;
    
    /** The track layer.
     */
    protected jfreerails.client.BackgroundMapView.TrackLayer trackLayer;
    
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
    
    public class TrackLayer implements MapViewLayer {
        
        private TrackSystem trackMap;
        
        private TrackPieceViewList trackPieceViewList;
        
        
        /** Returns true if there is a tile at this map
         * coordinate.
         *
         * @param mapCoordinate  Map coordinate in tiles.
         * @return True if there is a tile here.
         */
        
        public boolean aTileIsHere(Point mapCoordinate) {
            if (null != trackMap.getTrackNode(mapCoordinate)) {
                return true;
            } else {
                return false;
            }
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
        public void paintTile(Graphics g, int x, int y) {
            
            /*
             *  Since track tiles overlap the adjacent terrain tiles, we create a temporary Graphics
             *  object that only lets us draw on the selected tile.
             */
            
            paintRectangleOfTiles( g, new Rectangle(x,y,1,1));
            
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
            try {
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
            } catch (jfreerails.common.exception.FreerailsException fe) {
                fe.printStackTrace();
            }
            
        }
        
        
        /**
         *  Gets the tileSize attribute of the TrackLayer object
         *
         *@return    The tileSize value
         */
        public Dimension getTileSize() {
            return tileSize;
        }
        
        
        /** Creates a new tracklayer.
         * @param trackMap Description of the Parameter
         * @param trackPieceViewList Description of the Parameter
         */
        
        public TrackLayer(TrackSystem trackMap, TrackPieceViewList trackPieceViewList) {
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
    
    public class TerrainLayer implements MapViewLayer {
        
        private TileViewList tiles;
        
        private TerrainMap terrainMap;
        
        
        /** Description of the Method
         *
         * @param mapCoordinate  Description of the Parameter
         * @return Description of the Return Value
         */
        public boolean aTileIsHere(Point mapCoordinate) {
            return true;
        }
        
        
        /** Paints the specified tile onto the supplied
         * graphics context.
         * @param x x coordinate
         * @param y y coordinate.
         * @param g The graphics context
         */
        public void paintTile(Graphics g, int x, int y) {
            
            int screenX=tileSize.width*x;
            int screenY=tileSize.height*y;
            if ((x >= 0) && (x < mapSize.width) && (y >= 0) && (y < mapSize.height)) {
                int rgb = terrainMap.getTerrainTileType(x, y);
                tiles.getTileViewWithRGBValue(rgb).renderTile(g, screenX, screenY, x, y, terrainMap);
            }
        }
        
        /** Paints a rectangle of tiles on the supplied graphics
         * context.
         * @param g The grahics context.
         * @param tilesToPaint The rectangle, measued in tiles, to paint.
         */        
        public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint) {
            for(int x=tilesToPaint.x;x<(tilesToPaint.x+tilesToPaint.width);x++){
                for(int y=tilesToPaint.y;y<(tilesToPaint.y+tilesToPaint.height);y++){
                    terrainLayer.paintTile(g, x,y);
                }
            }
            
        }
        
        
        /**
         *  Gets the tileSize attribute of the TerrainLayer object
         *
         *@return    The tileSize value
         */
        public Dimension getTileSize() {
            return tileSize;
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
    
    public BackgroundMapView(jfreerails.map.FreerailsMap map, TileViewList tiles, TrackPieceViewList trackPieceViewList) {
        trackLayer = new jfreerails.client.BackgroundMapView.TrackLayer(map.getTrackMap(), trackPieceViewList);
        terrainLayer = new jfreerails.client.BackgroundMapView.TerrainLayer(map.getTerrainMap(), tiles);
        mapSize = new Dimension(map.getTerrainMap().getWidth(), map.getTerrainMap().getHeight());
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
    
    public void paintTile(Graphics g, int mapX, int mapY) {
        terrainLayer.paintTile(g, mapX, mapY);
        trackLayer.paintTile(g,  mapX, mapY);
    }
    
    /** Paints a rectangle of the background onto
     * the supplied grahics context.
     *
     * @param g The graphics context on which the
     * backgound gets painted.  Use its clip
     * rectangle to specify the rectangle to
     * paint.
     */    
    public void paintRect(Graphics g) {
        Dimension tileSize = getTileSize();
        Dimension mapSize = getMapSizeInTiles();
        int tileWidth = tileSize.width;
        int tileHeight = tileSize.height;
        
        clipRectangle=g.getClipBounds(clipRectangle);
        Rectangle rectToPaint=new Rectangle(clipRectangle.x / tileWidth,
        clipRectangle.y / tileHeight,
        (clipRectangle.width/ tileWidth) + 2,
        (clipRectangle.height) / tileHeight + 2);
        
        paintRectangleOfTiles(g, rectToPaint);
    }
}
