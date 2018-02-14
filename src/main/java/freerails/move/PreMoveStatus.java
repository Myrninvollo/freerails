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

package freerails.move;

import java.io.Serializable;

/**
 * Records the success or failure of an attempt to execute a move.
 */
public class PreMoveStatus implements Serializable {

    public static final PreMoveStatus PRE_MOVE_OK = new PreMoveStatus(MoveStatus.MOVE_OK);
    private static final long serialVersionUID = 3978145456646009140L;
    public final MoveStatus moveStatus;

    private PreMoveStatus(MoveStatus moveStatus) {
        this.moveStatus = moveStatus;
    }

    /**
     * @param reason
     * @return
     */
    public static PreMoveStatus failed(String reason) {
        return new PreMoveStatus(MoveStatus.moveFailed(reason));
    }

    /**
     * @param moveStatus
     * @return
     */
    public static Serializable fromMoveStatus(MoveStatus moveStatus) {
        if (moveStatus.succeeds()) {
            return PRE_MOVE_OK;
        }
        return new PreMoveStatus(moveStatus);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PreMoveStatus)) return false;

        final PreMoveStatus preMoveStatus = (PreMoveStatus) obj;

        return moveStatus.equals(preMoveStatus.moveStatus);
    }

    @Override
    public int hashCode() {
        return moveStatus.hashCode();
    }

    /**
     * Avoid creating a duplicate when deserializing.
     */
    private Object readResolve() {
        if (moveStatus.succeeds()) {
            return PRE_MOVE_OK;
        }
        return this;
    }
}