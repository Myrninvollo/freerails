package jfreerails.move;

import java.awt.Point;
import java.awt.Rectangle;

import jfreerails.element.TrackNode;
import jfreerails.element.TrackSection;
import jfreerails.map.TrackSystem;
import jfreerails.misc.OneTileMoveVector;
import jfreerails.move.status.MoveStatus;
import jfreerails.type.TrackRule;

public class AddOrRemoveTrackPieceCompositeMove
	extends AbstractTrackMove
	implements MapUpdateMove {

	private static final int newTrackPieceTemplate =
		(int) Integer.parseInt("010010000", 2);

	private final AddOrRemoveTrackSectionMove trackSectionMove;
	private final AddOrRemoveTrackNodeMove nodeAMove;
	private final AddOrRemoveTrackNodeMove nodeBMove;

	private AddOrRemoveTrackPieceCompositeMove(
		AddOrRemoveTrackSectionMove trackSectionMove,
		AddOrRemoveTrackNodeMove nodeAMove,
		AddOrRemoveTrackNodeMove nodeBMove) {
		this.trackSectionMove = trackSectionMove;
		this.nodeAMove = nodeAMove;
		this.nodeBMove = nodeBMove;

	}

	public MoveStatus tryDoMove(TrackSystem trackSystem) {
		MoveStatus moveStatus;
		if (MoveType.ADDMOVE == trackSectionMove.moveType) {
			return tryBuildTrackMove(trackSystem);
		} else {
			return tryRemoveTrackMove(trackSystem);
		}

	}

	public MoveStatus tryUndoMove(TrackSystem trackSystem) {
		throw new UnsupportedOperationException();
	}

	public MoveStatus doMove(TrackSystem trackSystem) {

		//Check that the move is ok.
		MoveStatus moveStatus;
		if (MoveType.ADDMOVE == trackSectionMove.moveType) {
			moveStatus = tryBuildTrackMove(trackSystem);
			if (!moveStatus.isOk()) {
				return moveStatus;
			} else {

				if (null != nodeAMove) {
					nodeAMove.doMove(trackSystem);
				}
				if (null != nodeBMove) {
					nodeBMove.doMove(trackSystem);
				}
				trackSectionMove.doMove(trackSystem);
				return MoveStatus.MOVE_ACCEPTED;
			}
		} else {
			moveStatus = tryRemoveTrackMove(trackSystem);

			if (!moveStatus.isOk()) {
				return moveStatus;
			} else {
				trackSectionMove.doMove(trackSystem);
				if (null != nodeAMove) {
					nodeAMove.doMove(trackSystem);
				}
				if (null != nodeBMove) {
					nodeBMove.doMove(trackSystem);
				}

				return MoveStatus.MOVE_ACCEPTED;
			}
		}
	}

	public MoveStatus undoMove(TrackSystem trackSystem) {
		throw new UnsupportedOperationException();
	}
	
	public Rectangle getUpdatedTiles(){
		
		
		Point pointA=trackSectionMove.from;
		Point pointB=trackSectionMove.to;
		int x,y,width, height;
		if(pointA.x<pointB.x){
			x=pointA.x;
			width=pointB.x-pointA.x;
		}else{
			x=pointB.x;
			width=pointA.x-pointB.x;
		}
		if(pointA.y<pointB.y){
			y=pointA.y;
			height=pointB.y-pointA.y;
		}else{
			y=pointB.y;
			height=pointA.y-pointB.y;
		}
		//If either are zero, it implies no tiles need 
		//updating, which is not the case.
		width++;
		height++;	
		return new Rectangle(x,y,width,height);
	}

	private MoveStatus tryBuildTrackMove(TrackSystem trackSystem) {
		Point to = this.trackSectionMove.to;
		Point from = this.trackSectionMove.from;

		TrackNode nodeA = trackSystem.getTrackNode(from);
		TrackNode nodeB = trackSystem.getTrackNode(to);

		if (null != nodeA) {
			//If there is already track here, reject move.
			if (null != nodeA.getTrackSection(this.trackSectionMove.trackVectorA)) {

				return MoveStatus.MOVE_REJECTED;
			}
			if (!nodeA.addingTrackSectionIsLegal(this.trackSectionMove.trackVectorA)) {

				return MoveStatus.MOVE_REJECTED;
			}

		}
		if (null != nodeAMove) {
			MoveStatus nodeAMoveStatus = nodeAMove.tryDoMove(trackSystem);
			if (!nodeAMoveStatus.isOk()) {

				return nodeAMoveStatus;
			}

		}

		if (null != nodeB) {
			//If there is already track here, reject move.
			if (null != nodeB.getTrackSection(this.trackSectionMove.trackVectorB)) {

				return MoveStatus.MOVE_REJECTED;
			}
			if (!nodeB.addingTrackSectionIsLegal(this.trackSectionMove.trackVectorB)) {

				return MoveStatus.MOVE_REJECTED;
			}

		}

		if (null != nodeBMove) {
			MoveStatus nodeBMoveStatus = nodeBMove.tryDoMove(trackSystem);
			if (!nodeBMoveStatus.isOk()) {

				return nodeBMoveStatus;
			}
		}

		if (!noDiagonalTrackConflicts(this.trackSectionMove.from,
			this.trackSectionMove.trackVectorA,
			trackSystem)) {
			return MoveStatus.MOVE_REJECTED;
		}

		if (!noDiagonalTrackConflicts(this.trackSectionMove.from,
			this.trackSectionMove.trackVectorA,
			trackSystem)) {
			return MoveStatus.MOVE_REJECTED;
		}
		return MoveStatus.MOVE_ACCEPTED;
	}

	private MoveStatus tryRemoveTrackMove(TrackSystem trackSystem) {
		Point to = this.trackSectionMove.to;
		Point from = this.trackSectionMove.from;

		TrackNode nodeA = trackSystem.getTrackNode(from);
		TrackNode nodeB = trackSystem.getTrackNode(to);

		if (null == nodeA || null == nodeB) {
			//No track to remove.
			return MoveStatus.MOVE_REJECTED;
		}

		MoveStatus moveStatus = this.trackSectionMove.tryDoMove(trackSystem);
		if (!moveStatus.isOk()) {
			System.out.println("s");
			return moveStatus;
		}

		if (null != nodeAMove) {
			MoveStatus nodeAMoveStatus = nodeAMove.tryDoMove(trackSystem);
			if (!nodeAMoveStatus.isOk()) {
				System.out.println("a");
				return nodeAMoveStatus;
			}

		}

		if (null != nodeBMove) {
			MoveStatus nodeBMoveStatus = nodeBMove.tryDoMove(trackSystem);
			if (!nodeBMoveStatus.isOk()) {
				System.out.println("b");
				return nodeBMoveStatus;
			}
		}

		return MoveStatus.MOVE_ACCEPTED;
	}

	public static AddOrRemoveTrackPieceCompositeMove generateBuildTrack(
		Point from,
		OneTileMoveVector direction,
		TrackRule trackRule,
		TrackSystem trackSystem) {

		if (null == from
			|| null == direction
			|| null == trackRule
			|| null == trackSystem) {
			throw new IllegalArgumentException();
		}
		//Check for errors...
		TrackNode nodeA, nodeB;
		Point to = direction.createRelocatedPoint(from);

		AddOrRemoveTrackSectionMove tempTrackSectionMove;
		AddOrRemoveTrackNodeMove tempNodeAMove = null;
		AddOrRemoveTrackNodeMove tempNodeBMove = null;

		//Add track nodes if necessay...
		nodeA = trackSystem.getTrackNode(from);
		if (null == nodeA) {
			tempNodeAMove = new AddOrRemoveTrackNodeMove(from, trackRule, MoveType.ADDMOVE);
			//trackNodeBuilder.addTrackNode(from, trackRule);
		}
		nodeB = trackSystem.getTrackNode(to);
		if (null == nodeB) {
			tempNodeBMove = new AddOrRemoveTrackNodeMove(to, trackRule, MoveType.ADDMOVE);
			//trackNodeBuilder.addTrackNode(to, trackRule);
		}

		//Add track section...

		tempTrackSectionMove =
			new AddOrRemoveTrackSectionMove(
				direction,
				from,
				direction.getOpposite(),
				to,
				MoveType.ADDMOVE);
		return new AddOrRemoveTrackPieceCompositeMove(
			tempTrackSectionMove,
			tempNodeAMove,
			tempNodeBMove);

	}

	public static AddOrRemoveTrackPieceCompositeMove generateRemoveTrack(
		Point position,
		OneTileMoveVector direction,
		TrackSystem trackSystem) {
		Point pointB = direction.createRelocatedPoint(position);
		OneTileMoveVector directionB = direction.getOpposite();
		TrackNode nodeA, nodeB;
		TrackSection trackSection;

		nodeA = trackSystem.getTrackNode(position);
		nodeB = trackSystem.getTrackNode(pointB);

		AddOrRemoveTrackSectionMove tempTrackSectionMove;
		AddOrRemoveTrackNodeMove tempNodeAMove = null;
		AddOrRemoveTrackNodeMove tempNodeBMove = null;

		if (null != nodeA) {
			if (1 >= nodeA.getNumberOfTrackSections()) {
				tempNodeAMove =
					new AddOrRemoveTrackNodeMove(
						position,
						nodeA.getTrackRule(),
						MoveType.REMOVEMOVE);

			}
		}
		if (null != nodeB) {
			if (1 >= nodeB.getNumberOfTrackSections()) {
				tempNodeBMove =
					new AddOrRemoveTrackNodeMove(pointB, nodeB.getTrackRule(), MoveType.REMOVEMOVE);

			}
		}

		tempTrackSectionMove =
			new AddOrRemoveTrackSectionMove(
				direction,
				position,
				directionB,
				pointB,
				MoveType.REMOVEMOVE);
		return new AddOrRemoveTrackPieceCompositeMove(
			tempTrackSectionMove,
			tempNodeAMove,
			tempNodeBMove);

	}

	private boolean noDiagonalTrackConflicts(
		Point point,
		OneTileMoveVector tv,
		TrackSystem trackSystem) {
		int trackTemplate = (1 << (3 * (1 + tv.getY()) + (1 + tv.getX())));
		int trackTemplateAbove;
		int trackTemplateBelow;

		//Avoid array-out-of-bounds exceptions.
		if (point.y > 0) {
			trackTemplateAbove =
				trackSystem.getTrackGraphicNumber(new Point(point.x, point.y - 1));
		} else {
			trackTemplateAbove = 0;
		}
		if ((point.y + 1) < trackSystem.getHeight()) {
			trackTemplateBelow =
				trackSystem.getTrackGraphicNumber(new Point(point.x, point.y + 1));
		} else {
			trackTemplateBelow = 0;
		}
		trackTemplateAbove = trackTemplateAbove >> 6;
		trackTemplateBelow = trackTemplateBelow << 6;
		trackTemplate = trackTemplate & (trackTemplateAbove | trackTemplateBelow);
		if (trackTemplate != 0) {
			return false;
			//There is a clash.
		} else {
			return true;
			//Things are ok.
		}

	}

}