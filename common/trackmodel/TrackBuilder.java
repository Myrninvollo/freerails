
/*
* TrackBuilder.java
*
* Created on 20 July 2001, 00:52
*/
package jfreerails.common.trackmodel;
import jfreerails.lib.TextMessageHandler;
import java.awt.Point;
import jfreerails.common.exception.FreerailsException;

/** This class provides  methods that: (1)  receive a requests
* to build track pieces, (2) check that executing these
* requests would result in legal track configurations,
* and (3), if (2) is satisfied, adds the requested track
* pieces to the track map.
*
*
* @author Luke Lindsay
* @version 0.1
*/


public class TrackBuilder extends java.lang.Object {

    public static final int UPGRADE_TRACK = 3;

    public static final int BUILD_TRACK = 1;

    public static final int REMOVE_TRACK = 2;

    private TrackRule trackRule;

    private int trackBuilderMode = BUILD_TRACK;

    private jfreerails.common.FreerailsMap.TerrainMap terrainMap;

    private jfreerails.common.FreerailsMap.TrackMap trackMap;
    
    public boolean buildTrack( Point from, jfreerails.common.OneTileMoveVector trackVector ) throws jfreerails.common.exception.FreerailsException {
        TrackNode  trackNodeA, trackNodeB;
        TrackRule  trackRuleA, trackRuleB;
        Point  pointA = from;
        Point  pointB = new Point( from.x + trackVector.getX(), from.y + trackVector.getY() );
        trackNodeA = trackMap.getTrackNode( pointA );
        trackNodeB = trackMap.getTrackNode( pointB );
        
        /* If the either of the nodes do not already exist, we need to create them. */
        if( null == trackNodeA ) {
            trackRuleA = this.trackRule;
            trackNodeA = new TrackNode( pointA, trackRuleA );
            String  terrainTypeAtpointA = terrainMap.getTerrainTypeName( pointA.x, pointA.y );
            if( false == trackRule.canBuildOnThisTerrainType( terrainTypeAtpointA ) ) {
                TextMessageHandler.sendMessage( "Cannot build this track type (" + trackRule.getTypeName() + ") on this terrain (" + terrainTypeAtpointA + ")." );
                return false;
            }
        
        //TODO add code to check that no track has been built yet.  I.e. must connect to existing track.
        }
        else {
            trackRuleA = trackNodeA.getTrackRule();
        }
        if( null == trackNodeB ) {
            trackRuleB = this.trackRule;
            trackNodeB = new TrackNode( pointB, trackRuleB );
            String  terrainTypeAtpointB = terrainMap.getTerrainTypeName( pointB.x, pointB.y );
            if( false == trackRule.canBuildOnThisTerrainType( terrainTypeAtpointB ) ) {
                TextMessageHandler.sendMessage( "Cannot build this track type (" + trackRule.getTypeName() + ") on this terrain (" + terrainTypeAtpointB + ")." );
                return false;
            }
        }
        else {
            trackRuleB = trackNodeB.getTrackRule();
        }
        if( ( trackNodeA.getRail( trackVector ) == true ) || ( trackNodeB.getRail( trackVector.getOpposite() ) == true ) ) {
            TextMessageHandler.sendMessage( "There is already track here!" );
            return false;
        }
        
        /* We need to check that building this piece of track will result in a legal track configuration.
        and that the track we are buliding does not cross any other track*/
        if( ( trackNodeA.testAddRail( trackVector, trackRuleA ) == true ) && ( trackNodeB.testAddRail( trackVector.getOpposite(), trackRuleB ) == true ) && ( noDiagonalTrackConflicts( pointA, trackVector ) == true ) && ( noDiagonalTrackConflicts( pointB, trackVector.getOpposite() ) == true ) ) {
            trackNodeA.addRail( trackVector, trackRuleA );
            trackNodeB.addRail( trackVector.getOpposite(), trackRuleB );
            
            //If we have just created tracknodeA, we need to add it to the track map.
            if( null == trackMap.getTrackNode( pointA ) ) {
                trackMap.addTrackNode( pointA, trackNodeA );
            }
            
            //If we have just created tracknodeB, we need to add it to the track map.
            if( null == trackMap.getTrackNode( pointB ) ) {
                trackMap.addTrackNode( pointB, trackNodeB );
            }
            TextMessageHandler.sendMessage( "Lay track from " + from.x + ", " + from.y + trackVector.getVectorName() + ": ok" );
            return true;
        }
        else {
            TextMessageHandler.sendMessage( "Lay track from " + from.x + ", " + from.y + trackVector.getVectorName() + ": illegal track configuration" );
            return false;
        }
    }
    
