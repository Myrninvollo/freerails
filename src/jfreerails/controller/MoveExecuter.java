package jfreerails.controller;

import java.util.LinkedList;
import jfreerails.move.Move;
import jfreerails.move.MoveStatus;
import jfreerails.world.top.World;


/**
 * @version         1.0
 *
 * Perhaps "undo move" should be provided by client rather than server.
 *
 * Requirements for Undo Move:
 * *Move must already have been performed
 * *Undo must not succeed if circumstances have changed such that the undo is
 * not possible.
 * *Undo cannot be performed more than once
 * *Client must receive notification of change.
 * *Order of composite undo must be reversed wrt forwards move.
 * *Undo must only be executable by client which performed the move.
 * *Undos must remain in stack for sufficient time for human to manually perform
 * them
 *
 * TODO for network operation, this class must obtain a mutex on the World
 * whilst it processes moves. Due to the potentially large number of moves,
 * perhaps they should be queued and processed in bunches?
 */
public class MoveExecuter implements UncommittedMoveReceiver {
    private static final int MAX_UNDOS = 10;
    protected final World world;
    protected final MoveReceiver moveReceiver;
    private final LinkedList moveStack = new LinkedList();
    protected Object mutex;

    /**
     * @deprecated
     */
    public MoveExecuter(World w, MoveReceiver mr, Object mutex) {
        world = w;
        moveReceiver = mr;
        this.mutex = mutex;
    }

    /*
     * @see MoveReceiver#processMove(Move)
     */
    public void processMove(Move move) {
        executeMove(move);
    }

    /**
     * Forwards moves after execution. This implementation forwards all
     * successful moves submitted. Subclasses may choose to override this to
     * forward moves differently.
     */
    protected void forwardMove(Move move, MoveStatus status) {
        if (status != MoveStatus.MOVE_OK) {
            System.err.println("Couldn't commit move: " + status.message);

            return;
        }

        if (moveReceiver == null) {
            return;
        }

        moveReceiver.processMove(move);
    }

    /**
     * FIXME clients can undo each others moves
     */
    public void undoLastMove() {
        if (moveStack.size() > 0) {
            Move m = (Move)moveStack.removeLast();
            MoveStatus ms;

            synchronized (mutex) {
                ms = m.undoMove(world);
            }

            if (ms != MoveStatus.MOVE_OK) {
                System.err.println("Couldn't undo move!");

                /* push it back on the stack to prevent further
                 * out-of-order undos */
                moveStack.add(m);
            }

            forwardMove(m, ms);
        } else {
            System.err.println("No moves on stack.");
        }
    }

    public MoveStatus executeMove(Move move) {
        moveStack.add(move);

        if (moveStack.size() > MAX_UNDOS) {
            moveStack.removeFirst();
        }

        MoveStatus ms;

        synchronized (mutex) {
            ms = move.doMove(world);
        }

        forwardMove(move, ms);

        return ms;
    }
}