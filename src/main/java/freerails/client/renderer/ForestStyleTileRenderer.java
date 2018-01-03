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
 * ForestStyleTileView.java
 *
 */
package freerails.client.renderer;

import freerails.client.common.BinaryNumberFormatter;
import freerails.client.common.ImageManager;
import freerails.world.ReadOnlyWorld;
import freerails.world.terrain.TerrainType;

import java.awt.*;
import java.io.IOException;

/**
 * Looks to see whether the tiles to the left and right of the same type when
 * deciding which tile icon to use.
 */
final public class ForestStyleTileRenderer extends
        freerails.client.renderer.AbstractTileRenderer {
    private static final int[] X_LOOK_AT = {-1, 1};

    private static final int[] Y_LOOK_AT = {0, 0};

    /**
     * @param imageManager
     * @param rgbValues
     * @param tileModel
     * @param w
     * @throws IOException
     */
    public ForestStyleTileRenderer(ImageManager imageManager, int[] rgbValues,
                                   TerrainType tileModel, ReadOnlyWorld w) throws IOException {
        super(tileModel, rgbValues, w);
        this.setTileIcons(new Image[4]);

        for (int i = 0; i < this.getTileIcons().length; i++) {
            String fileName = generateRelativeFileName(i);
            this.getTileIcons()[i] = imageManager.getImage(fileName);
        }
    }

    /**
     * @param x
     * @param y
     * @param w
     * @return
     */
    @Override
    public int selectTileIcon(int x, int y, ReadOnlyWorld w) {
        int iconNumber = 0;

        for (int i = 0; i < 2; i++) {
            iconNumber = iconNumber
                    | checkTile(x + X_LOOK_AT[i], y + Y_LOOK_AT[i], w);
            iconNumber = iconNumber << 1;
        }

        iconNumber = iconNumber >> 1;

        return iconNumber;
    }

    /**
     * @param i
     * @return
     */
    @Override
    protected String generateFileNameNumber(int i) {
        return BinaryNumberFormatter.format(i, 2);
    }
}