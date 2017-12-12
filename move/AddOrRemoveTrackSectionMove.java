package jfreerails.move;

import java.awt.Point;

import jfreerails.element.TrackNode;
import jfreerails.element.TrackSection;
import jfreerails.map.TrackSystem;
import jfreerails.misc.OneTileMoveVector;
import jfreerails.move.AbstractTrackMove;
import jfreerails.move.MoveType;
import jfreerails.move.status.MoveStatus;
import jfreerails.controller.*;

public class AddOrRemoveTrackSectionMove extends AbstractTrackMove {

	protected final OneTileMoveVector trackVectorA;
	protected final Point from;
	protected final OneTileMoveVector trackVectorB;
	protected final Point to;
	protected final MoveType moveType;

	public AddOrRemoveTrackSectionMove(
		OneTileMoveVector v1,
		Point from,
		OneTileMoveVector v2,
		Point to,
		MoveType moveType) {
		this.trackVectorA = v1;
		this.from = from;
		this.trackVectorB = v2;
		this.to = to;
		this.moveType = moveType;
	}

	public MoveStatus tryDoMove(TrackSystem trackSystem) {
		if (moveType == MoveType.ADDMOVE) {
			return tryAddTrackSection(trackSystem);
		}else {
			return tryRemoveTrackSection(trackSystem);
		}
	}

	public MoveStatus tryUndoMove(TrackSystem trackSystem) {
		throw new UnsupportedOperationException();
	}

	public MoveStatus doMove(TrackSystem trackSystem) {
		if (moveType == MoveType.ADDMOVE) {
			return addTrackSection(trackSystem);
		} else {
			return removeTrackSection(trackSystem);
		}
	}

	public MoveStatus undoMove(TrackSystem trackSystem) {
		throw new UnsupportedOperationException();
	}

	private MoveStatus addTrackSection(TrackSystem trackSystem) {
		MoveStatus moveStatus = tryAddTrackSection(trackSystem);
		if (moveStatus.isOk()) {
			TrackNode nodeA, nodeB;
			nodeA = trackSystem.getTrackNode(from);
			nodeB = trackSystem.getTrackNode(to);

			TrackSection trackSection =
				new TrackSection(nodeA, trackVectorA, nodeB, trackVectorB);
			nodeA.addTrackSection(trackVectorA, trackSection);
			nodeB.addTrackSection(trackVectorB, trackSection);
		}
		return moveStatus;
	}
	private MoveStatus tryAddTrackSection(TrackSystem trackSystem) {
		TrackNode nodeA, nodeB;
		nodeA = trackSystem.getTrackNode(from);
		nodeB = trackSystem.getTrackNode(to);

		if (null == nodeA || null == nodeB) {
			return new MoveStatus(false, "No track node available to add track to.");
		}
		if (null != nodeA.getTrackSection(trackVectorA)
			|| null != nodeB.getTrackSection(trackVectorB)) {
			return new MoveStatus(false, "Already track here.");
		}
		if (nodeA.addingTrackSectionIsLegal(trackVectorA)
			&& nodeB.addingTrackSectionIsLegal(trackVectorB)) {
			return MoveStatus.MOVE_ACCEPTED;

		} else {
			return MoveStatus.MOVE_REJECTED;
		}

	}
	private MoveStatus tryRemoveTrackSection(TrackSystem trackSystem) {
		TrackNode nodeA, nodeB;
		nodeA = trackSystem.getTrackNode(from);
		nodeB = trackSystem.getTrackNode(to);

		if (null == nodeA || null == nodeB) {
			System.out.println("null node");
			return MoveStatus.MOVE_REJECTED;
		}
		if (null == nodeA.getTrackSection(trackVectorA)
			|| null == nodeB.getTrackSection(trackVectorB)) {
				System.out.println("null sectiojn");
			return MoveStatus.MOVE_REJECTED;
		
		} else {
			return MoveStatus.MOVE_ACCEPTED;
		}

	}

	private MoveStatus removeTrackSection(TrackSystem trackSystem) {

		if (null == trackSystem.getTrackNode(from)
			|| null == trackSystem.getTrackNode(to)) {
			return new MoveStatus(false, "No track section to remove");
		}
		TrackNode nodeA = trackSystem.getTrackNode(from);
		TrackSection trackSection = nodeA.getTrackSection(trackVectorA);
		if (null == trackSection) {
			return new MoveStatus(false, "No track section to remove");
		}
		trackSection.destroy();
		return MoveStatus.MOVE_ACCEPTED;
	}
}