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
import freerails.model.finances.Transaction;
import freerails.model.player.FreerailsPrincipal;

/**
 * This {@link Move} adds a {@link Transaction} to a players bank account on the
 * {@link World} object.
 */
public class AddTransactionMove implements Move {

    private static final long serialVersionUID = 3976738055925019701L;
    private final Transaction transaction;
    private final FreerailsPrincipal principal;

    /**
     * Whether the move fails if there is not enough cash.
     */
    private final boolean cashConstrained;

    /**
     * @param account
     * @param transaction
     */
    public AddTransactionMove(FreerailsPrincipal account, Transaction transaction) {
        principal = account;
        this.transaction = Utils.verifyNotNull(transaction);
        cashConstrained = false;
    }

    /**
     * @param account
     * @param transaction
     * @param constrain
     */
    public AddTransactionMove(FreerailsPrincipal account, Transaction transaction, boolean constrain) {
        principal = account;
        this.transaction = Utils.verifyNotNull(transaction);
        cashConstrained = constrain;
    }

    /**
     * @return
     */
    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public int hashCode() {
        int result;
        result = transaction.hashCode();
        result = 29 * result + principal.hashCode();
        result = 29 * result + (cashConstrained ? 1 : 0);

        return result;
    }

    public MoveStatus tryDoMove(World world, FreerailsPrincipal principal) {
        if (world.isPlayer(this.principal)) {
            if (cashConstrained) {
                // TODO Money arithmetics
                long bankBalance = world.getCurrentBalance(this.principal).amount;
                long transactionAmount = transaction.price().amount;
                long balanceAfter = bankBalance + transactionAmount;

                if (transactionAmount < 0 && balanceAfter < 0) {
                    return MoveStatus.moveFailed("You can't afford that!");
                }
            }

            return MoveStatus.MOVE_OK;
        }
        return MoveStatus.moveFailed(principal.getName() + " does not have a bank account.");
    }

    public MoveStatus tryUndoMove(World world, FreerailsPrincipal principal) {
        int size = world.getNumberOfTransactions(this.principal);

        if (0 == size) {
            return MoveStatus.moveFailed("No transactions to remove!");
        }

        Transaction lastTransaction = world.getTransaction(this.principal, size - 1);

        if (lastTransaction.equals(transaction)) {
            return MoveStatus.MOVE_OK;
        }
        return MoveStatus.moveFailed("Expected " + transaction + "but found " + lastTransaction);
    }

    public MoveStatus doMove(World world, FreerailsPrincipal principal) {
        MoveStatus moveStatus = tryDoMove(world, principal);

        if (moveStatus.succeeds()) {
            world.addTransaction(this.principal, transaction);
        }

        return moveStatus;
    }

    public MoveStatus undoMove(World world, FreerailsPrincipal principal) {
        MoveStatus moveStatus = tryUndoMove(world, principal);

        if (moveStatus.succeeds()) {
            world.removeLastTransaction(this.principal);
        }

        return moveStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddTransactionMove) {
            AddTransactionMove test = (AddTransactionMove) obj;

            return test.principal.equals(principal) && test.transaction.equals(transaction);
        }
        return false;
    }

    /**
     * @return
     */
    public FreerailsPrincipal getPrincipal() {
        return principal;
    }
}