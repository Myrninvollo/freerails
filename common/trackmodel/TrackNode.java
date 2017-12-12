
/*
* TrackNode.java
*
* Created on 10 July 2001, 12:47
*/
package jfreerails.common.trackmodel;

/** This class encapsulates the type and configuation
* of the track on a square.  For every square on
* which track is layed, an object of this class is
* created.
* @author Luke Lindsay
* @version 0.1
*/


public class TrackNode extends java.lang.Object {

    private TrackRule trackType;

    private boolean[][] railsArrangement = new boolean[ 3 ][ 3 ];

    private java.awt.Point position;
    
    public TrackRule getTrackRule() {
        return this.trackType;
    }
    
    //This value determines which track graphic to display.
    
    public int getTrackGraphicNumber() {
        return getTrackGraphicNumber( railsArrangement );
    }
    
    public int getTrackTypeNumber() {
        return this.trackType.getRuleNumber();
    }
    
    /** Creates new TrackNode */
    
    public TrackNode( java.awt.Point position, TrackRule trackRule ) {
        this.position = position;
        this.trackType = trackRule;
    }
    
    public boolean isOrphaned() {
        final int  orphanedTrackNode = ( 1 << 4 );
        if( this.getTrackGraphicNumber() == orphanedTrackNode ) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public java.awt.Point getPosition() {
        return position;
    }
    
    public boolean testAddRail( jfreerails.common.OneTileMoveVector rail, TrackRule trackRule ) throws jfreerails.common.exception.FreerailsException {
        int  trackGraphicNumber = this.getTrackGraphicNumber();
        trackGraphicNumber = trackGraphicNumber | ( 1 << ( 3 * ( 1 + rail.getY() ) + ( 1 + rail.getX() ) ) );
        trackGraphicNumber = trackGraphicNumber | ( 1 << 4 ); //The centre square!
        return trackRule.testTrackPieceLegality( trackGraphicNumber );
    }
    
    /** Returns a 9-bit value specifying  the track configuration, and
    * hence the appropriate icon, for the track at this node.  E.g.
    * the binary representation of a vertical straight would be:
    * 010
    * 010
    * 010 i.e. 010010010
    * @param railsList The rail list that is used ot generate the track
    * graphic number.
    * @return The track graphic number.
    */
    
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
    
    protected void removeRail( jfreerails.common.OneTileMoveVector direction ) {
        railsArrangement[ 1 + direction.getX() ][ 1 + direction.getY() ] = false;
    }
    
    protected void addRail( jfreerails.common.OneTileMoveVector rail, TrackRule trackRule ) {
        railsArrangement[ 1 ][ 1 ] = true; //The central piece.
        railsArrangement[ 1 + rail.getX() ][ 1 + rail.getY() ] = true;
    }
    
    protected boolean getRail( jfreerails.common.OneTileMoveVector rail ) {
        return railsArrangement[ 1 + rail.getX() ][ 1 + rail.getY() ];
    }
    
    protected boolean upgrade( TrackRule newTrackRule ) throws jfreerails.common.exception.FreerailsException {
        int  trackGraphicNumber = this.getTrackGraphicNumber();
        if( newTrackRule.testTrackPieceLegality( trackGraphicNumber ) ) {
            this.trackType = newTrackRule;
            return true;
        }
        else {
            return false;
        }
    }
}
