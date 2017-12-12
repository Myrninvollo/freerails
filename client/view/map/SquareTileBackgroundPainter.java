
/*
 *  SquareTileBackgroundPainter.java
 *
 *  Created on 31 July 2001, 16:36
 */
package jfreerails.client.view.map;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *  This class stores a buffer containing the terrain and track layers of
 *  current visible rectangle of the map. It is responsible of painting these
 *  layers and updating the buffer when the map scrolls or tiles are updated.
 *
 *@author     Luke Lindsay
 *@created    01 November 2001
 *@version    1.0
 */

public class SquareTileBackgroundPainter extends BufferedTiledBackgroundPainter {

    private NewMapView mapView;


    /**
     *  Updates a tile of the backgound buffer. It needs to be called when a
     *  visilbe tile's properties change, e.g. track is built on the tile.
     *
     *@param  mapCoord  map coordinate of in tiles.
     */

//    public void refreshTile(int xx, int yy) {
//
//                    
//    }


    /**
     *  Description of the Method
     *
     *@param  x       Description of the Parameter
     *@param  y       Description of the Parameter
     *@param  width   Description of the Parameter
     *@param  height  Description of the Parameter
     */
    protected void paintBufferRectangle(int x, int y, int width, int height) {
        java.awt.Graphics gg=bg.create();
        gg.setClip( x,  y,  width,  height);
        gg.translate(-bufferRect.x , -bufferRect.y);
        mapView.paintRect(gg, bufferRect);
    }


    /**
     *  Creates new SquareTileBackgroundPainter
     *
     *@param  mapView  Object that has access to the map and paints individual
     *      tiles.
     */

    public SquareTileBackgroundPainter(NewMapView mapView) {
        this.mapView = mapView;
    }
    public void refreshRectangleOfTiles(Rectangle r){
        Dimension tileSize=mapView.getTileSize();
        int w=tileSize.width;
        int h=tileSize.height;
        java.awt.Graphics gg=bg.create();
        gg.translate(-bufferRect.x , -bufferRect.y);
        gg.clipRect(r.x*w,r.y*h,r.width*w,r.height*h);
        mapView.paintRectangleOfTiles(gg, r);
    }
    public Dimension getTileSize(){
		return mapView.getTileSize();
	}
	
	public float getScale() {
		return mapView.getScale();
		
	}

	
	public void setScale(float scale) {
		mapView.setScale(scale);

	}
	public void paintTile(Graphics g, Point tile) {
		mapView.paintTile(g, tile);
	}

	
	public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
//	public void paintRect(Graphics g, Rectangle visibleRect) {
//		throw new UnsupportedOperationException("Method not yet implemented.");
//	}
	

	
	public NewMapView getParentMapView() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}

	
	public void setParentMapView(NewMapView parent) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	public void refreshTile(Point tile) {
		
                    Graphics gg=bg.create();
                    gg.translate(-bufferRect.x , -bufferRect.y);
                    mapView.paintTile(gg, tile);

	}

	
	public void refresh() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	
	public Dimension getMapSizeInPixels() {
		return mapView.getMapSizeInPixels();
	
	}
	public Dimension getMapSizeInTiles(){
		return mapView.getMapSizeInTiles();
	}
	public void refreshTileAndNotifyParent(Point tile){
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}
	public void refreshAndNotifyParent(){
		throw new UnsupportedOperationException("Method not yet implemented.");
	
	}
}
