package jfreerails.type;
import java.util.Iterator;

import jfreerails.world.flat.TrackConfiguration;
import jfreerails.world.std_track.TrackPiece;

import experimental.FreerailsSerializable;
/**
*  Description of the Interface
*
*@author     Luke Lindsay
*@created    09 October 2001
*/

public interface TrackRule extends FreerailsSerializable {

	boolean canBuildOnThisTerrainType(String TerrainType);

	int getRuleNumber();

	String getTypeName();

	boolean testTrackPieceLegality(int trackTemplateToTest);

	boolean trackPieceIsLegal(TrackConfiguration config);

	int getMaximumConsecutivePieces();

	jfreerails.world.flat.OneTileMoveVector[] getLegalRoutes(
		jfreerails.world.flat.OneTileMoveVector directionComingFrom);

	boolean isDoubleTrackEnabled();

	Iterator getLegalConfigurationsIterator();

	TrackPiece getTrackPiece(TrackConfiguration config);

}