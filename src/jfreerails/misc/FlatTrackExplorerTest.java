package jfreerails.misc;

import jfreerails.world.track.MapFixtureFactory;
import jfreerails.world.track.PositionOnTrack;
import jfreerails.world.track.TrackRule;
import jfreerails.world.track.TrackRuleList;
import jfreerails.world.track.TrackTileMap;
import jfreerails.world.track.TrackTileMapImpl;
import jfreerails.world.misc.OneTileMoveVector;
import java.awt.Dimension;
import java.awt.Point;

import jfreerails.move.ChangeTrackPieceCompositeMove;
import jfreerails.move.MoveStatus;
import junit.framework.TestCase;

/**
 * This class 
 * @author Luke Lindsay
 */
public class FlatTrackExplorerTest extends TestCase {

	TrackRuleList trackRules;
	TrackTileMap map;

	/**
	 * Constructor for FlatTrackExplorerTest.
	 * @param arg0
	 */
	public FlatTrackExplorerTest(String arg0) {
		super(arg0);

	}
	/** Generates a track map with track going West, East, and North-East from the
	 * point 10,10.
	 * 	 
	 */
	protected void setUp() {
		trackRules = MapFixtureFactory.generateTrackRuleList();
		map = new TrackTileMapImpl(new Dimension(20, 20));
		TrackRule rule = trackRules.getTrackRule(0);

		OneTileMoveVector[] vectors =
			{
				OneTileMoveVector.WEST,
				OneTileMoveVector.EAST,
				OneTileMoveVector.NORTH_EAST };
		Point p = new Point(10, 10);
		Point[] points = { p, p, p };
		for (int i = 0;(i < points.length && i < vectors.length); i++) {
			ChangeTrackPieceCompositeMove move =
				ChangeTrackPieceCompositeMove.generateBuildTrackMove(
					points[i],
					vectors[i],
					rule,
					map);
			move.doMove(map);
		}
	}
	/** Tests that we can place a track explorer on the map
	 * at point 10,10.
	 */
	public void testJumpOntoMap() {
		setUp();
		FlatTrackExplorer fte;
		try {
			fte = new FlatTrackExplorer(map, new Point(10, 10));
			assertNotNull(fte);
			PositionOnTrack pos = fte.getCurrentPosition();
			assertEquals(10, pos.getX());
			assertEquals(10, pos.getY());

		} catch (IllegalArgumentException iae) {
			assertTrue(false);
		}
		try {
			fte = new FlatTrackExplorer(map, new Point(15, 15));
			assertTrue(false);
		} catch (IllegalArgumentException iae) {

		}

	}

	/** Tests that the track explorer at point 10,10 tells us
	 * that we can move west, east, or northeast.
	 */
	public void testGetPossibleDirections() {
		setUp();
		FlatTrackExplorer fte;

		fte = new FlatTrackExplorer(map, new Point(10, 10));

		//There should be 3 branches.
		assertTrue(fte.hasNextBranch());
		fte.nextBranch();
		assertEquals(OneTileMoveVector.NORTH_EAST, fte.currentBranch);
		assertTrue(fte.hasNextBranch());
		fte.nextBranch();
		assertEquals(OneTileMoveVector.EAST, fte.currentBranch);
		assertTrue(fte.hasNextBranch());
		fte.nextBranch();
		assertEquals(OneTileMoveVector.WEST, fte.currentBranch);
		assertTrue(!fte.hasNextBranch());

	}
	/** Tests that we can move the track explorer at point 10,10
	 * northeast, and that when we have done this, we can move it back again.
	 */
	public void testMoveTrackExplorer() {
		setUp();
		FlatTrackExplorer fte;

		fte = new FlatTrackExplorer(map, new Point(10, 10));
		PositionOnTrack pos = fte.getCurrentPosition();
		assertEquals(10, pos.getX());
		assertEquals(10, pos.getY());
		assertTrue(fte.hasNextBranch());
		fte.nextBranch();
		assertEquals(OneTileMoveVector.NORTH_EAST, fte.currentBranch);
		fte.moveForward();		
		pos = fte.getCurrentPosition();
		assertEquals(11, pos.getX());
		assertEquals(9, pos.getY());
		
		assertTrue(fte.hasNextBranch());
		fte.nextBranch();
		assertEquals(OneTileMoveVector.SOUTH_WEST, fte.currentBranch);
		assertTrue(!fte.hasNextBranch());
		fte.moveForward();		
		pos = fte.getCurrentPosition();
		assertEquals(10, pos.getX());
		assertEquals(10, pos.getY());

	}

}
