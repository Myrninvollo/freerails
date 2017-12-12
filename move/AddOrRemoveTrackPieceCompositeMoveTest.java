package jfreerails.move;

import java.awt.Point;

import jfreerails.element.TrackNode;
import jfreerails.element.TrackSection;
import jfreerails.list.TrackRuleList;
import jfreerails.map.FreerailsMap;
import jfreerails.map.TrackSystem;
import jfreerails.misc.OneTileMoveVector;
import jfreerails.move.IllegalMoveFreerailsException;
import jfreerails.move.status.MoveStatus;
import jfreerails.type.TrackRule;
import jfreerails.unittest.fixture.MapFixtureFactory;
import junit.framework.TestCase;
import jfreerails.controller.*;

public class AddOrRemoveTrackPieceCompositeMoveTest extends TestCase {
	private FreerailsMap map;

	private TrackRuleList trackRuleList;
	private TrackSystem trackSystem;

	public AddOrRemoveTrackPieceCompositeMoveTest(String name) {
		super(name);
	}
	protected void setUp() {
		MapFixtureFactory mapFactory = new MapFixtureFactory();
		trackRuleList = mapFactory.trackRuleList;
		map = mapFactory.generateMap();
		trackSystem = map.getTrackMap();

	}

	public void testRemoveTrack() {
		OneTileMoveVector east = OneTileMoveVector.EAST;
		OneTileMoveVector west = OneTileMoveVector.WEST;

		TrackRule trackRule = trackRuleList.getTrackRule(0);
		try {
			buildTrack(new Point(0, 5), east, trackRule);

			buildTrack(new Point(0, 6), east, trackRule);
			buildTrack(new Point(1, 6), east, trackRule);

			buildTrack(new Point(0, 7), east, trackRule);
			buildTrack(new Point(1, 7), east, trackRule);
			buildTrack(new Point(2, 7), east, trackRule);
		} catch (IllegalMoveFreerailsException e) {
			assertTrue(false);
		}
		//Remove only track piece built.
		try {
			removeTrack(new Point(0, 5), east);
			assertTrue(null == trackSystem.getTrackNode(new Point(0, 5)));
			assertTrue(null == trackSystem.getTrackNode(new Point(1, 5)));

		} catch (IllegalMoveFreerailsException e) {
			e.printStackTrace();
			assertTrue(false);
		}

		//Try to remove non existent track piece
		try {
			assertTrue(null == trackSystem.getTrackNode(new Point(0, 5)));
			removeTrack(new Point(0, 5), east);
			assertTrue(false);
		} catch (IllegalMoveFreerailsException e) {

		}
	}

	public void testBuildTrack() {
		Point pointA = new Point(0, 0);
		Point pointB = new Point(1, 1);
		Point pointC = new Point(1, 0);
		OneTileMoveVector southeast = OneTileMoveVector.SOUTH_EAST;
		OneTileMoveVector east = OneTileMoveVector.EAST;
		OneTileMoveVector northeast = OneTileMoveVector.NORTH_EAST;
		OneTileMoveVector south = OneTileMoveVector.SOUTH;
		OneTileMoveVector west = OneTileMoveVector.WEST;

		TrackRule trackRule = trackRuleList.getTrackRule(0);

		//First track piece built
		assertBuildTrackSuceeds(pointA, southeast, trackRule);

		//Track connected from one existing track piece
		assertBuildTrackSuceeds(pointB, northeast, trackRule);

		//Track connected to one existing track piece
		assertBuildTrackSuceeds(pointC, east, trackRule);

		//Track connecting two existing track pieces.
		assertBuildTrackSuceeds(pointA, east, trackRule);

		//Track off map.. should fail.
		assertBuildTrackFails(pointA, northeast, trackRule);

		//Track already there.
		assertBuildTrackFails(pointA, southeast, trackRule);

		//Illegal config. connecting from one existing track piece
		assertBuildTrackFails(pointA, south, trackRule);

		//Illegal config. connecting to one existing track piece
		assertBuildTrackFails(new Point(0, 1), northeast, trackRule);

		//Illegal config. connecting between two existing track pieces
		assertBuildTrackFails(pointC, south, trackRule);

		//Not allowed on this terrain type, from existing track.
		assertBuildTrackFails(
			new Point(2, 0),
			northeast,
			trackRuleList.getTrackRule(1));

		//Not allowed on this terrain type, to existing track.
		assertBuildTrackFails(new Point(3, 0), west, trackRuleList.getTrackRule(1));

		//Not allowed on this terrain type, first track piece built.
		assertBuildTrackFails(new Point(3, 1), east, trackRuleList.getTrackRule(1));
	}

	private void assertBuildTrackFails(
		Point pointA,
		OneTileMoveVector v,
		TrackRule trackRule) {
		AddOrRemoveTrackPieceCompositeMove move =
			AddOrRemoveTrackPieceCompositeMove.generateBuildTrack(pointA, v, trackRule, trackSystem);
		MoveStatus moveStatus = move.doMove(trackSystem);
		assertTrue(!moveStatus.isOk());
	}

	private void assertBuildTrackSuceeds(
		Point point,
		OneTileMoveVector v,
		TrackRule trackRule) {

		TrackNode nodeA, nodeB;
		TrackSection trackSectionA, trackSectionB;

		AddOrRemoveTrackPieceCompositeMove move =
			AddOrRemoveTrackPieceCompositeMove.generateBuildTrack(point, v, trackRule, trackSystem);
		MoveStatus moveStatus = move.tryDoMove(trackSystem);
		assertTrue(moveStatus.isOk());
		
		moveStatus = move.doMove(trackSystem);
		assertTrue(moveStatus.isOk());

		nodeA = trackSystem.getTrackNode(point);
		nodeB = trackSystem.getTrackNode(v.createRelocatedPoint(point));
		assertTrue(null != nodeA);
		assertTrue(null != nodeB);
		trackSectionA = nodeA.getTrackSection(v);
		trackSectionB = nodeB.getTrackSection(v.getOpposite());
		assertTrue(null != trackSectionA);
		assertTrue(null != trackSectionB);
		assertTrue(trackSectionA == trackSectionB);

	}

	private void buildTrack(Point point, OneTileMoveVector v, TrackRule trackRule)
		throws IllegalMoveFreerailsException {
		AddOrRemoveTrackPieceCompositeMove move =
			AddOrRemoveTrackPieceCompositeMove.generateBuildTrack(point, v, trackRule, trackSystem);
		MoveStatus moveStatus = move.doMove(trackSystem);
		boolean moveIsOk = moveStatus.isOk();
		if (!moveIsOk) {
			throw new IllegalMoveFreerailsException();
		}

	}
	private void removeTrack(Point point, OneTileMoveVector v)
		throws IllegalMoveFreerailsException {
			
		AddOrRemoveTrackPieceCompositeMove move =
			AddOrRemoveTrackPieceCompositeMove.generateRemoveTrack(point, v, trackSystem);
		MoveStatus moveStatus = move.doMove(trackSystem);
		
		
		boolean moveIsOk = moveStatus.isOk();
		if (!moveIsOk) {
			throw new IllegalMoveFreerailsException();
		}
		assertTrue(null==trackSystem.getTrackNode(point));

	}

}