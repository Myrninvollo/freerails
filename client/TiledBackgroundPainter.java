
/*
* TiledBackgroundPainter.java
*
* Created on 31 July 2001, 16:22
*/
package jfreerails.client;
import java.awt.image.VolatileImage;
import java.awt.*;

/** This abstract class stores a buffer of the backgound
* of the current visible rectangle of the map.  Code
* that is independent of how tiles are represented, e.g.
* whether they are square or isometric, should go here.
*
* @author Luke Lindsay
* @version 1.0
*/


public abstract class TiledBackgroundPainter {

    protected Rectangle bufferRect = new Rectangle();

    protected java.awt.Image backgroundBuffer;

    protected java.awt.GraphicsConfiguration defaultConfiguration = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

    protected java.awt.Graphics bg;

    protected final boolean BUFFER_IS_VOLATILE = false;
    
    public void setbackgroundBuffer( int w, int h ) {
        if( BUFFER_IS_VOLATILE ) {
            do {
                backgroundBuffer = defaultConfiguration.createCompatibleVolatileImage( w, h );
                if( ( (VolatileImage)backgroundBuffer ).validate( defaultConfiguration ) == VolatileImage.IMAGE_INCOMPATIBLE ) {
                    backgroundBuffer = defaultConfiguration.createCompatibleVolatileImage( w, h );
                }
                bufferRect.height = backgroundBuffer.getHeight( null );
                bufferRect.width = backgroundBuffer.getWidth( null );
                bg = backgroundBuffer.getGraphics();
                refreshBackground();
            }while( ( (VolatileImage)backgroundBuffer ).contentsLost() );
        }
        else {
            backgroundBuffer = defaultConfiguration.createCompatibleImage( w, h );
            bufferRect.height = backgroundBuffer.getHeight( null );
            bufferRect.width = backgroundBuffer.getWidth( null );
            bg = backgroundBuffer.getGraphics();
            refreshBackground();
        }
    }
    
    public void paint( java.awt.Graphics outputGraphics, Rectangle newVisibleRectectangle ) {
        do {
            
            /* If this is the first call to the paint method or the component has just been resized,
            *we need to create a new backgroundBuffer.    */
            if( ( backgroundBuffer == null ) || ( newVisibleRectectangle.height != bufferRect.height ) || ( newVisibleRectectangle.width != bufferRect.width ) ) {
                setbackgroundBuffer( newVisibleRectectangle.width, newVisibleRectectangle.height );
            }
            
            /* If the BB is volatile, have we lost it?   */
            if( BUFFER_IS_VOLATILE && ( VolatileImage.IMAGE_INCOMPATIBLE == ( (VolatileImage)backgroundBuffer ).validate( defaultConfiguration ) ) ) {
                setbackgroundBuffer( newVisibleRectectangle.width, newVisibleRectectangle.height );
            }
            
            /* Has the VisibleRectangle moved since the last paint?     */
            if( ( bufferRect.x != newVisibleRectectangle.x ) || ( bufferRect.y != newVisibleRectectangle.y ) ) {
                int  dx = bufferRect.x - newVisibleRectectangle.x;
                int  dy = bufferRect.y - newVisibleRectectangle.y;
                scrollbackgroundBuffer( dx, dy );
                bufferRect.setBounds( newVisibleRectectangle );
            }
            if( ( bufferRect.width != newVisibleRectectangle.width ) && ( bufferRect.height != newVisibleRectectangle.height ) ) {
                paintBufferRectangle( newVisibleRectectangle.x - bufferRect.x, newVisibleRectectangle.y - bufferRect.y, newVisibleRectectangle.width, newVisibleRectectangle.height );
            }
            outputGraphics.drawImage( backgroundBuffer, newVisibleRectectangle.x, newVisibleRectectangle.y, null );
        }while( BUFFER_IS_VOLATILE && ( ( (VolatileImage)backgroundBuffer ).contentsLost() ) );
        bufferRect.setBounds( newVisibleRectectangle );
    }
    
