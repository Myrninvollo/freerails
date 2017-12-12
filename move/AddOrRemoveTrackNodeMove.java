package jfreerails.move;

import java.awt.Point;

import jfreerails.element.TrackNode;
import jfreerails.enum.TrackRuleAtPoint;
import jfreerails.map.TrackSystem;
import jfreerails.move.status.MoveStatus;
import jfreerails.type.TrackRule;

public class AddOrRemoveTrackNodeMove extends AbstractTrackMove {

	protected final Point location;

	protected final TrackRule type;

	protected final MoveType moveType;

	public AddOrRemoveTrackNodeMove(
		Point position,
		TrackRule type,
		MoveType moveType) {
		if (null == position || null == type || null == moveType) {
			throw new IllegalArgumentException("null pointer");
		}
		this.location = position;
		this.type = type;
		this.moveType = moveType;

	}
	public MoveStatus tryDoMove(TrackSystem trackSystem) {
		if (MoveType.ADDMOVE == moveType) {
			return testaddTrackNode(trackSystem);
		} else {
			return testRemoveTrackNode(trackSystem);
		}
	}

	public MoveStatus tryUndoMove(TrackSystem trackSystem) {
		throw new UnsupportedOperationException();

	}

	public MoveStatus doMove(TrackSystem trackSystem) {
		if (MoveType.ADDMOVE == moveType) {
			return addTrackNode(trackSystem);
		} else {
			return removeTrackNode(trackSystem);

		}
	}

	public MoveStatus undoMove(TrackSystem trackSystem) {
		throw new UnsupportedOperationException();
	}

	private MoveStatus addTrackNode(TrackSystem trackSystem) {
		MoveStatus moveStatus = testaddTrackNode(trackSystem);

		boolean moveIsOk = moveStatus.isOk();
		if (moveIsOk) {
			TrackNode trackNode = new TrackNode(location, type);
			trackSystem.addTrackNode(location, trackNode);

		}

		return moveStatus;

	}

	private MoveStatus testaddTrackNode(TrackSystem trackSystem) {
		if (null != trackSystem.getTrackNode(location)) {
			return new MoveStatus(false, "Couldn't add tracknode since there is already one here.");
		}

		TrackRuleAtPoint canBuildHere = trackSystem.trackIsAllowed(location, type);
		if (!canBuildHere.trackIsAllowed()) {
			return new MoveStatus(false, "This track type is not allowed on this terrain.");
		}
		return MoveStatus.MOVE_ACCEPTED;
	}
	private MoveStatus testRemoveTrackNode(TrackSystem trackSystem) {
		TrackNode node = trackSystem.getTrackNode(location);
		if (null == node) {
			return new MoveStatus(false, "No track node to remove.");
		}
		if (node.getTrackRule() != type) {
			return new MoveStatus(false, "Unexpected track type, couldn't remove node.");
		}

		return MoveStatus.MOVE_ACCEPTED;
	}

	private MoveStatus removeTrackNode(TrackSystem trackSystem) {

		TrackNode trackNode = trackSystem.getTrackNode(location);
		if (null == trackNode) {
			return new MoveStatus(false, "No track to remove");
		}
		if (type != trackNode.getTrackRule()) {
			return new MoveStatus(false, "Unexpected track type, couldn't remove");
		}

		trackSystem.removeTrackNode(location);
		return MoveStatus.MOVE_ACCEPTED;

	}

}