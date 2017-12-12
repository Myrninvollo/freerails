package jfreerails.controller;

import java.util.Stack;

import jfreerails.move.Move;
import jfreerails.move.MoveStatus;
import jfreerails.world.top.World;

/**
 * @version 	1.0
 * 
 */
final public class MoveExecuter implements MoveReceiver {

	private final World world;

	private final MoveReceiver moveReceiver;

	private static final Stack moveStack = new Stack();

	private static MoveExecuter moveExecuter = null;
	
	private MoveExecuter(World w, MoveReceiver mr) {
		world = w;
		moveReceiver = mr;
	}

	/**
	 * Call this once before using this class
	 */
	public static void init(World w, MoveReceiver mr) {
		moveExecuter = new MoveExecuter(w, mr);
	}

	/**
	 * Submit Moves to the MoveExecuter returned from this method for
	 * execution, rather than calling the doMove() method directly.
	 * @return a publicly available MoveExecuter.
	 */
	public static MoveExecuter getMoveExecuter() {
	    return moveExecuter;
	}

	/*
	 * @see MoveReceiver#processMove(Move)
	 */
	public MoveStatus processMove(Move move) {
	    moveStack.push(move);
	    MoveStatus ms = move.doMove(world);
	    moveReceiver.processMove(move);
	    return ms;
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
