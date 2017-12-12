
/*
* TrackRule.java
*
* Created on 15 July 2001, 19:53
*/
package jfreerails.common.trackmodel;

import jfreerails.common.OneTileMoveVector;
import jfreerails.common.TileModel;
import jfreerails.common.exception.FreerailsException;
import jfreerails.common.IntPoint;
import jfreerails.common.trackmodel.EightRotationsOfTrackPieceProducer;

/**
* This class encapsulates the rules that apply to a type of track node.  
* They concern: the legal routes trains can travel across the node, whether 
*the node's track can be doubled, on which terrain types it can be built, and the
*maximum number of consecutive nodes of this type (used for bridges and tunnels).
*
* @author  Luke Lindsay
* @version 
*/


public class TrackRule extends java.lang.Object {

    public int[][] legalRoutesAcrossNodeTemplates;

    private TileModel[] canOnlyBuildOnTheseTerrainTypes = null;

    private boolean signalTower = false;

    private TrackNode type;

    
    /*Track templates are 9 bit values.
    */
    private boolean[] legalTrackTemplates = new boolean[ 512 ];

    
    //If legalTrackTemplate[x]==true, then x is a legal track-template.
    private boolean enableDoubleTrack;

    private int maximumConsecutivePieces = 0; //0 signifies no maximum.  

    private TileModel[] cannotBuildOnTheseTerrainTypes = null;
    
    public int getMaximumConsecutivePieces() {
        return maximumConsecutivePieces;
    }
    
    public boolean IsASignalTower() {
        return signalTower;
    }
    
    public boolean isDoubleTrackEnabled() {
        return enableDoubleTrack;
    }
    
    public boolean testTrackPieceLegality( int trackTemplateToTest ) throws FreerailsException {
        
        //Check the values we have been passed for errors.
        if( ( trackTemplateToTest > 511 ) || ( trackTemplateToTest < 0 ) ) {
            throw new FreerailsException( "trackTemplate = " + trackTemplateToTest + ", it should be in the range 0-511" );
        }
        return legalTrackTemplates[ trackTemplateToTest ];
    }
    
    public void setMaximumConsecutivePieces( int maximumConsecutivePieces ) {
        this.maximumConsecutivePieces = maximumConsecutivePieces;
    }
    
    public boolean canBuildOnThisTerrainType( TileModel TerrainType ) {
        
        //TODO add code..
        return true;
    }
    
    public void setCannotBuildOnTheseTerrainTypes( TileModel[] terrainTypes ) {
        cannotBuildOnTheseTerrainTypes = terrainTypes;
    }
    
    public void setCanOnlyBuildOnTheseTerrainTypes( TileModel[] terrainTypes ) {
        canOnlyBuildOnTheseTerrainTypes = terrainTypes;
    }
    
    public OneTileMoveVector[] getLegalRoutes( OneTileMoveVector directionComingFrom ) {
        
        //TODO add code..
        return null;
    }
    
    /** Creates new TrackRule */
    
    public TrackRule(int[] trackTemplatesPrototypes /*, int[][] legalRoutesAcrossNodeTemplatePrototypes*/ ) throws FreerailsException {
        
        
        for(int i=0;i<trackTemplatesPrototypes.length;i++){
            
            /* Check for invalid parameters. */
            if( ( trackTemplatesPrototypes[i]>511 ) || ( trackTemplatesPrototypes[i] < 0 ) ) {
                throw new FreerailsException( "trackTemplate = " + trackTemplatesPrototypes[i] + ", it should be in the range 0-511" );
            }
        
        //TODO uncomment and add code.
        //for( int  k = 0;k < legalRoutesAcrossNodeTemplates.length;k++ ) {
        //    if( legalRoutesAcrossNodeTemplatePrototypes[ k ] != ( legalRoutesAcrossNodeTemplatePrototypes[ k ] & trackTemplatesPrototypes[i]  ) ) {
        //        throw new FreerailsException( "Route template: " + legalRoutesAcrossNodeTemplates[ k ] + " is not a subset of the track template: " + trackTemplate + "  It should be!" );
        //    }
       //}
      
            for (int j=0;j<trackTemplatesPrototypes.length;j++){
                int [] rotationsOfTrackTemplate=EightRotationsOfTrackPieceProducer.getRotations(trackTemplatesPrototypes[j]);
               
                for (int k=0;k<rotationsOfTrackTemplate.length;k++){
                    if(legalTrackTemplates[rotationsOfTrackTemplate[k]]==false){
                        legalTrackTemplates[rotationsOfTrackTemplate[k]]=true;
                        
                        }
                }
               
            
        }
            
        
    }
    }
        
    public void setSignalTower( boolean signalTower ) {
        this.signalTower = signalTower;
    }
}
