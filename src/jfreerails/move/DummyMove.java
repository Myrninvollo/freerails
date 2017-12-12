package jfreerails.move;

import jfreerails.world.top.World;
/**
 * This is a move that does nothing, but is useful for the purposes of
 * subclassing. This is just a temporary measure.
 */
public class DummyMove implements Move {
    public MoveStatus tryDoMove(World w) {
	return MoveStatus.MOVE_OK;
    }

    public MoveStatus tryUndoMove(World w) {
	return MoveStatus.MOVE_OK;
    }

    public MoveStatus doMove(World w) {
	return MoveStatus.MOVE_OK;
    }

    public MoveStatus undoMove(World w) {
	return MoveStatus.MOVE_OK;
    }
}

