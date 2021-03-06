/*
 * FreeRails
 * Copyright (C) 2000-2018 The FreeRails Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

/*
 *
 */
package freerails.move;

import freerails.util.Utils;
import freerails.model.world.World;
import freerails.model.player.FreerailsPrincipal;

import java.util.ArrayList;
import java.util.List;

/**
 * This Move may be subclassed to create a move composed of a number of
 * component Moves where atomicity of the move is required. This class defines a
 * number of methods which may not be subclassed - all changes must be
 * encapsulated as sub-moves of this move.
 */
public class CompositeMove implements Move {

    private static final long serialVersionUID = 3257289149391517489L;
    private final List<Move> moves;

    /**
     * @param moves
     */
    public CompositeMove(List<Move> moves) {
        this.moves = Utils.immutableList(moves);
    }

    public CompositeMove(Move move, List<Move> moreMoves) {
        moves = new ArrayList<>();
        moves.add(move);
        moves.addAll(moreMoves);
    }

    /**
     * @param moves
     */
    public CompositeMove(Move... moves) {
        this.moves = Utils.immutableList(moves);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CompositeMove)) return false;

        final CompositeMove compositeMove = (CompositeMove) obj;

        return moves.equals(compositeMove.moves);
    }

    /**
     * This method lets sub classes look at the moves.
     */
    public final Move getMove(int i) {
        return moves.get(i);
    }

    @Override
    public int hashCode() {
        // This will do for now.
        return moves.size();
    }

    /**
     * @return
     */
    public final List<Move> getMoves() {
        return moves;
    }

    public MoveStatus tryDoMove(World world, FreerailsPrincipal principal) {
        // Since whether a move later in the list goes through could
        // depend on whether an earlier move has been executed, we need
        // actually execute moves, then undo them to test whether the
        // array of moves can be executed successfully.
        MoveStatus moveStatus = doMove(world, principal);

        if (moveStatus.succeeds()) {
            // We just wanted to see if we could do them so we undo them again.
            undoMoves(world, moves.size() - 1, principal);
        }

        // If its not success, then doMove would have undone the moves so we don't
        // need to undo them.
        return moveStatus;
    }

    public MoveStatus tryUndoMove(World world, FreerailsPrincipal principal) {
        MoveStatus moveStatus = undoMove(world, principal);

        if (moveStatus.succeeds()) {
            redoMoves(world, 0, principal);
        }

        return moveStatus;
    }

    public MoveStatus doMove(World world, FreerailsPrincipal principal) {
        MoveStatus moveStatus = compositeTest(world);

        if (!moveStatus.succeeds()) {
            return moveStatus;
        }

        for (int i = 0; i < moves.size(); i++) {
            moveStatus = moves.get(i).doMove(world, principal);

            if (!moveStatus.succeeds()) {
                // Undo any moves we have already done.
                undoMoves(world, i - 1, principal);

                return moveStatus;
            }
        }

        return moveStatus;
    }

    public MoveStatus undoMove(World world, FreerailsPrincipal principal) {
        MoveStatus moveStatus = MoveStatus.MOVE_OK;

        for (int i = moves.size() - 1; i >= 0; i--) {
            moveStatus = moves.get(i).undoMove(world, principal);

            if (!moveStatus.succeeds()) {
                // Redo any moves we have already undone.
                redoMoves(world, i + 1, principal);

                return moveStatus;
            }
        }

        return moveStatus;
    }

    private void undoMoves(World world, int number, FreerailsPrincipal principal) {
        for (int i = number; i >= 0; i--) {
            MoveStatus moveStatus = moves.get(i).undoMove(world, principal);

            if (!moveStatus.succeeds()) {
                throw new IllegalStateException(moveStatus.getMessage());
            }
        }
    }

    private void redoMoves(World world, int number, FreerailsPrincipal principal) {
        for (int i = number; i < moves.size(); i++) {
            MoveStatus moveStatus = moves.get(i).doMove(world, principal);

            if (!moveStatus.succeeds()) {
                throw new IllegalStateException(moveStatus.getMessage());
            }
        }
    }

    /**
     * Subclasses may override this method to perform tests which pass or fail
     * depending on the combination of moves making up this composite move.
     */
    public MoveStatus compositeTest(World world) {
        return MoveStatus.MOVE_OK;
    }

    /**
     * @return
     */
    public int size() {
        return moves.size();
    }

    @Override
    public final String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < moves.size(); i++) {
            s.append(moves.get(i).toString()).append((i > 0) ? ", " : "");
        }

        return s.toString();
    }
}