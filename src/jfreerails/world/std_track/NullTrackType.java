/*
 * NullTrackType.java
 *
 * Created on 23 January 2002, 23:13
 */

package jfreerails.world.std_track;
import java.io.ObjectStreamException;
import java.util.Iterator;

import jfreerails.world.flat.OneTileMoveVector;
import jfreerails.world.flat.TrackConfiguration;
/**
 *
 * @author  lindsal
 * @version 
 */
final public class NullTrackType implements jfreerails.world.type.TrackRule {

	public static final int NULL_TRACK_TYPE_RULE_NUMBER = -999;

	private static final NullTrackType nullTrackType = new NullTrackType();

	/** Creates new NullTrackType */
	private NullTrackType() {
	}

	private Object readResolve() throws ObjectStreamException {
		return nullTrackType;
	}

	public static NullTrackType getInstance() {
		return nullTrackType;
	}

	public boolean canBuildOnThisTerrainType(String TerrainType) {
		return true; //No track is possible anywhere.
	}

	public jfreerails.world.flat.OneTileMoveVector[] getLegalRoutes(
		jfreerails.world.flat.OneTileMoveVector directionComingFrom) {
		return new OneTileMoveVector[0];
	}

	public int getMaximumConsecutivePieces() {
		return -1;
	}

	public int getRuleNumber() {
		return NULL_TRACK_TYPE_RULE_NUMBER;
	}

	public String getTypeName() {
		return "NullTrackType";
	}

	public boolean isDoubleTrackEnabled() {
		return false;
	}

	public boolean testTrackPieceLegality(int trackTemplateToTest) {
		if (trackTemplateToTest != 0) {
			return false;
		} else {
			return true;
		}
	}
	public boolean trackPieceIsLegal(TrackConfiguration config) {
		return testTrackPieceLegality(config.getTrackGraphicsNumber());
	}
	public Iterator getLegalConfigurationsIterator() {
		throw new java.lang.UnsupportedOperationException("Method not implemented yet!");
	}
	public TrackPiece getTrackPiece(TrackConfiguration config) {
		throw new java.lang.UnsupportedOperationException("Method not implemented yet!");
	}

}
