
/*
* TrackBuilder.java
*
* Created on 20 July 2001, 00:52
*/
package jfreerails.common.trackmodel;
import jfreerails.common.IntPoint;
import jfreerails.common.trackmodel.TrackRule;
import jfreerails.common.trackmodel.TrackNode;
import jfreerails.common.trackmodel.TrackMap;
import jfreerails.common.exception.FreerailsException;
import jfreerails.common.OneTileMoveVector;
import jfreerails.lib.TextMessageHandler;
import jfreerails.common.TerrainMap;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class TrackBuilder extends java.lang.Object {

    private TrackMap trackMap;

    private TerrainMap terrainMap;

    private TrackRule trackRule;
    
    public void setTrackRule( TrackRule trackRule ) {
        this.trackRule = trackRule;
        TextMessageHandler.sendMessage( trackRule.getTypeName() );
    }
    
    public boolean buildTrack( IntPoint from, OneTileMoveVector trackVector ) throws FreerailsException {
        TrackNode  trackNodeA, trackNodeB;
        TrackRule  trackRuleA, trackRuleB;
        IntPoint  pointA = from;
        IntPoint  pointB = new IntPoint( from.getX() + trackVector.getX(), from.getY() + trackVector.getY() );
        trackNodeA = trackMap.getTrackNode( pointA );
        trackNodeB = trackMap.getTrackNode( pointB );
        
        /* If the either of the nodes do not already exist, we need to create them. */
        if( null == trackNodeA ) {
            trackRuleA = this.trackRule;
            trackNodeA = new TrackNode( pointA, trackRuleA );
            String  terrainTypeAtpointA = terrainMap.getTerrainTypeName( pointA.getX(), pointA.getY() );
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
            String  terrainTypeAtpointB = terrainMap.getTerrainTypeName( pointB.getX(), pointB.getY() );
            if( false == trackRule.canBuildOnThisTerrainType( terrainTypeAtpointB ) ) {
                TextMessageHandler.sendMessage( "Cannot build this track type (" + trackRule.getTypeName() + ") on this terrain (" + terrainTypeAtpointB + ")." );
                return false;
            }
        }
        else {
            trackRuleB = trackNodeB.getTrackRule();
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
            TextMessageHandler.sendMessage( "Lay track from " + from.getX() + ", " + from.getY() + trackVector.getVectorName() + ": ok" );
            return true;
        }
        else {
            TextMessageHandler.sendMessage( "Lay track from " + from.getX() + ", " + from.getY() + trackVector.getVectorName() + ": illegal track configuration" );
            return false;
        }
    }
    
    public boolean testBuildTrack( IntPoint from, OneTileMoveVector vector, TrackRule trackRule ) {
        return false;
    }
    
    /** Creates new TrackBuilder */
    
    public TrackBuilder( TrackMap trackMap, TrackRule trackRule, TerrainMap terrainMap ) {
        this.trackMap = trackMap;
        this.trackRule = trackRule;
        this.terrainMap = terrainMap;
    }
    
    public TrackRule getTrackRule() {
        return this.trackRule;
    }
    
    /*Check that if the track we are buliding is diagonal, it does not cross a perpendicular diagonal track. */
    
    private boolean noDiagonalTrackConflicts( IntPoint point, OneTileMoveVector tv ) {
        int  trackTemplate = ( 1 << ( 3 * ( 1 + tv.getY() ) + ( 1 + tv.getX() ) ) );
        int  trackTemplateAbove;
        int  trackTemplateBelow;
        
        //Avoid array-out-of-bounds exceptions.
        if( point.getY() > 0 ) {
            trackTemplateAbove = trackMap.getTrackGraphicNumber( new IntPoint( point.getX(), point.getY() - 1 ) );
        }
        else {
            trackTemplateAbove = 0;
        }
        if( ( point.getY() + 1 ) < trackMap.getHeight() ) {
            trackTemplateBelow = trackMap.getTrackGraphicNumber( new IntPoint( point.getX(), point.getY() + 1 ) );
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
