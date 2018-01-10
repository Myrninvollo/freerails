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

package freerails.server;

import freerails.world.ReadOnlyWorld;
import freerails.world.SKEY;
import freerails.world.World;
import freerails.world.terrain.City;
import freerails.world.terrain.FullTerrainTile;
import freerails.world.terrain.TerrainCategory;
import freerails.world.terrain.TerrainType;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/**
 * Lets the server analyse and alter cities.
 */
class CityModel {

    final List<CityTile> urbanCityTiles = new ArrayList<>();
    final List<CityTile> industryCityTiles = new ArrayList<>();
    final List<TerrainType> industriesNotAtCity = new ArrayList<>();
    final List<CityTile> resourceCityTiles = new ArrayList<>();
    final List<Point> clearTiles = new ArrayList<>();
    /**
     * The number of stations within this city's bounds.
     */
    int stations = 0;

    void addTile(TerrainType type) {
        Random rand = new Random();

        // Pick a spot at random at which to place the tile.
        if (clearTiles.size() > 0) {
            int tilePos = rand.nextInt(clearTiles.size());
            Point p = clearTiles.remove(tilePos);

            if (type.getCategory().equals(TerrainCategory.Urban)) {
                urbanCityTiles.add(new CityTile(p, type));
            } else if (type.getCategory().equals(TerrainCategory.Industry)) {
                industryCityTiles.add(new CityTile(p, type));
                industriesNotAtCity.remove(type);
            } else if (type.getCategory().equals(TerrainCategory.Country)) {
                throw new IllegalArgumentException(
                        "call remove(.) to replace a city tile with a country tile!");
            } else if (type.getCategory().equals(TerrainCategory.Resource)) {
                resourceCityTiles.add(new CityTile(p, type));
            }
        }
    }

    void loadFromMap(ReadOnlyWorld w, int cityID) {
        /* Reset lists of tiles. */
        urbanCityTiles.clear();
        industryCityTiles.clear();
        clearTiles.clear();
        resourceCityTiles.clear();

        /* Set up the list of industries not at the city. */
        industriesNotAtCity.clear();

        for (int i = 0; i < w.size(SKEY.TERRAIN_TYPES); i++) {
            TerrainType type = (TerrainType) w.get(SKEY.TERRAIN_TYPES, i);

            if (type.getCategory().equals(TerrainCategory.Industry)) {
                industriesNotAtCity.add(type);
            }
        }

        stations = 0;

        /* Identify city's bounds. */
        Rectangle mapRect = new Rectangle(0, 0, w.getMapWidth(), w
                .getMapHeight());
        City city = (City) w.get(SKEY.CITIES, cityID);
        Rectangle cityArea = new Rectangle(city.getX() - 3,
                city.getY() - 3, 7, 7);
        cityArea = cityArea.intersection(mapRect);

        /* Count tile types. */
        for (int x = cityArea.x; x < cityArea.x + cityArea.width; x++) {
            for (int y = cityArea.y; y < cityArea.y + cityArea.height; y++) {
                FullTerrainTile tile = (FullTerrainTile) w.getTile(x, y);

                /* Count the number of stations at the city. */
                if (tile.getTrackPiece().getTrackRule().isStation()) {
                    stations++;
                }

                int terrainTypeNumber = tile.getTerrainTypeID();
                TerrainType type = (TerrainType) w.get(SKEY.TERRAIN_TYPES,
                        terrainTypeNumber);

                if (type.getCategory().equals(TerrainCategory.Urban)) {
                    urbanCityTiles.add(new CityTile(new Point(x, y), type));
                } else if (type.getCategory().equals(
                        TerrainCategory.Industry)) {
                    industryCityTiles.add(new CityTile(new Point(x, y), type));
                    industriesNotAtCity.remove(type);
                } else if (type.getCategory().equals(
                        TerrainCategory.Country)) {
                    clearTiles.add(new Point(x, y));
                } else if (type.getCategory().equals(
                        TerrainCategory.Resource)) {
                    resourceCityTiles.add(new CityTile(new Point(x, y), type));
                }
            }
        }
    }

    int size() {
        return urbanCityTiles.size() + industryCityTiles.size()
                + resourceCityTiles.size();
    }

    void writeToMap(World w) {
        for (CityTile urbanCityTile : urbanCityTiles) {
            writeTile(w, urbanCityTile);
        }

        for (CityTile industryCityTile : industryCityTiles) {
            writeTile(w, industryCityTile);
        }

        for (CityTile resourceCityTile : resourceCityTiles) {
            writeTile(w, resourceCityTile);
        }
    }

    private void writeTile(World w, CityTile cityTile) {
        int type = 0;

        while (!w.get(SKEY.TERRAIN_TYPES, type).equals(cityTile.type)) {
            type++;
        }

        int x = cityTile.p.x;
        int y = cityTile.p.y;
        FullTerrainTile fTile = (FullTerrainTile) w.getTile(x, y);
        fTile = FullTerrainTile.getInstance(type, fTile.getTrackPiece());
        w.setTile(x, y, fTile);
    }

}