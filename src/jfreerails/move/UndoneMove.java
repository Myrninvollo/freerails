package jfreerails.move;

import jfreerails.world.player.FreerailsPrincipal;
import jfreerails.world.top.World;


/**
 * Specifies a move that has been undone by the MoveExecuter. This move has
 * already been executed and thus all attempts to try/perform the move will
 * fail.
 */
public class UndoneMove implements Move {
    private Move undoneMove;

    /**
     * @param move The move that was undone
     */
    public UndoneMove(Move move) {
        undoneMove = move;
    }

    public MoveStatus tryDoMove(World w, FreerailsPrincipal p) {
        return MoveStatus.moveFailed(this.getClass().getName());
    }

    public MoveStatus tryUndoMove(World w, FreerailsPrincipal p) {
        return MoveStatus.moveFailed(this.getClass().getName());
    }

    public MoveStatus doMove(World w, FreerailsPrincipal p) {
        return MoveStatus.moveFailed(this.getClass().getName());
    }

    public MoveStatus undoMove(World w, FreerailsPrincipal p) {
        return MoveStatus.moveFailed(this.getClass().getName());
    }

    public Move getUndoneMove() {
        return undoneMove;
    }
}