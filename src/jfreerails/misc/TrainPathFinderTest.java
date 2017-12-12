package jfreerails.misc;

import java.awt.Dimension;
import java.awt.Point;

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
 * 30-Nov-2002
 * @author Luke Lindsay
 *
 */
public class TrainPathFinderTest extends TestCase {

	TrackTileMap map;
	TrackRule rule;

	protected void setUp() {

		TrackRuleList trackRules = MapFixtureFactory.generateTrackRuleList();
		map = new TrackTileMapImpl(new Dimension(20, 20));
		rule = trackRules.getTrackRule(0);

		addTrack(10, 10, OneTileMoveVector.EAST);
		addTrack(11, 10, OneTileMoveVector.EAST);
		addTrack(12, 10, OneTileMoveVector.EAST);
		addTrack(13, 10, OneTileMoveVector.EAST);
		addTrack(14, 10, OneTileMoveVector.EAST);

		addTrack(12, 10, OneTileMoveVector.SOUTH_EAST);
		addTrack(13, 11, OneTileMoveVector.SOUTH_EAST);

		addTrack(13, 10, OneTileMoveVector.SOUTH_EAST);
		addTrack(14, 11, OneTileMoveVector.SOUTH_EAST);

	}

	private void addTrack(int x, int y, OneTileMoveVector v) {
		ChangeTrackPieceCompositeMove move = ChangeTrackPieceCompositeMove.generateBuildTrackMove(new Point(x, y), v, rule, map);
		move.doMove(map);
	}

	public void testFindPath2() {

		PositionOnTrack position;
		Point target;
		PositionOnTrack nextPosition;

		position = new PositionOnTrack(10, 10, OneTileMoveVector.WEST);
		target = new Point(11, 10);
		nextPosition = new PositionOnTrack(11, 10, OneTileMoveVector.EAST);
		assertNextPositionIs(position, target, nextPosition);

		position = new PositionOnTrack(10, 10, OneTileMoveVector.WEST);
		target = new Point(12, 10);
		nextPosition = new PositionOnTrack(11, 10, OneTileMoveVector.EAST);
		assertNextPositionIs(position, target, nextPosition);

		position = new PositionOnTrack(12, 10, OneTileMoveVector.NORTH_WEST);
		target = new Point(10, 10);
		nextPosition = new PositionOnTrack(11, 10, OneTileMoveVector.WEST);
		assertNextPositionIs(position, target, nextPosition);

		position = new PositionOnTrack(13, 11, OneTileMoveVector.NORTH_WEST);
		target = new Point(10, 10);
		nextPosition = new PositionOnTrack(12, 10, OneTileMoveVector.NORTH_WEST);
		assertNextPositionIs(position, target, nextPosition);

		position = new PositionOnTrack(13, 11, OneTileMoveVector.NORTH_WEST);
		target = new Point(15, 12);
		nextPosition = new PositionOnTrack(12, 10, OneTileMoveVector.NORTH_WEST);
		assertNextPositionIs(position, target, nextPosition);

	}

	/*
	public void testFindPath() {
	
		Point position;
		Point target;
		Point nextPosition;
	
		position = new Point(12, 10);
		target = new Point(11, 10);
		nextPosition = new Point(11, 10);
		assertNextPositionIs(position, target, nextPosition);
	
		position = new Point(12, 10);
		target = new Point(13, 11);
		nextPosition = new Point(13, 11);
		assertNextPositionIs(position, target, nextPosition);
	
		position = new Point(12, 10);
		target = new Point(13, 10);
		nextPosition = new Point(13, 10);
		assertNextPositionIs(position, target, nextPosition);
	
		//find path to current position
		position = new Point(10, 10);
		target = new Point(10, 10);
		nextPosition = new Point(11, 10);
		assertNextPositionIs(position, target, nextPosition);
	
		//find path to current position
		position = new Point(15, 10);
		target = new Point(15, 10);
		nextPosition = new Point(14, 10);
		assertNextPositionIs(position, target, nextPosition);
	
		position = new Point(12, 10);
		target = new Point(14, 12);
		nextPosition = new Point(13, 11);
		assertNextPositionIs(position, target, nextPosition);
	
	}
	*/

	public void testPathExists() {
		//Check that the track is where we think it is ...

		assertCanMove(10, 10, OneTileMoveVector.EAST);
		assertCanMove(11, 10, OneTileMoveVector.EAST);
		assertCanMove(12, 10, OneTileMoveVector.EAST);
		assertCanMove(13, 10, OneTileMoveVector.EAST);
		assertCanMove(14, 10, OneTileMoveVector.EAST);

		assertCanMove(11, 10, OneTileMoveVector.WEST);
		assertCanMove(12, 10, OneTileMoveVector.WEST);
		assertCanMove(13, 10, OneTileMoveVector.WEST);
		assertCanMove(14, 10, OneTileMoveVector.WEST);
		assertCanMove(15, 10, OneTileMoveVector.WEST);

		assertCanMove(12, 10, OneTileMoveVector.SOUTH_EAST);
		assertCanMove(13, 11, OneTileMoveVector.SOUTH_EAST);

		assertCanMove(14, 12, OneTileMoveVector.NORTH_WEST);
		assertCanMove(13, 11, OneTileMoveVector.NORTH_WEST);

		assertCanMove(13, 10, OneTileMoveVector.SOUTH_EAST);
		assertCanMove(14, 11, OneTileMoveVector.SOUTH_EAST);

		assertCanMove(15, 12, OneTileMoveVector.NORTH_WEST);
		assertCanMove(14, 11, OneTileMoveVector.NORTH_WEST);
	}

	/*
	public void assertNextPositionIs(
		Point position,
		Point target,
		Point nextPosition) {
		PositionOnTrack pot =
			NewFlatTrackExplorer.getPossiblePositions(map, position)[0];
		NewFlatTrackExplorer explorer = new NewFlatTrackExplorer(map, pot);
		TrainPathFinder.setTarget(target.x, target.y);
	
		TrainPathFinder trainPathFinder = new TrainPathFinder(explorer);
		int i = trainPathFinder.nextInt();
		PositionOnTrack next = new PositionOnTrack(i);
	
		assertEquals(nextPosition, new Point(next.getX(), next.getY()));
	
	}
	*/

	public void assertNextPositionIs(PositionOnTrack position, Point target, PositionOnTrack nextPosition) {

		NewFlatTrackExplorer explorer = new NewFlatTrackExplorer(map, position);
		TrainPathFinder.setTarget(target.x, target.y);

		TrainPathFinder trainPathFinder = new TrainPathFinder(explorer);
		int i = trainPathFinder.nextInt();
		PositionOnTrack next = new PositionOnTrack(i);

		assertEquals(nextPosition, next);

	}

	public void assertCanMove(int x, int y, OneTileMoveVector v) {
		PositionOnTrack pot = NewFlatTrackExplorer.getPossiblePositions(map, new Point(x, y))[0];
		NewFlatTrackExplorer explorer = new NewFlatTrackExplorer(map, pot);
		boolean found = false;
		while (explorer.hasNextBranch()) {
			explorer.nextBranch();
			int i = explorer.getBranchPosition();
			PositionOnTrack pot2 = new PositionOnTrack(i);
			if (pot2.getDirection() == v) {
				found = true;
			}
		}
		assertTrue(found);
	}

}
