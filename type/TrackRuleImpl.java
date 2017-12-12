
/*
*  TrackRule.java
*
*  Created on 15 July 2001, 19:53
*/
package jfreerails.type;
import java.util.HashSet;

import jfreerails.common.exception.FreerailsException;
import jfreerails.constructorparam.TrackRuleImplConstructorParameters;
import jfreerails.element.TrackNode;
import jfreerails.misc.EightRotationsOfTrackPieceProducer;
import jfreerails.misc.OneTileMoveVector;

/**
*  This class encapsulates the rules that apply to a type of track node. They
*  concern: the legal routes trains can travel across the node, whether the
*  node's track can be doubled, on which terrain types it can be built, and the
*  maximum number of consecutive nodes of this type (used for bridges and
*  tunnels).
*
*@author     Luke Lindsay
*@created    09 October 2001
*@version    0.1
*/

public class TrackRuleImpl extends java.lang.Object implements TrackRule {

	///////////////////////////////////////
	// associations

	public TrackNode m_trackNode;

	private final boolean enableDoubleTrack;

	private final boolean station;

	//final private  int[][] legalRoutesAcrossNodeTemplates;
	private final HashSet canOnlyBuildOnTheseTerrainTypes;

	private final int ruleNumber;

	/*
	*  Track templates are 9 bit values, so there are 512 possible templates.
	*  If legalTrackTemplate[x]==true, then x is a legal track-template.
	*  Example:
	*  000
	*  111
	*  000
	*  This represents a horizontal straight.
	*/
	private final boolean[] legalTrackTemplates = new boolean[512];

	private final int maximumConsecutivePieces;

	//-1 signifies no maximum.
	private final HashSet cannotBuildOnTheseTerrainTypes;

	private final int rGBvalue;

	private final String typeName;

	private final boolean signalTower;

	///////////////////////////////////////
	// access methods for associations

	public TrackNode getTrackRule() {
		return m_trackNode;
	}

	public void setTrackRule(TrackNode trackNode) {
		if (this.m_trackNode != trackNode) {
			if (this.m_trackNode != null)
				this.m_trackNode.removeUses(this);
			this.m_trackNode = trackNode;
			if (trackNode != null)
				trackNode.addUses(this);
		}
	}
	/**
	*  Gets the typeName attribute of the TrackRule object
	*
	*@return    The typeName value
	*/

	public String getTypeName() {
		return typeName;
	}

	/**
	*  Gets the aStation attribute of the TrackRule object
	*
	*@return    The aStation value
	*/

	public boolean isAStation() {
		return station;
	}

	/**
	*  A unit test for JUnit
	*
	*@param  trackTemplateToTest     Description of Parameter
	*@return                         Description of the Returned Value
	
	*/

	public boolean testTrackPieceLegality(int trackTemplateToTest) {

		//Check the values we have been passed for errors.
		if ((trackTemplateToTest > 511) || (trackTemplateToTest < 0)) {
			throw new java.lang.IllegalArgumentException(
				"trackTemplate = " + trackTemplateToTest + ", it should be in the range 0-511");
		}
		return legalTrackTemplates[trackTemplateToTest];
	}

	/**
	*  Creates new TrackRule
	*
	*@param  trackTemplatesPrototypes
	*@param  legalRoutesAcrossNodeTemplatePrototypes
	*@param  ruleNumber
	*@param  rgb                                      Description of Parameter
	*@param  trackTypeName                            Description of Parameter
	*@param  maximumConsecutivePieces                 Description of Parameter
	*@param  isASignalTower                           Description of Parameter
	*@param  isAStation                               Description of Parameter
	*@param  isDoubleTrackEnabled                     Description of Parameter
	*@param  onlyOnTheseterrainTypes                  Description of Parameter
	*@param  notOnTheseTerrainTypes                   Description of Parameter
	*@exception  FreerailsException                   Description of Exception
	*@throws  FreerailsException
	*/

