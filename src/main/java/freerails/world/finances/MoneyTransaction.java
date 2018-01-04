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
package freerails.world.finances;

/**
 * For example, the cost of buying a trains.
 */
public class MoneyTransaction implements Transaction {
    private static final long serialVersionUID = 3258416144497782835L;

    private final Money amount;

    private final TransactionCategory category;

    /**
     * @param amount
     * @param category
     */
    public MoneyTransaction(Money amount, TransactionCategory category) {
        this.amount = new Money(-amount.getAmount());
        this.category = category;
    }

    public Money deltaCash() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MoneyTransaction) {
            MoneyTransaction test = (MoneyTransaction) o;

            return test.amount.equals(amount) && category == test.category;
        }
        return false;
    }

    /**
     * @return
     */
    public TransactionCategory getCategory() {
        return category;
    }

    @Override
    public int hashCode() {
        int result;
        result = amount.hashCode();
        result = 29 * result + category.hashCode();

        return result;
    }
}