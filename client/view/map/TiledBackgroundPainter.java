package jfreerails.client.view.map;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import jfreerails.client.*;

/**
 *  Description of the Interface
 *
 *@author     Luke Lindsay
 *@created    06 October 2001
 */

public interface TiledBackgroundPainter {



    /**
     * Refreshes the image stored on the backbuffer of the specified tile.
     * Call to prevent the background buffer becoming out of sync. with
     * the map when a tile is updated.
     *
     *@param  mapCoord  Description of Parameter
     */

    public void refreshTile(int x, int y);


    public void refreshRectangleOfTiles(int x, int y, int width, int height);


    /**
     * Paints a rectangular region of the background on the
     * specified graphics object.  If the background buffer
     * does not currently store the specified region, it is
     * scrolled and/or resized accordingly before being painted
     * onto the graphics object.
     *
     *@param  outputGraphics          The object on which to paint the background.
     *@param  newVisibleRectectangle  The region of the background to paint.
     */

    public void paint(java.awt.Graphics outputGraphics, Rectangle newVisibleRectectangle);
}
