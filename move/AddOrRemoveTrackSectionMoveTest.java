package jfreerails.move;

import java.awt.Point;

import jfreerails.element.TrackSection;
import jfreerails.list.TrackRuleList;
import jfreerails.map.FreerailsMap;
import jfreerails.map.TrackSystem;
import jfreerails.misc.OneTileMoveVector;
import jfreerails.move.IllegalMoveFreerailsException;
import jfreerails.move.MoveType;
import jfreerails.move.status.MoveStatus;
import jfreerails.type.TrackRule;
import jfreerails.unittest.fixture.MapFixtureFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import jfreerails.controller.*;

public class AddOrRemoveTrackSectionMoveTest extends TestCase {

	private FreerailsMap map;
	private TrackRuleList trackRuleList;

	private TrackSystem trackSystem;

	public AddOrRemoveTrackSectionMoveTest(String name) {
		super(name);
	}

	protected void setUp() {

		MapFixtureFactory mapFactory = new MapFixtureFactory();
		trackRuleList = mapFactory.trackRuleList;
		map = mapFactory.generateMap();

		trackSystem = map.getTrackMap();

		//trackNodeBuilder = new TrackNodeBuilder(map.getTrackMap());
		//trackSectionBuilder = new TrackSectionBuilder(map.getTrackMap());

	}

	protected void addTrackNodes() {

		addTrackNode(new Point(0, 0), trackRuleList.getTrackRule(0));
		addTrackNode(new Point(0, 1), trackRuleList.getTrackRule(0));
		addTrackNode(new Point(1, 0), trackRuleList.getTrackRule(0));
		addTrackNode(new Point(1, 1), trackRuleList.getTrackRule(0));

	}
	protected void removeTrackNodes() {

		trackSystem.removeTrackNode(new Point(0, 0));
		trackSystem.removeTrackNode(new Point(0, 1));
		trackSystem.removeTrackNode(new Point(1, 0));
		trackSystem.removeTrackNode(new Point(1, 1));

	}

	public static Test suite() {
		TestSuite suite = new TestSuite(AddOrRemoveTrackSectionMoveTest.class);
		return suite;
	}
	public void testAddTrackSection() {

		checkThatTrackSectionsGetsAdded();
		checkThatTrackConfigIsLegal();

	}
	private void checkThatTrackSectionsGetsAdded() {

		addTrackNodes();
		OneTileMoveVector se = OneTileMoveVector.SOUTH_EAST;
		Point pointA = new Point(0, 0);
		Point pointB = se.createRelocatedPoint(pointA);

		TrackSection trackA, trackB;

		//Check that there is no track where there shouldn't be.
		trackA = getTrackSection(pointA, se);
		trackB = getTrackSection(pointB, se.getOpposite());

		assertTrue(null == trackA);
		assertTrue(null == trackB);

		try {
			addTrackSection(se, pointA, se.getOpposite(), pointB);
		} catch (jfreerails.move.IllegalMoveFreerailsException e) {
			assertTrue(false);
		}

		//Check that there is  track where there we have just built it.
		trackA = getTrackSection(pointA, se);
		trackB = getTrackSection(pointB, se.getOpposite());
		assertTrue(null != trackA);
		assertTrue(null != trackB);
		assertTrue(trackA == trackB);

		//Try add track-sections that are already present.. should fail.
		try {
			addTrackSection(se, pointA, se.getOpposite(), pointB);
			assertTrue(false);
		} catch (jfreerails.move.IllegalMoveFreerailsException e) {
			assertTrue(true);
		}

		removeTrackNodes();

		//Try adding track-sections to a node that does not exist: should fail.
		try {
			addTrackSection(se, pointA, se.getOpposite(), pointB);
			assertTrue(false);
		} catch (jfreerails.move.IllegalMoveFreerailsException e) {
			assertTrue(true);
		}

	}

