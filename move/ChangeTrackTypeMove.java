package jfreerails.move;

import java.awt.Point;

import jfreerails.element.TrackNode;
import jfreerails.map.TrackSystem;
import jfreerails.move.AbstractTrackMove;
import jfreerails.move.IllegalMoveFreerailsException;
import jfreerails.move.status.MoveStatus;
import jfreerails.type.TrackRule;
import jfreerails.controller.*;

public class ChangeTrackTypeMove extends AbstractTrackMove {

	private final Point location;
	private final TrackRule oldType;
	private final TrackRule newType;

	
	public ChangeTrackTypeMove(Point pos, TrackRule oldType, TrackRule newType) {
		this.location = pos;
		this.oldType = oldType;
		this.newType = newType;

	}

	public MoveStatus tryDoMove(TrackSystem trackSystem) {
		throw new UnsupportedOperationException();
	}

	public MoveStatus tryUndoMove(TrackSystem trackSystem) {
		throw new UnsupportedOperationException();
	}

	public MoveStatus doMove(TrackSystem trackSystem) {
		return upgradeTrack(trackSystem);
	}

	public MoveStatus undoMove(TrackSystem trackSystem) {
		throw new UnsupportedOperationException();
	}

	private MoveStatus upgradeTrack(TrackSystem trackSystem) {

		TrackNode node = trackSystem.getTrackNode(location);

		MoveStatus moveStatus = tryUpgradeTrack(trackSystem);
		if (moveStatus.isOk()) {
			boolean ok = node.upgrade(newType);
			if (false == ok) {
				return MoveStatus.MOVE_REJECTED;
			}
		}
		return moveStatus;
	}
	private MoveStatus tryUpgradeTrack(TrackSystem trackSystem) {
		TrackNode node = trackSystem.getTrackNode(location);

		if (null == node) {
			return MoveStatus.MOVE_REJECTED;
		}
		if (node.getTrackRule() != oldType) {
			return MoveStatus.MOVE_REJECTED;
		}
		int trackConfiguration = node.getTrackGraphicNumber();
		if (false == newType.testTrackPieceLegality(trackConfiguration)) {
			return MoveStatus.MOVE_REJECTED;
		}
		boolean thisTrackIsAllowedHere =
			trackSystem.trackIsAllowed(location, newType).trackIsAllowed();
		if (false == thisTrackIsAllowedHere) {
			return MoveStatus.MOVE_REJECTED;
		}
		if (node.getTrackRule() == newType) {
			return MoveStatus.MOVE_REJECTED;
		}

		return MoveStatus.MOVE_ACCEPTED;
	}
	
}