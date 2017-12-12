package jfreerails.move;

import jfreerails.world.misc.FreerailsPathIterator;
import jfreerails.world.misc.IntLine;
import jfreerails.world.train.Snake;
import jfreerails.world.train.SnakeImpl;
import junit.framework.TestCase;

/**
 * @author Luke Lindsay 22-Oct-2002
 *
 */
public class ChangeSnakeMoveTest extends TestCase {
	
	static class Fixture {
		public final int[] ax, ay, bx, by, cx, cy;

		public Fixture(
			int[] ax,
			int[] ay,
			int[] bx,
			int[] by,
			int[] cx,
			int[] cy) {
			this.ax = ax;
			this.ay = ay;
			this.bx = bx;
			this.by = by;
			this.cx = cx;
			this.cy = cy;

		}

	}
	
	public static final Fixture ADD_TO_HEAD1=new Fixture(
		new int[] { 20, 30, 40 }, new int[] { 21, 31, 41 },
		//plus
		new int[] { 10, 20 }, new int[] { 11, 21 },
		//equals
		new int[] { 10, 30, 40 }, new int[] { 11, 31, 41 }
		);
	
	public static final Fixture ADD_TO_HEAD2=new Fixture(	
		new int[] { 20, 30, 40 }, new int[] { 21, 31, 41 },			
		//plus
		new int[] { 10, 20, 20 },new int[] { 11, 21, 21 },
		//equals
		new int[] { 10, 20, 30, 40 }, new int[] { 11, 21, 31, 41 });
		
		
	public static final Fixture ADD_TO_TAIL1=new Fixture(
			new int[] { 70, 50, 20 },
			new int[] { 71, 51, 21 },
		//plus
		new int[] { 20, 30, 10 }, new int[] { 21, 31, 11 },
		//equals
		new int[] { 70, 50, 30, 10 }, new int[] { 71, 51, 31, 11 });
		
	public static final Fixture ADD_TO_TAIL2=new Fixture(
			new int[] { 10, 50, 90 },
			new int[] { 11, 51, 91 },
		//plus
		new int[] { 20, 30, 10 }, new int[] { 21, 31, 11 },
		//equals
		new int[] { 20, 30, 50, 90 }, new int[] { 21, 31, 51, 91 });
		
	public static final Fixture SUBTRACT_FROM_HEAD=new Fixture(
			new int[] { 10, 50, 90, 100 },
			new int[] { 11, 51, 91, 101 },
		//plus
		new int[] { 10, 50, 60 }, 
		new int[] { 11, 51, 61 },
		//equals
		new int[] { 60, 90, 100 }, 
		new int[] { 61, 91, 101 });
		
		
	public static final Fixture SUBTRACT_FROM_TAIL=new Fixture(
			new int[] { 10, 50, 90, 100 },
			new int[] { 11, 51, 91, 101 },
		//plus
		new int[] { 80, 90, 100 }, new int[] { 81, 91, 101 },
		//equals
		new int[] { 10, 50, 80 }, new int[] { 11, 51, 81 });

	/**
	 * Constructor for ChangeSnakeMoveTest.
	 * @param arg0
	 */
	public ChangeSnakeMoveTest(String arg0) {
		super(arg0);
	}

	public void testChangeSnakeMove() {
		//Test that we cannot pass a null snake to the constructor.

		//Test that we cannot pass an invalid snake to the constructor.
	}

	public void testDoMove() {
/*
		//Adding to the head.

		assertTrue(aPlusBEqualsC(ADD_TO_HEAD1));

		//Adding to the head again.

		assertTrue(aPlusBEqualsC(ADD_TO_HEAD2));

		//Adding to the tail.		
		assertTrue(aPlusBEqualsC(ADD_TO_TAIL1));

		//Adding to the tail again.

		assertTrue(aPlusBEqualsC(ADD_TO_TAIL2));

		//Subtracting from the head

		assertTrue(aMinusBEqualsC(SUBTRACT_FROM_HEAD));

		//Subtracting from the tail		
		assertTrue(aMinusBEqualsC(SUBTRACT_FROM_TAIL));
*/
	}

	public void testUndoMove() {
	}

	public void testTryDoMove() {
		//Test that we can add the snake {(10,10), (20,20)}
		//to the snake {(20,20), (30,30), (50,20)}.

	}

	public void testTryUndoMove() {
	}

	public boolean aPlusBEqualsC(
		Fixture f) {

		return aAndBEqualsC(f, true);
	}

	public boolean aAndBEqualsC(
		Fixture f,
		boolean addition) {
		Snake a = new SnakeImpl(f.ax, f.ay);
		Snake b = new SnakeImpl(f.bx, f.by);

		ChangeSnakeMove move = new ChangeSnakeMove(b);
		move.doMove(a);

		return equals(a, f.cx, f.cy);
	}

	public boolean aMinusBEqualsC(Fixture f) {

		return aAndBEqualsC(f, false);
	}

	public boolean isAplusBValid(int[] ax, int[] ay, int[] bx, int[] by) {

		return false;
	}

	public boolean isAMinusBValid(int[] ax, int[] ay, int[] bx, int[] by) {

		return false;
	}

	public boolean equals(Snake s, int[] x, int[] y) {

		Snake snake2 = new SnakeImpl(x, y);

		FreerailsPathIterator path = s.pathIterator();
		FreerailsPathIterator path2 = snake2.pathIterator();

		IntLine line1 = new IntLine();
		IntLine line2 = new IntLine();
		while (path.hasNext() && path2.hasNext()) {
			path.nextSegment(line1);
			path2.nextSegment(line2);

			if (line1.x1 != line2.x1
				|| line1.y1 != line2.y1
				|| line1.x2 != line2.x2
				|| line1.y2 != line2.y2) {
				return false;
			}
		}

		if (path.hasNext() || path2.hasNext()) {
			return false;
		} else {
			return true;
		}
	}

}
