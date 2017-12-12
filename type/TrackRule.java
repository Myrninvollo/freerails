package jfreerails.type;
import jfreerails.common.exception.FreerailsException;
import jfreerails.element.TrackNode;
import jfreerails.element.*;

/**
*  Description of the Interface
*
*@author     Luke Lindsay
*@created    09 October 2001
*/


public interface TrackRule {
    
    /**
    *  Gets the aStation attribute of the trackruleinterface object
    *
    *@return    The aStation value
    */
    
    public boolean isAStation();
    
    /**
    *  Description of the Method
    *
    *@param  TerrainType  Description of Parameter
    *@return              Description of the Returned Value
    */
    
    public boolean canBuildOnThisTerrainType( String TerrainType );
    
    /**
    *  Gets the ruleNumber attribute of the trackruleinterface object
    *
    *@return    The ruleNumber value
    */
    
    public int getRuleNumber();
    
    /**
    *  Gets the typeName attribute of the trackruleinterface object
    *
    *@return    The typeName value
    */
    
    public String getTypeName();
    
    /**
    *  A unit test for JUnit
    *
    *@param  trackTemplateToTest     Description of Parameter
    *@return                         Description of the Returned Value    
    */
    
    public boolean testTrackPieceLegality( int trackTemplateToTest ) ;
    
    /**
    *  Gets the maximumConsecutivePieces attribute of the trackruleinterface
    *  object
    *
    *@return    The maximumConsecutivePieces value
    */
    
    public int getMaximumConsecutivePieces();
    
    /**
    *  Gets the legalRoutes attribute of the trackruleinterface object
    *
    *@param  directionComingFrom  Description of Parameter
    *@return                      The legalRoutes value
    */
    
    public jfreerails.misc.OneTileMoveVector[] getLegalRoutes( jfreerails.misc.OneTileMoveVector directionComingFrom );
    
    /**
    *  Gets the aSignalTower attribute of the trackruleinterface object
    *
    *@return    The aSignalTower value
    */
    
    public boolean isASignalTower();
    
    /**
    *  Gets the doubleTrackEnabled attribute of the trackruleinterface object
    *
    *@return    The doubleTrackEnabled value
    */
    
    public boolean isDoubleTrackEnabled();
    
    public TrackNode getTrackRule();
    
    public void setTrackRule(TrackNode trackNode);
}
