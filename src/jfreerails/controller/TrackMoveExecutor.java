package jfreerails.controller;

import java.util.Stack;

import jfreerails.move.Move;
import jfreerails.move.MoveStatus;
import jfreerails.move.TrackMove;
import jfreerails.world.top.World;

/**
 * @version 	1.0
 * 
 */
final public class TrackMoveExecutor implements MoveReceiver {

	private final World world;

	private final MoveReceiver moveReceiver;

	private static final Stack moveStack = new Stack();

	public TrackMoveExecutor(World w, MoveReceiver mr) {
		world = w;
		moveReceiver = mr;
	}

	/*
	 * @see MoveReceiver#processMove(Move)
	 */
	public MoveStatus processMove(Move move) {
		if (move instanceof TrackMove) {
			moveStack.push(move);

			MoveStatus ms = ((TrackMove) move).doMove(world);

			moveReceiver.processMove(move);

			return ms;
		} else {
			System.out.println("is not a track move");
			return MoveStatus.MOVE_RECEIVED;
		}
	}

	public void undoLastMove() {
		if (moveStack.size() > 0) {
			Move m = (Move) moveStack.pop();
			m.undoMove(world);

			moveReceiver.processMove(m);

		} else {
			System.out.println("No moves on stack.");
		}
	}

}