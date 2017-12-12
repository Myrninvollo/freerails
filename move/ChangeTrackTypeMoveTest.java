package jfreerails.move;

import java.awt.Point;

import jfreerails.element.TrackNode;
import jfreerails.list.TrackRuleList;
import jfreerails.map.FreerailsMap;
import jfreerails.map.TrackSystem;
import jfreerails.misc.OneTileMoveVector;
import jfreerails.move.IllegalMoveFreerailsException;
import jfreerails.move.MoveType;
import jfreerails.move.status.MoveStatus;
import jfreerails.type.TrackRule;
import jfreerails.unittest.fixture.MapFixtureFactory;
import junit.framework.TestCase;
import jfreerails.controller.*;

public class ChangeTrackTypeMoveTest extends TestCase {

	private FreerailsMap map;
	private AddOrRemoveTrackNodeMove trackNodeBuilder;
	private ChangeTrackTypeMove trackUpgrader;
	
	private TrackRuleList trackRuleList;
	private TrackSystem trackSystem;

	public ChangeTrackTypeMoveTest(String name) {
		super(name);
	}

	protected void setUp() {

		MapFixtureFactory mapFactory = new MapFixtureFactory();
		trackRuleList = mapFactory.trackRuleList;
		map = mapFactory.generateMap();

		trackSystem = map.getTrackMap();

			addTrackNode(new Point(0, 0), trackRuleList.getTrackRule(0));
			addTrackNode(new Point(1, 0), trackRuleList.getTrackRule(0));
			addTrackNode(new Point(2, 0), trackRuleList.getTrackRule(2));
			addTrackNode(new Point(3, 0), trackRuleList.getTrackRule(2));
			//leave 4,0 empty 
			addTrackNode(new Point(5, 0), trackRuleList.getTrackRule(0));
			addTrackNode(new Point(6, 0), trackRuleList.getTrackRule(0));
			
			AddOrRemoveTrackSectionMove move =
			new AddOrRemoveTrackSectionMove(OneTileMoveVector.EAST,
				new Point(5, 0),
				OneTileMoveVector.WEST,
				new Point(6, 0), MoveType.ADDMOVE);
			move.doMove(trackSystem);
		

	}
	public void testUpgradeTrack() {
		//Test a->c. 
		try {
			TrackNode node = trackSystem.getTrackNode(new Point(0, 0));

			assertTrue(node.getTrackRule() == trackRuleList.getTrackRule(0));
			upgradeTrack(
				new Point(0, 0),
				trackRuleList.getTrackRule(0),
				trackRuleList.getTrackRule(2));

			assertTrue(node.getTrackRule() == trackRuleList.getTrackRule(2));
		} catch (IllegalMoveFreerailsException e) {
			e.printStackTrace();

			assertTrue(false);
		}
		//Test a->b when b is not allowed on this terrain.
		try {
			upgradeTrack(
				new Point(1, 0),
				trackRuleList.getTrackRule(0),
				trackRuleList.getTrackRule(1));
			assertTrue(false);
		} catch (IllegalMoveFreerailsException e) {

		}
		//Test c->c already here.
		try {
			upgradeTrack(
				new Point(2, 0),
				trackRuleList.getTrackRule(2),
				trackRuleList.getTrackRule(2));
			assertTrue(false);
		} catch (IllegalMoveFreerailsException e) {

		}
		//Test a->c when initial type is not a.
		try {
			upgradeTrack(
				new Point(3, 0),
				trackRuleList.getTrackRule(0),
				trackRuleList.getTrackRule(2));
			assertTrue(false);
		} catch (IllegalMoveFreerailsException e) {

		}
		//Test a->b when there is no track node here.
		try {
			upgradeTrack(
				new Point(4, 0),
				trackRuleList.getTrackRule(0),
				trackRuleList.getTrackRule(2));
			assertTrue(false);
		} catch (IllegalMoveFreerailsException e) {

		}
		//Test a->b when type b does not allow the configuration at a.
		try {
			upgradeTrack(
				new Point(5, 0),
				trackRuleList.getTrackRule(0),
				trackRuleList.getTrackRule(2));
			assertTrue(false);
		} catch (IllegalMoveFreerailsException e) {

		}
	}
	private void addTrackNode(Point point, TrackRule rule) {
		AddOrRemoveTrackNodeMove addTrackNodeMove = new AddOrRemoveTrackNodeMove(point, rule, MoveType.ADDMOVE);
		addTrackNodeMove.doMove(trackSystem);
	}
	private void upgradeTrack(Point pos, TrackRule oldRule, TrackRule newRule) throws IllegalMoveFreerailsException{
		 ChangeTrackTypeMove move=new ChangeTrackTypeMove(pos, oldRule, newRule);
		MoveStatus moveStatus=move.doMove(trackSystem);
		if(!moveStatus.isOk()){
			throw new IllegalMoveFreerailsException();
		}
		
	}
		
}