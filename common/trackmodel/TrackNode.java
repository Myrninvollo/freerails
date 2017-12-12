
/*
* TrackNode.java
*
* Created on 10 July 2001, 12:47
*/
package jfreerails.common.trackmodel;

/**
*
* @author  Luke Lindsay
* @version 
*/
import jfreerails.common.trackmodel.RailModel;
import jfreerails.common.OneTileMoveVector;
import jfreerails.common.IntPoint;
import jfreerails.common.trackmodel.TrackRule;
import jfreerails.common.exception.FreerailsException;


public class TrackNode extends java.lang.Object {

    private TrackRule trackRule;

    private boolean[][] railsList = new boolean[ 3 ][ 3 ];

    private IntPoint position;
    
    public void addRail( OneTileMoveVector rail, TrackRule trackRule ) {
        railsList[ 1 ][ 1 ] = true; //The central piece.
        railsList[ 1 + rail.getX() ][ 1 + rail.getY() ] = true;
    }
    
    //This value determines which track graphic to display.
    
    public int getTrackGraphicNumber() {
        return getTrackGraphicNumber( railsList );
    }
    
    public TrackRule getTrackRule() {
        return this.trackRule;
    }
    
    public boolean testAddRail( OneTileMoveVector rail, TrackRule trackRule ) throws FreerailsException {
        int  trackGraphicNumber = this.getTrackGraphicNumber();
        trackGraphicNumber = trackGraphicNumber | ( 1 << ( 3 * ( 1 + rail.getY() ) + ( 1 + rail.getX() ) ) );
        trackGraphicNumber = trackGraphicNumber | ( 1 << 4 ); //The centre square!
        return trackRule.testTrackPieceLegality( trackGraphicNumber );
    }
    
    public void removeRail( OneTileMoveVector direction ) {
        
    
    //setRail( direction, null );
    }
    
    public IntPoint getPosition() {
        return position;
    }
    
    public int getTrackTypeNumber() {
        return this.trackRule.getRuleNumber();
    }
    
    /** Creates new TrackNode */
    
    public TrackNode( IntPoint position, TrackRule trackRule ) {
        this.position = position;
        this.trackRule = trackRule;
    }
    
    public static int getTrackGraphicNumber( boolean[][] railsList ) {
        int  trackGraphicNumber = 0;
        for( int  y = 0;y < 3;y++ ) {
            for( int  x = 0;x < 3;x++ ) {
                if( railsList[ x ][ y ] == true ) {
                    trackGraphicNumber = trackGraphicNumber | ( 1 << ( 3 * y + x ) );
                }
            }
        }
        return trackGraphicNumber;
    }
}
