
/*
* Cursor.java
*
* Created on 01 August 2001, 06:02
*/
package jfreerails.client;
import jfreerails.client.event.CursorEvent;
import jfreerails.client.event.CursorEventListener;
import jfreerails.common.OneTileMoveVector;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.event.KeyEvent;

/** This class represents the cursor for the map view.  It receives key
* and mouse inputs and fires cursor events.
*
* @author Luke Lindsay
* @version 1
*/


public class FreerailsCursor implements java.awt.event.KeyListener {

    
    /** This inner class controls rendering of the cursor.
    */
    public jfreerails.client.FreerailsCursor.CursorRenderer cursorRenderer = new jfreerails.client.FreerailsCursor.CursorRenderer();

    protected java.util.Vector listeners = new java.util.Vector();

    
    //private jfreerails.common.trackmodel.TrackBuilder trackBuilder;
    private Point cursorMapPosition = new Point( 0, 0 );

    private MapView mapView;

    private Point oldCursorMapPosition = new Point( 0, 0 );

    
    /*The cursor tile and one tile in each direction
    */
    private java.awt.BasicStroke stroke = new java.awt.BasicStroke( 3 );

    
    //The width of the rectangle used to draw the cursor
    private int paintCursor;
    
    /** This inner class controls rendering of the cursor.
    */
    

    public class CursorRenderer {
        
        /** Paints the cursor.  The method calculates position to paint it based on the
        * tile size and the cursor's map position.
        * @param g The graphics object to paint the cursor on.
        * @param tileSize The dimensions of a tile.
        */
        
        public void paintCursor( java.awt.Graphics g, java.awt.Dimension tileSize ) {
            java.awt.Graphics2D  g2 = (java.awt.Graphics2D)g;
            g2.setStroke( stroke );
            g2.setColor( java.awt.Color.white ); //The colour of the cursor
            g2.drawRect( cursorMapPosition.x * tileSize.width, cursorMapPosition.y * tileSize.height, tileSize.width, tileSize.height );
        }
    }
    
    /** Creates a new FreerailsCursor.
    * @param mapView The view that the curors moves across.
    */
    
    public FreerailsCursor( MapView mapView ) {
        this.mapView = mapView;
    }
    
    /** Use this method rather than KeyTyped to process keyboard input.
    * @param keyEvent The key pressed.
    */
    
    public void keyPressed( KeyEvent keyEvent ) {
        switch( keyEvent.getKeyCode() ) {
          case KeyEvent.VK_NUMPAD1:
              moveCursor( OneTileMoveVector.SOUTH_WEST );
              break;
          
          case KeyEvent.VK_NUMPAD2:
              moveCursor( OneTileMoveVector.SOUTH );
              break;
          
          case KeyEvent.VK_NUMPAD3:
              moveCursor( OneTileMoveVector.SOUTH_EAST );
              break;
          
          case KeyEvent.VK_NUMPAD4:
              moveCursor( OneTileMoveVector.WEST );
              break;
          
          case KeyEvent.VK_NUMPAD6:
              moveCursor( OneTileMoveVector.EAST );
              break;
          
          case KeyEvent.VK_NUMPAD7:
              moveCursor( OneTileMoveVector.NORTH_WEST );
              break;
          
          case KeyEvent.VK_NUMPAD8:
              moveCursor( OneTileMoveVector.NORTH );
              break;
          
          case KeyEvent.VK_NUMPAD9:
              moveCursor( OneTileMoveVector.NORTH_EAST );
              break;
          
          default:
              fireOffCursorKeyPressed( keyEvent, cursorMapPosition );
        }
    
    // component = (MapViewJComponent)keyEvent.getComponent();
    }
    
    /** Removes a listener.
    * @param l The listener.
    */
    
    public void removeCursorEventListener( CursorEventListener l ) {
        listeners.removeElement( l );
    }
    
    /** Moves the cursor provided the destination is a legal position.
    * @param tryThisPoint The cursor's destination.
    */
    
    public void TryMoveCursor( Point tryThisPoint ) {
        java.awt.Dimension  mapSize = mapView.getMapSizeInTiles();
        Rectangle  legalRectangle; //The set of legal cursor positions.
        legalRectangle = new Rectangle( 1, 1, mapSize.width - 2, mapSize.height - 2 );
        if( true == legalRectangle.contains( tryThisPoint ) ) {
            
            /*Move the cursor. */
            oldCursorMapPosition.setLocation( cursorMapPosition );
            cursorMapPosition.setLocation( tryThisPoint );
            int  deltaX = cursorMapPosition.x - oldCursorMapPosition.x;
            int  deltaY = cursorMapPosition.y - oldCursorMapPosition.y;
            
            /*Build track! */
            if( OneTileMoveVector.checkValidity( deltaX, deltaY ) ) {
                try {
                    fireOffCursorOneTileMove( new OneTileMoveVector( deltaX, deltaY ), oldCursorMapPosition );
                }
                
                //This should never happen!
                catch( jfreerails.common.exception.FreerailsException fe ) {
                    fe.printStackTrace();
                }
            }
            else {
                jfreerails.lib.TextMessageHandler.sendMessage( "Jumped to: " + cursorMapPosition.x + ", " + cursorMapPosition.y );
                fireOffCursorJumped( oldCursorMapPosition, cursorMapPosition );
            }
        }
        else {
            jfreerails.lib.TextMessageHandler.sendMessage( "Illegal cursor position!" );
        }
    }
    
    /** Empty method, needed to implement the KeyListener interface.
    * @param keyEvent The key typed.
    */
    
    public void keyTyped( KeyEvent keyEvent ) {
        
    }
    
    /** Use keyPressed instead of this method.
    * @param keyEvent the key pressed
    */
    
    public void keyReleased( KeyEvent keyEvent ) {
        
    }
    
    /** Adds a listener.  Listeners could include: the trackbuild system, the view the cursor moves across, etc.
    * @param l The listener.
    */
    
    public void addCursorEventListener( CursorEventListener l ) {
        listeners.addElement( l );
    }
    
    private void fireOffCursorOneTileMove( OneTileMoveVector v, Point oldPosition ) {
        CursorEvent  ce = new CursorEvent( this );
        ce.vector = v;
        ce.oldPosition = oldPosition;
        ce.newPosition = cursorMapPosition;
        for( int  i = 0;i < listeners.size();i++ ) {
            ( (CursorEventListener)listeners.elementAt( i ) ).cursorOneTileMove( ce );
        }
    }
    
    private void fireOffCursorKeyPressed( KeyEvent keyEvent, Point position ) {
        CursorEvent  ce = new CursorEvent( this );
        ce.keyEvent = keyEvent;
        ce.oldPosition = position;
        ce.newPosition = position;
        for( int  i = 0;i < listeners.size();i++ ) {
            ( (CursorEventListener)listeners.elementAt( i ) ).cursorKeyPressed( ce );
        }
    }
    
    private void moveCursor( OneTileMoveVector v ) {
        TryMoveCursor( new Point( cursorMapPosition.x + v.getX(), cursorMapPosition.y + v.getY() ) );
    }
    
    private void fireOffCursorJumped( Point oldPosition, Point newPosition ) {
        CursorEvent  ce = new CursorEvent( this );
        ce.oldPosition = oldCursorMapPosition;
        ce.newPosition = newPosition;
        for( int  i = 0;i < listeners.size();i++ ) {
            ( (CursorEventListener)listeners.elementAt( i ) ).cursorJumped( ce );
        }
    }
}
