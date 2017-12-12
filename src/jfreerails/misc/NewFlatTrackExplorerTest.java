package jfreerails.misc;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;

import jfreerails.move.ChangeTrackPieceCompositeMove;
import jfreerails.world.misc.OneTileMoveVector;
import jfreerails.world.track.MapFixtureFactory;
import jfreerails.world.track.PositionOnTrack;
import jfreerails.world.track.TrackRule;
import jfreerails.world.track.TrackRuleList;
import jfreerails.world.track.TrackTileMap;
import jfreerails.world.track.TrackTileMapImpl;
import junit.framework.TestCase;
/**
 * 24-Nov-2002
 * @author Luke Lindsay
 *
 */
public class NewFlatTrackExplorerTest extends TestCase {

	TrackRuleList trackRules;
	TrackTileMap map;

	/**
	 * Constructor for NewFlatTrackExplorerTest.
	 * @param arg0
	 */
	public NewFlatTrackExplorerTest(String arg0) {
		super(arg0);
	}

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

	public void testGetFirstVectorToTry() {
		setUp();
		PositionOnTrack p =
			new PositionOnTrack(10, 10, OneTileMoveVector.SOUTH_WEST);
		NewFlatTrackExplorer fte = new NewFlatTrackExplorer(map, p);
		OneTileMoveVector v = fte.getFirstVectorToTry();
		assertEquals(OneTileMoveVector.EAST, v);
	}

	/** Tests that the track explorer at point 10,10 tells us
	 * that we can move west, east, or northeast.
	*/
	public void testGetPossibleDirections() {
		setUp();
		NewFlatTrackExplorer fte;

		PositionOnTrack p =
			new PositionOnTrack(10, 10, OneTileMoveVector.SOUTH_WEST);
		fte = new NewFlatTrackExplorer(map, p);

		//There should be 3 branches.
		assertTrue(fte.hasNextBranch());
		fte.nextBranch();
		p.setValuesFromInt(fte.getBranchPosition());
		assertEquals(OneTileMoveVector.EAST, p.getDirection());
		assertTrue(fte.hasNextBranch());
		fte.nextBranch();

		p.setValuesFromInt(fte.getBranchPosition());
		assertEquals(OneTileMoveVector.WEST, p.getDirection());

		assertTrue(fte.hasNextBranch());
		fte.nextBranch();
		p.setValuesFromInt(fte.getBranchPosition());
		assertEquals(OneTileMoveVector.NORTH_EAST, p.getDirection());
		assertTrue(!fte.hasNextBranch());

	}
	/** Tests that we can move the track explorer at point 10,10
	 * northeast, and that when we have done this, we can move it back again.
	 */
	public void testMoveTrackExplorer() {
		
		setUp();
		
		NewFlatTrackExplorer fte;
		
		PositionOnTrack p = new PositionOnTrack(10, 10, OneTileMoveVector.EAST);
		fte = new NewFlatTrackExplorer(map, p);
				
		PositionOnTrack pos = new PositionOnTrack(fte.getPosition());
		assertEquals(10, pos.getX());
		assertEquals(10, pos.getY());
		assertTrue(fte.hasNextBranch());
		fte.nextBranch();
		pos.setValuesFromInt(fte.getBranchPosition());
		assertEquals(OneTileMoveVector.NORTH_EAST, pos.getDirection());
		assertEquals(11, pos.getX());
		assertEquals(9, pos.getY());
				
		int branchPosition = fte.getBranchPosition();		
		fte.moveForward();				
		assertEquals(branchPosition, fte.getPosition());
		
		pos.setValuesFromInt(fte.getPosition());
		assertEquals(11, pos.getX());
		assertEquals(9, pos.getY());
		
		assertTrue(fte.hasNextBranch());
		fte.nextBranch();
		assertEquals(OneTileMoveVector.SOUTH_WEST, fte.currentBranch.getDirection());
		assertTrue(!fte.hasNextBranch());
		fte.moveForward();
		pos.setValuesFromInt(fte.getPosition());
		assertEquals(10, pos.getX());
		assertEquals(10, pos.getY());
		
	}

	public void testHasNext() {
		setUp();
		NewFlatTrackExplorer explorer =
			new NewFlatTrackExplorer(
				this.map,
				new PositionOnTrack(10, 10, OneTileMoveVector.EAST));
		assertTrue(explorer.hasNextBranch());
	}

	public void testGetPossiblePositions() {
		setUp();

		PositionOnTrack[] positions =
			NewFlatTrackExplorer.getPossiblePositions(
				this.map,
				new Point(10, 10));
		assertNotNull(positions);
		assertEquals(3, positions.length);
		HashSet directions = new HashSet();
		directions.add(OneTileMoveVector.WEST);
		directions.add(OneTileMoveVector.EAST);
		directions.add(OneTileMoveVector.SOUTH_WEST);

		HashSet directions2 = new HashSet();
		for (int i = 0; i < positions.length; i++) {
			directions2.add(positions[i].getDirection());
		}
		assertEquals(directions, directions2);
	}

}
