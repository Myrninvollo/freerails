package jfreerails.move;

import java.awt.Point;

import jfreerails.element.TrackNode;
import jfreerails.list.TrackRuleList;
import jfreerails.map.FreerailsMap;
import jfreerails.map.TrackSystem;
import jfreerails.move.IllegalMoveFreerailsException;
import jfreerails.move.MoveType;
import jfreerails.move.status.MoveStatus;
import jfreerails.type.TrackRule;
import jfreerails.unittest.fixture.MapFixtureFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import jfreerails.controller.*;

public class AddOrRemoveTrackNodeMoveTest extends TestCase {

	private FreerailsMap map;

	private TrackRuleList trackRuleList;
	private TrackSystem trackSystem;

	public AddOrRemoveTrackNodeMoveTest(String name) {
		super(name);
	}

	protected void setUp() {

		MapFixtureFactory mapFactory = new MapFixtureFactory();
		trackRuleList = mapFactory.trackRuleList;
		map = mapFactory.generateMap();
		trackSystem = map.getTrackMap();
		//trackList = map.getTrackMap().getTrackList();
		//trackNodeBuilder = new TrackNodeBuilder(map.getTrackMap());

	}

	public void testRemoveTrackNode() {
		Point pos = new Point(3, 3);

		//Should fail, no track here
		try {

			assertTrue(null == trackSystem.getTrackNode(pos));
			removeTrackNode(pos);

			assertTrue(false);
		} catch (jfreerails.move.IllegalMoveFreerailsException me) {
			assertTrue(true);
		}
		//This should go through..

		try {

			buildTrackNode(pos, trackRuleList.getTrackRule(0));

			TrackNode trackNode = trackSystem.getTrackNode(pos);
			assertTrue(null != trackNode);
			assertTrue(trackSystem.contains(trackNode));
			assertTrue(null != trackSystem.getTrackNode(pos));
			removeTrackNode(pos);
			assertTrue(!trackSystem.contains(trackNode));
			assertTrue(null == trackSystem.getTrackNode(pos));

			assertTrue(true);
		} catch (jfreerails.move.IllegalMoveFreerailsException me) {
			assertTrue(false);
		}

	}

	public void testAddTrackNode() {

		Point pos = new Point(1, 1);

		//This should go through..

		assertTrue(null == trackSystem.getTrackNode(pos));
		boolean canBuildHere =
			trackSystem
				.trackIsAllowed(pos, trackRuleList.getTrackRule(0))
				.buildingTrackIsOk
				.trackIsAllowed();
		assertTrue(canBuildHere);

		AddOrRemoveTrackNodeMove move =
			new AddOrRemoveTrackNodeMove(pos, trackRuleList.getTrackRule(0), MoveType.ADDMOVE);

		MoveStatus moveStatus = move.doMove(trackSystem);
		assertTrue(null!=moveStatus);
		assertTrue(moveStatus.isOk());
		TrackNode trackNode = trackSystem.getTrackNode(pos);
		assertTrue(null != trackNode);
		assertTrue(trackSystem.contains(trackNode));

		boolean moveIsOk = moveStatus.isOk();

		pos = new Point(2, 2);

		//Should fail, not allowed on this terrain..
		try {
			assertTrue(null == trackSystem.getTrackNode(pos));
			buildTrackNode(pos, trackRuleList.getTrackRule(1));

			assertTrue(false);
		} catch (jfreerails.move.IllegalMoveFreerailsException me) {
			assertTrue(true);
		}

		//Should fail, already track here.
		pos = new Point(1, 1);
		try {
			assertTrue(null != trackSystem.getTrackNode(pos));
			buildTrackNode(pos, trackRuleList.getTrackRule(0));

			assertTrue(false);
		} catch (jfreerails.move.IllegalMoveFreerailsException me) {
			assertTrue(true);
		}
	}

	public static Test suite() {
		TestSuite suite = new TestSuite(AddOrRemoveTrackNodeMoveTest.class);
		return suite;
	}

	public static void main(String args[]) {
		Test test = suite();
		TestRunner.run(test);
	}
	private void buildTrackNode(Point point, TrackRule rule)
		throws IllegalMoveFreerailsException {
		AddOrRemoveTrackNodeMove buildTrackNodeMove =
			new AddOrRemoveTrackNodeMove(point, rule, MoveType.ADDMOVE);

		MoveStatus moveStatus = buildTrackNodeMove.doMove(trackSystem);

		boolean moveIsOk = moveStatus.isOk();
		if (!moveIsOk) {
			throw new IllegalMoveFreerailsException();
		}

	}
	private void removeTrackNode(Point point)
		throws IllegalMoveFreerailsException {

		TrackNode node = trackSystem.getTrackNode(point);
		TrackRule rule;
		if (null != node) {
			rule = node.getTrackRule();
		} else {
			rule = trackSystem.getTrackRuleList().getTrackRule(0);
		}

		AddOrRemoveTrackNodeMove buildTrackNodeMove =
			new AddOrRemoveTrackNodeMove(point, rule, MoveType.REMOVEMOVE);

		MoveStatus moveStatus = buildTrackNodeMove.doMove(trackSystem);

		boolean moveIsOk = moveStatus.isOk();
		if (!moveIsOk) {
			throw new IllegalMoveFreerailsException();
		}

	}

}