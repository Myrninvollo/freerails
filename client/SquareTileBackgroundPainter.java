
/*
 *  SquareTileBackgroundPainter.java
 *
 *  Created on 31 July 2001, 16:36
 */
package jfreerails.client;
import java.awt.*;
import java.awt.Dimension;

/**
 *  This class stores a buffer containing the terrain and track layers of
 *  current visible rectangle of the map. It is responsible of painting these
 *  layers and updating the buffer when the map scrolls or tiles are updated.
 *
 *@author     Luke Lindsay
 *@created    01 November 2001
 *@version    1.0
 */

public class SquareTileBackgroundPainter extends jfreerails.client.BufferedTiledBackgroundPainter {

    private MapView mapView;


    /**
     *  Updates a tile of the backgound buffer. It needs to be called when a
     *  visilbe tile's properties change, e.g. track is built on the tile.
     *
     *@param  mapCoord  map coordinate of in tiles.
     */

    public void refreshTile(int xx, int yy) {

                    Point tileMapCoordinate = new Point(xx, yy);
                    Graphics gg=bg.create();
                    gg.translate(-bufferRect.x , -bufferRect.y);
                    mapView.paintTile(gg, xx,yy);

    }


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
        mapView.paintRect(gg);
    }


    /**
     *  Creates new SquareTileBackgroundPainter
     *
     *@param  mapView  Object that has access to the map and paints individual
     *      tiles.
     */

    public SquareTileBackgroundPainter(MapView mapView) {
        this.mapView = mapView;
    }
    public void refreshRectangleOfTiles(int x, int y, int width, int height){
        Dimension tileSize=mapView.getTileSize();
        int w=tileSize.width;
        int h=tileSize.height;
        java.awt.Graphics gg=bg.create();
        gg.translate(-bufferRect.x , -bufferRect.y);
        gg.clipRect(x*w,y*h,width*w,height*h);
        mapView.paintRectangleOfTiles(gg, new Rectangle(x,y,width,height));
    }
}
