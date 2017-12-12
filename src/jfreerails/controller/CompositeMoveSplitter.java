package jfreerails.controller;

import jfreerails.move.CompositeMove;
import jfreerails.move.Move;

/**
 * Splits an instance of CompositeMove into its component moves.
 */
public final class CompositeMoveSplitter implements MoveReceiver {

    private MoveReceiver moveReceiver;

    public CompositeMoveSplitter(MoveReceiver mr) {
	moveReceiver = mr;
    }

    public void processMove(Move move) {
	if (move instanceof CompositeMove) {
	
	    Move[] moves = ((CompositeMove) move).getMoves();
	    moveReceiver.processMove(move);
	    for (int i = 0; i < moves.length; i++) {
		
		processMove(moves[i]);
	    }
	} else {
	    moveReceiver.processMove(move);
	}
    }
}
