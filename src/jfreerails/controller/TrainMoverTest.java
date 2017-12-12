package jfreerails.controller;

import java.util.ArrayList;

import experimental.TrainFixture;

import jfreerails.move.ChangeTrainPositionMove;
import jfreerails.world.misc.IntLine;
import jfreerails.world.train.PathWalker;
import jfreerails.world.train.TrainList;
import jfreerails.world.train.TrainPosition;
import junit.framework.TestCase;

/**
 * @author Luke Lindsay 30-Oct-2002
 *
 */
public class TrainMoverTest extends TestCase {

	ArrayList points;

	TrainMover trainMover;

	TrainList trainList;

	/**
	 * Constructor for TrainMoverTest.
	 * @param arg0
	 */
	public TrainMoverTest(String arg0) {
		super(arg0);
	}

	protected void setUp() {
		TrainFixture trainFixture = new TrainFixture();

		points = trainFixture.getPoints();

		trainMover = trainFixture.getTrainMover();

		trainList = trainFixture.getTrainList();

	}

	public void testTrainMover() {

		setUp();

		TrainPosition pos = trainList.getTrain(0).getPosition();

		assertEquals(pos.getX(0), 0);
		assertEquals(pos.getY(0), 0);

		PathWalker pw = trainMover.getWalker();

		pw.stepForward(10);

		IntLine line = new IntLine();

		pw.nextSegment(line);

		assertEquals(line.x1, 0);
		assertEquals(line.y1, 0);

	}

	public void testUpdate() {
		
		setUp();
		
		TrainPosition pos = trainList.getTrain(0).getPosition();
		
		System.out.println(pos.toString());
		
		ChangeTrainPositionMove m= trainMover.update(30);
		
		m.doMove(trainList);

	}

}