	public TrackRuleImpl(TrackRuleImplConstructorParameters trackRuleParams)
		throws FreerailsException {

		this.ruleNumber = trackRuleParams.ruleNumber;
		this.rGBvalue = trackRuleParams.rGBvalue;
		this.typeName = trackRuleParams.typeName;
		this.maximumConsecutivePieces = trackRuleParams.maximumConsecutivePieces;
		this.signalTower = trackRuleParams.signalTower;
		this.station = trackRuleParams.station;
		this.enableDoubleTrack = trackRuleParams.enableDoubleTrack;
		this.canOnlyBuildOnTheseTerrainTypes =
			trackRuleParams.canOnlyBuildOnTheseTerrainTypes;
		this.cannotBuildOnTheseTerrainTypes =
			trackRuleParams.cannotBuildOnTheseTerrainTypes;

		if(null==this.canOnlyBuildOnTheseTerrainTypes&&null==this.cannotBuildOnTheseTerrainTypes){
			throw new IllegalArgumentException("Both cannotBuildOnTheseTerrainTypes and canOnlyBuildOnTheseTerrainTypes equals null for track type "
				+ typeName);
		}

		//Iterate over the track templates.	
		for (int i = 0; i < trackRuleParams.legalTrackTemplates.length; i++) {

			int trackTemplate =
				(int) Integer.parseInt(trackRuleParams.legalTrackTemplates[i], 2);

			//  Check for invalid parameters.		
			if ((trackTemplate > 511) || (trackTemplate < 0)) {
				throw new IllegalArgumentException(
					"trackTemplate = " + trackTemplate + ", it should be in the range 0-511");
			}

			int[] rotationsOfTrackTemplate =
				EightRotationsOfTrackPieceProducer.getRotations(trackTemplate);
			for (int k = 0; k < rotationsOfTrackTemplate.length; k++) {

				legalTrackTemplates[rotationsOfTrackTemplate[k]] = true;

			}

		}
	}

	/**
	*  Gets the maximumConsecutivePieces attribute of the TrackRule object
	*
	*@return    The maximumConsecutivePieces value
	*/

	public int getMaximumConsecutivePieces() {
		return maximumConsecutivePieces;
	}

	/**
	*  Gets the legalRoutes attribute of the TrackRule object
	*
	*@param  directionComingFrom  Description of Parameter
	*@return                      The legalRoutes value
	*/

	public OneTileMoveVector[] getLegalRoutes(OneTileMoveVector directionComingFrom) {

		//TODO add code..
		return null;
	}

	/**
	*  Description of the Method
	*
	*@param  TerrainType  Description of Parameter
	*@return              Description of the Returned Value
	*/

	public boolean canBuildOnThisTerrainType(String TerrainType) {
		if (null != canOnlyBuildOnTheseTerrainTypes) {
			if (canOnlyBuildOnTheseTerrainTypes.contains(TerrainType)) {
				return true;
			} else {
				return false;
			}
		}
		if (null != cannotBuildOnTheseTerrainTypes) {
			if (cannotBuildOnTheseTerrainTypes.contains(TerrainType)) {
				return false;
			} else {
				return true;
			}
		}
		System.out.println(
			"Warning: both cannotBuildOnTheseTerrainTypes and canOnlyBuildOnTheseTerrainTypes equals null for track type "
				+ typeName);
		return true;
	}

	/**
	*  Gets the aSignalTower attribute of the TrackRule object
	*
	*@return    The aSignalTower value
	*/

	public boolean isASignalTower() {
		return signalTower;
	}

	/**
	*  Gets the doubleTrackEnabled attribute of the TrackRule object
	*
	*@return    The doubleTrackEnabled value
	*/

	public boolean isDoubleTrackEnabled() {
		return enableDoubleTrack;
	}

	/**
	*  Gets the ruleNumber attribute of the TrackRule object
	*
	*@return    The ruleNumber value
	*/

	public int getRuleNumber() {
		return this.ruleNumber;
	}

}