	private void checkThatTrackConfigIsLegal() {
		addTrackNodes();
		OneTileMoveVector se = OneTileMoveVector.SOUTH_EAST;
		OneTileMoveVector north = OneTileMoveVector.NORTH;

		Point pointA = new Point(0, 0);
		Point pointB = se.createRelocatedPoint(pointA);

		TrackSection trackA, trackB;

		//Check that there is no track where there shouldn't be.
		trackA = getTrackSection(pointA, se);
		trackB = getTrackSection(pointB, se.getOpposite());

		assertTrue(null == trackA);
		assertTrue(null == trackB);
		try {
			addTrackSection(se, pointA, se.getOpposite(), pointB);
		} catch (jfreerails.move.IllegalMoveFreerailsException e) {
			assertTrue(false);
		}

		//Check that there is  track where there we have just built it.
		trackA = getTrackSection(pointA, se);
		trackB = getTrackSection(pointB, se.getOpposite());
		assertTrue(null != trackA);
		assertTrue(null != trackB);
		assertTrue(trackA == trackB);
		try {
			addTrackSection(north, pointA, north, pointB);
			assertTrue(false);
		} catch (IllegalMoveFreerailsException e) {
			assertTrue(true);
		}
		trackA = getTrackSection(pointA, north);
		trackB = getTrackSection(pointB, north);
		assertTrue(null == trackA);
		assertTrue(null == trackB);

		removeTrackNodes();

	}

	protected TrackSection getTrackSection(Point point, OneTileMoveVector v) {
		TrackSection ts = trackSystem.getTrackNode(point).getTrackSection(v);
		return ts;
	}
	public void testRemoveTrackSection() {
		try {
			addTrackNodes();
			OneTileMoveVector se = OneTileMoveVector.SOUTH_EAST;
			Point pointA = new Point(0, 0);
			Point pointB = se.createRelocatedPoint(pointA);

			TrackSection trackA, trackB;

			addTrackSection(se, pointA, se.getOpposite(), pointB);

			//Check that there is  track where there we have just built it.
			trackA = getTrackSection(pointA, se);
			trackB = getTrackSection(pointB, se.getOpposite());
			assertTrue(null != trackA);
			assertTrue(null != trackB);
			assertTrue(trackA == trackB);

			removeTrackSection(se, pointA, se.getOpposite(), pointB);

			//Check that there is no track where there shouldn't be.
			trackA = getTrackSection(pointB, se);
			trackB = getTrackSection(pointA, se.getOpposite());
			assertTrue(null == trackA);
			assertTrue(null == trackB);

			//Try removing track-sections that are not present.. should fail.
			try {
				removeTrackSection(se, pointA, se.getOpposite(), pointB);
				assertTrue(false);
			} catch (jfreerails.move.IllegalMoveFreerailsException e) {
				assertTrue(true);
			}

			removeTrackNodes();

			//Try removing track-sections from a node that does not exist: should fail.
			try {
				removeTrackSection(se, pointA, se.getOpposite(), pointB);
				assertTrue(false);
			} catch (jfreerails.move.IllegalMoveFreerailsException e) {
				assertTrue(true);
			}

		} catch (jfreerails.move.IllegalMoveFreerailsException e) {
			assertTrue(false);
		}

	}
	private void addTrackNode(Point point, TrackRule rule) {
		AddOrRemoveTrackNodeMove addTrackNodeMove = new AddOrRemoveTrackNodeMove(point, rule, MoveType.ADDMOVE);
		addTrackNodeMove.doMove(trackSystem);
	}
	private void addTrackSection(
		OneTileMoveVector v1,
		Point from,
		OneTileMoveVector v2,
		Point to)
		throws IllegalMoveFreerailsException {
		AddOrRemoveTrackSectionMove move =
			new AddOrRemoveTrackSectionMove(v1, from, v2, to, MoveType.ADDMOVE);
		MoveStatus moveStatus = move.doMove(trackSystem);
		if (!moveStatus.isOk()) {
			throw new IllegalMoveFreerailsException();
		}

	}

	private void removeTrackSection(
		OneTileMoveVector v1,
		Point from,
		OneTileMoveVector v2,
		Point to)
		throws IllegalMoveFreerailsException {
		AddOrRemoveTrackSectionMove move =
			new AddOrRemoveTrackSectionMove(v1, from, v2, to, MoveType.REMOVEMOVE);
		MoveStatus moveStatus = move.doMove(trackSystem);
		if (!moveStatus.isOk()) {
			throw new IllegalMoveFreerailsException();
		}

	}
}