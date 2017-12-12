
/*
* MapViewJComponent.java
*
* Created on 31 July 2001, 13:56
*/
package jfreerails.client;
import jfreerails.client.event.CursorEvent;
import java.awt.*;

/**
*
* @author  Luke Lindsay
* @version
*/


public class MapViewJComponentConcrete extends MapViewJComponent implements jfreerails.client.event.CursorEventListener {

    private jfreerails.common.trackmodel.TrackBuilder trackBuilder;

    private FreerailsCursor cursor;
    

    private class MapViewJComponentMouseAdapter extends java.awt.event.MouseAdapter {
        
        public void mousePressed( java.awt.event.MouseEvent mouseEvent ) {
            int  x = mouseEvent.getX();
            int  y = mouseEvent.getY();
            Dimension  tileSize = mapView.getTileSize();
            cursor.TryMoveCursor( new java.awt.Point( x / tileSize.width, y / tileSize.height ) );
            MapViewJComponentConcrete.this.requestFocus();
        }
    }
    
    public void updateTiles( Rectangle rect ) {
        
    }
    
    public void cursorKeyPressed( CursorEvent ce ) {
        reactToCursorMovement( ce );
    }
    
    public void paint( java.awt.Graphics g ) {
        super.paint( g );
        java.awt.Graphics2D  g2 = (java.awt.Graphics2D)g;
        
        //java.awt.Rectangle  r = this.getVisibleRect();
        
        //tiledBackgroundPainter.paint( g2, r );
        cursor.cursorRenderer.paintCursor( g2, new java.awt.Dimension( 30, 30 ) );
    }
    
    public void updateTile( java.awt.Point tileCoodinate ) {
        
    }
    
    public void cursorJumped( CursorEvent ce ) {
        try {
            trackBuilder.performAction( ce.newPosition );
            tiledBackgroundPainter.updateTile( ce.newPosition );
            reactToCursorMovement( ce );
        }
        catch( jfreerails.common.exception.FreerailsException fe ) {
            
            //This should never happen!
            fe.printStackTrace();
        }
    }
    
    public void cursorOneTileMove( CursorEvent ce ) {
        if( null != trackBuilder ) {
            try {
                trackBuilder.performAction( ce.oldPosition, ce.vector );
                tiledBackgroundPainter.updateTile( ce.oldPosition );
            }
            catch( jfreerails.common.exception.FreerailsException fe ) {
                
                //This should never happen!
                fe.printStackTrace();
            }
        }
        else {
            System.out.println( "No track builder available!" );
        }
        reactToCursorMovement( ce );
    }
    
    public MapViewJComponentConcrete( MapView mv, jfreerails.common.trackmodel.TrackBuilder trackBuilder ) {
        super( new SquareTileBackgroundPainter( mv ), mv );
        this.setBorder( null );
        this.trackBuilder = trackBuilder;
        this.cursor = new FreerailsCursor( mv );
        cursor.addCursorEventListener( this );
        this.addMouseListener( new jfreerails.client.MapViewJComponentConcrete.MapViewJComponentMouseAdapter() );
        this.addKeyListener( cursor );
        this.requestFocus();
    }
    
    private void reactToCursorMovement( CursorEvent ce ) {
        java.awt.Dimension  tileSize = mapView.getTileSize();
        Rectangle  vr = this.getVisibleRect();
        Rectangle  rectangleSurroundingCursor = new Rectangle( 0, 0, 1, 1 );
        rectangleSurroundingCursor.setLocation( ( ce.newPosition.x - 1 ) * tileSize.width, ( ce.newPosition.y - 1 ) * tileSize.height );
        rectangleSurroundingCursor.setSize( tileSize.width * 3, tileSize.height * 3 );
        if( !( vr.contains( rectangleSurroundingCursor ) ) ) {
            int  x = ce.newPosition.x * tileSize.width - vr.width / 2;
            int  y = ce.newPosition.y * tileSize.height - vr.height / 2;
            this.scrollRectToVisible( new Rectangle( x, y, vr.width, vr.height ) );
        }
        this.repaint( ( ce.newPosition.x - 1 ) * tileSize.width, ( ce.newPosition.y - 1 ) * tileSize.height, tileSize.width * 3, tileSize.height * 3 );
        this.repaint( ( ce.oldPosition.x - 1 ) * tileSize.width, ( ce.oldPosition.y - 1 ) * tileSize.height, tileSize.width * 3, tileSize.height * 3 );
    }
}
