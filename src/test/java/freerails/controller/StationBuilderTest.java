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
package freerails.controller;

import freerails.client.ModelRoot;
import freerails.client.ModelRootImpl;
import freerails.move.MoveExecutor;
import freerails.move.MoveStatus;
import freerails.model.MapFixtureFactory2;
import freerails.move.SimpleMoveExecutor;
import freerails.move.StationBuilder;
import freerails.util.Vec2D;
import freerails.model.terrain.TileTransition;
import freerails.model.world.World;
import junit.framework.TestCase;

/**
 * A Junit test.
 */
public class StationBuilderTest extends TestCase {

    private TrackMoveProducer trackBuilder;
    private StationBuilder stationBuilder;

    /**
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        World world = MapFixtureFactory2.getCopy();
        MoveExecutor moveExecutor = new SimpleMoveExecutor(world, 0);
        ModelRoot modelRoot = new ModelRootImpl();
        trackBuilder = new TrackMoveProducer(moveExecutor, world, modelRoot);
        stationBuilder = new StationBuilder(moveExecutor);
    }

    /**
     *
     */
    public void testBuildStation() {
        stationBuilder.setStationType(stationBuilder.getTrackTypeID("terminal"));
        TileTransition[] track = {TileTransition.EAST, TileTransition.EAST, TileTransition.EAST};

        MoveStatus moveStatus = trackBuilder.buildTrack(new Vec2D(10, 10), track);
        assertTrue(moveStatus.succeeds());
        assertTrue(stationBuilder.tryBuildingStation(new Vec2D(10, 10)).succeeds());
        assertTrue(stationBuilder.tryBuildingStation(new Vec2D(13, 10)).succeeds());

        moveStatus = stationBuilder.buildStation(new Vec2D(10, 10));
        assertTrue(moveStatus.succeeds());

        moveStatus = stationBuilder.buildStation(new Vec2D(13, 10));
        assertFalse(moveStatus.succeeds());
    }

}
