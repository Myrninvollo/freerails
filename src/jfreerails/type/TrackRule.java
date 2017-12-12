package jfreerails.type;
import java.util.Iterator;

import jfreerails.world.std_track.TrackConfiguration;
import jfreerails.world.std_track.TrackPiece;
/**
*  Description of the Interface
*
*@author     Luke Lindsay
*@created    09 October 2001
*/

public interface TrackRule {

	boolean canBuildOnThisTerrainType(String TerrainType);

	int getRuleNumber();

	String getTypeName();

	boolean testTrackPieceLegality(int trackTemplateToTest);

	boolean trackPieceIsLegal(TrackConfiguration config);

	int getMaximumConsecutivePieces();

	jfreerails.misc.OneTileMoveVector[] getLegalRoutes(
		jfreerails.misc.OneTileMoveVector directionComingFrom);

	boolean isDoubleTrackEnabled();

	Iterator getLegalConfigurationsIterator();

	TrackPiece getTrackPiece(TrackConfiguration config);

}