    public TrackBuilder( TrackRule trackRule ) throws FreerailsException {
        if( trackRule == null ) {
            throw new FreerailsException( "Tried to create new TrackBuilder, but trackRule==null" );
        }
        this.trackRule = trackRule;
    }
    
    public void setTrackBuilderToUPGRADE_TRACK() {
        trackBuilderMode = UPGRADE_TRACK;
    }
    
    /** Creates new TrackBuilder */
    
    public TrackBuilder( jfreerails.common.FreerailsMap map, TrackRule trackRule ) throws FreerailsException {
        if( trackRule == null ) {
            throw new FreerailsException( "Tried to create new TrackBuilder, but (map==null)||(trackRule==null)" );
        }
        this.trackMap = map.trackMap;
        this.trackRule = trackRule;
        this.terrainMap = map.terrainMap;
    }
    
    public void setTrackBuilderToBUILD_TRACK() {
        trackBuilderMode = BUILD_TRACK;
    }
    
    /** Gets the track rule that is currently being applied.
    */
    
    public TrackRule getTrackRule() {
        return this.trackRule;
    }
    
    public boolean performAction( Point from, jfreerails.common.OneTileMoveVector trackVector ) throws jfreerails.common.exception.FreerailsException {
        if( trackBuilderMode == BUILD_TRACK ) {
            return buildTrack( from, trackVector );
        }
        if( trackBuilderMode == REMOVE_TRACK ) {
            return removeTrack( from, trackVector );
        }
        if( trackBuilderMode == UPGRADE_TRACK ) {
            return upgradeTrack( new Point( from.x + trackVector.getX(), from.y + trackVector.getY() ) );
        }
        else {
            return false;
        }
    }
    
    public void setTrackBuilderToREMOVE_TRACK() {
        trackBuilderMode = REMOVE_TRACK;
    }
    
    public boolean performAction( Point point ) throws jfreerails.common.exception.FreerailsException {
        if( trackBuilderMode == UPGRADE_TRACK ) {
            return upgradeTrack( point );
        }
        else {
            return false;
        }
    }
    
    public boolean testBuildTrack( Point from, jfreerails.common.OneTileMoveVector vector, TrackRule trackRule ) {
        return false;
    }
    
    public boolean upgradeTrack( Point point ) throws jfreerails.common.exception.FreerailsException {
        TrackNode  trackNode = trackMap.getTrackNode( point );
        if( null == trackNode ) {
            TextMessageHandler.sendMessage( "There is no track here, so cannot upgrade track!" );
            return false;
        }
        if( trackRule.getTypeName().equalsIgnoreCase( trackNode.getTrackRule().getTypeName() ) ) {
            TextMessageHandler.sendMessage( "The track type here is:" + trackRule.getTypeName() + " so no need to upgrade" );
            return false;
        }
        String  terrainType = terrainMap.getTerrainTypeName( point.x, point.y );
        if( false == trackRule.canBuildOnThisTerrainType( terrainType ) ) {
            TextMessageHandler.sendMessage( "Cannot upgrade to this track type (" + trackRule.getTypeName() + ") on this terrain (" + terrainType + ")." );
            return false;
        }
        int  trackGraphicNumber = trackNode.getTrackGraphicNumber();
        if( trackRule.testTrackPieceLegality( trackGraphicNumber ) ) {
            trackNode.upgrade( trackRule );
            TextMessageHandler.sendMessage( "Track upgraded to: " + trackRule.getTypeName() );
            return true;
        }
        else {
            TextMessageHandler.sendMessage( "Couldn't upgrade track to " + trackRule.getTypeName() + ": it would have been an illegal track configuration" );
            return false;
        }
    }
    
