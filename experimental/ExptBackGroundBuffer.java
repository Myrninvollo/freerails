
/*
* ExptBackGroundBuffer.java
*
* Created on 24 August 2001, 22:03
*/
package jfreerails.experimental;
import jfreerails.client.*;
import java.awt.*;
import java.awt.image.VolatileImage;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class ExptBackGroundBuffer extends jfreerails.client.SquareTileBackgroundPainter {
    
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
    
    /** Creates new ExptBackGroundBuffer */
    
    public ExptBackGroundBuffer( MapView mapView ) {
        super( mapView );
    }
}
