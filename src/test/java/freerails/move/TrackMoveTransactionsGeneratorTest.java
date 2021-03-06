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

import freerails.model.world.ReadOnlyWorld;
import freerails.move.mapupdatemove.ChangeTrackPieceMove;
import freerails.move.mapupdatemove.TrackMove;
import freerails.util.Vec2D;
import freerails.model.world.FullWorld;
import freerails.model.world.SharedKey;
import freerails.model.world.World;
import freerails.model.player.Player;
import freerails.model.terrain.FullTerrainTile;
import freerails.model.MapFixtureFactory;
import freerails.model.track.TrackConfiguration;
import freerails.model.track.TrackPiece;
import freerails.model.track.TrackPieceImpl;
import freerails.model.track.TrackRule;
import junit.framework.TestCase;

/**
 * Test for TrackMoveTransactionsGenerator.
 */
public class TrackMoveTransactionsGeneratorTest extends TestCase {

    private World world;
    private TrackMoveTransactionsGenerator transactionGenerator;

    /**
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        world = new FullWorld(new Vec2D(20, 20));
        MapFixtureFactory.generateTrackRuleList(world);
        Player player = new Player("test player", 0);
        world.addPlayer(player);
        transactionGenerator = new TrackMoveTransactionsGenerator(world, player.getPrincipal());
    }

    /**
     *
     */
    public void testAddTrackMove() {
        TrackPiece oldTrackPiece;
        TrackPiece newTrackPiece;
        TrackConfiguration newConfig;
        TrackMove trackMove;

        // Try building the simplest piece of track.
        newConfig = TrackConfiguration.getFlatInstance("000010000");
        oldTrackPiece = ((FullTerrainTile) world.getTile(Vec2D.ZERO)).getTrackPiece();

        TrackRule r = (TrackRule) world.get(SharedKey.TrackRules, 0);
        int owner = ReadOnlyWorld.getPlayerIndex(world, MapFixtureFactory.TEST_PRINCIPAL);
        newTrackPiece = new TrackPieceImpl(newConfig, r, owner, 0);
        trackMove = new ChangeTrackPieceMove(oldTrackPiece, newTrackPiece, Vec2D.ZERO);

        Move move = transactionGenerator.addTransactions(trackMove);
        assertNotNull(move);
    }
}