    public boolean removeTrack( Point from, jfreerails.common.OneTileMoveVector trackVector ) throws jfreerails.common.exception.FreerailsException {
        TrackNode  trackNodeA, trackNodeB;
        TrackRule  trackRuleA, trackRuleB;
        Point  pointA = from;
        Point  pointB = new Point( from.x + trackVector.getX(), from.y + trackVector.getY() );
        trackNodeA = trackMap.getTrackNode( pointA );
        trackNodeB = trackMap.getTrackNode( pointB );
        if( ( null == trackNodeA ) || ( null == trackNodeB ) ) {
            TextMessageHandler.sendMessage( "No track to remove!" );
            return false;
        }
        if( ( trackNodeA.getRail( trackVector ) == true ) && ( trackNodeB.getRail( trackVector.getOpposite() ) == true ) ) {
            trackNodeA.removeRail( trackVector );
            trackNodeB.removeRail( trackVector.getOpposite() );
            TextMessageHandler.sendMessage( "Removing track from " + from.x + ", " + from.y + trackVector.getVectorName() + ": ok" );
            if( trackNodeA.isOrphaned() ) {
                trackMap.removeTrackNode( pointA );
            }
            if( trackNodeB.isOrphaned() ) {
                trackMap.removeTrackNode( pointB );
            }
            return true;
        }
        if( ( trackNodeA.getRail( trackVector ) == true ) || ( trackNodeB.getRail( trackVector.getOpposite() ) == true ) ) {
            throw new jfreerails.common.exception.FreerailsException( "Trying to remove track, there was a rail at one node but no the other." );
        }
        else {
            TextMessageHandler.sendMessage( "No track to remove from " + from.x + ", " + from.y + trackVector.getVectorName() );
            return false;
        }
    }
    
    /** Sets the current track rule.  E.g. there are different
    * rules governing the track-configurations that are legal for
    * double and single track.
    * @param trackRule The track rule to apply.
    */
    
    public void setTrackRule( TrackRule trackRule ) {
        this.trackRule = trackRule;
        TextMessageHandler.sendMessage( trackRule.getTypeName() );
    }
    
    /*Check that if the track we are buliding is diagonal, it does not cross a perpendicular diagonal track. */
    
    private boolean noDiagonalTrackConflicts( Point point, jfreerails.common.OneTileMoveVector tv ) {
        int  trackTemplate = ( 1 << ( 3 * ( 1 + tv.getY() ) + ( 1 + tv.getX() ) ) );
        int  trackTemplateAbove;
        int  trackTemplateBelow;
        
        //Avoid array-out-of-bounds exceptions.
        if( point.y > 0 ) {
            trackTemplateAbove = trackMap.getTrackGraphicNumber( new Point( point.x, point.y - 1 ) );
        }
        else {
            trackTemplateAbove = 0;
        }
        if( ( point.y + 1 ) < trackMap.getHeight() ) {
            trackTemplateBelow = trackMap.getTrackGraphicNumber( new Point( point.x, point.y + 1 ) );
        }
        else {
            trackTemplateBelow = 0;
        }
        trackTemplateAbove = trackTemplateAbove >> 6;
        trackTemplateBelow = trackTemplateBelow << 6;
        trackTemplate = trackTemplate & ( trackTemplateAbove | trackTemplateBelow );
        if( trackTemplate != 0 ) {
            return false; //There is a clash.
        }
        else {
            return true; //Things are ok.
        }
    }
}