    public void paintBackground( java.awt.Graphics g, int x, int y, int width, int height ) {
        Rectangle  r = g.getClipBounds();
        if( ( bufferRect.width == r.width ) && ( bufferRect.height == r.height ) ) {
            g.drawImage( backgroundBuffer, x, y, x + width, y + height, x, y, x + width, y + height, null );
        }
        else {
            paintBufferRectangle( r.x, r.y, r.width, r.height );
            g.drawImage( backgroundBuffer, x, y, x + width, y + height, x, y, x + width, y + height, null );
        }
    }
    
    public abstract void updateTile( Point mapCoord );
    
    public void refreshBackground() {
        paintBufferRectangle( 0, 0, bufferRect.width, bufferRect.height );
    }
    
    /** Used for benchmarking only.
    */
    
    public void benchmark( Graphics g, Rectangle newVisibleRectangle ) {
        do {
            
            /* If this is the first call to the paint method or the component has just been resized,
            *we need to create a new backgroundBuffer.    */
            if( ( backgroundBuffer == null ) || ( newVisibleRectangle.height != bufferRect.height ) || ( newVisibleRectangle.width != bufferRect.width ) ) {
                setbackgroundBuffer( newVisibleRectangle.width, newVisibleRectangle.height );
            }
            
            /* If the BB is volatile, have we lost it?   */
            if( BUFFER_IS_VOLATILE && ( VolatileImage.IMAGE_INCOMPATIBLE == ( (VolatileImage)backgroundBuffer ).validate( defaultConfiguration ) ) ) {
                setbackgroundBuffer( newVisibleRectangle.width, newVisibleRectangle.height );
            }
            
            /* Has the VisibleRectangle moved since the last paint?     */
            if( ( bufferRect.x != newVisibleRectangle.x ) || ( bufferRect.y != newVisibleRectangle.y ) ) {
                int  dx = bufferRect.x - newVisibleRectangle.x;
                int  dy = bufferRect.y - newVisibleRectangle.y;
                scrollbackgroundBuffer( dx, dy );
                bufferRect.setBounds( newVisibleRectangle );
            }
            if( ( bufferRect.width != newVisibleRectangle.width ) && ( bufferRect.height != newVisibleRectangle.height ) ) {
                paintBufferRectangle( newVisibleRectangle.x - bufferRect.x, newVisibleRectangle.y - bufferRect.y, newVisibleRectangle.width, newVisibleRectangle.height );
            }
            g.drawImage( backgroundBuffer, 0, 0, null );
        }while( BUFFER_IS_VOLATILE && ( ( (VolatileImage)backgroundBuffer ).contentsLost() ) );
        bufferRect.setBounds( newVisibleRectangle );
    }
    
    abstract public void paintBufferRectangle( int x, int y, int width, int height );
    
    protected void scrollbackgroundBuffer( int dx, int dy ) {
        int  copyWidth = bufferRect.width;
        int  copyHeight = bufferRect.height;
        int  copySourceX = 0;
        int  copySourceY = 0;
        if( dx > 0 ) {
            copyWidth -= dx;
        }
        else {
            copyWidth += dx;
            copySourceX = -dx;
        }
        if( dy > 0 ) {
            copyHeight -= dy;
        }
        else {
            copyHeight += dy;
            copySourceY = -dy;
        }
        bg.copyArea( copySourceX, copySourceY, copyWidth, copyHeight, dx, dy );
        
        // now have to reset scroll so new paint is right
        bufferRect.x -= dx;
        bufferRect.y -= dy;
        
        // paint exposed areas
        if( dx > 0 ) {
            bg.clearRect( 0, 0, dx, bufferRect.height );
            paintBufferRectangle( 0, 0, dx, bufferRect.height );
        }
        else {
            if( dx < 0 ) {
                bg.clearRect( bufferRect.width + dx, 0, -dx, bufferRect.height );
                paintBufferRectangle( bufferRect.width + dx, 0, -dx, bufferRect.height );
            }
        }
        if( dy > 0 ) {
            bg.clearRect( 0, 0, bufferRect.width, dy );
            paintBufferRectangle( 0, 0, bufferRect.width, dy );
        }
        else {
            if( dy < 0 ) {
                bg.clearRect( 0, bufferRect.height + dy, bufferRect.width, -dy );
                paintBufferRectangle( 0, bufferRect.height + dy, bufferRect.width, -dy );
            }
        }
    }
}
