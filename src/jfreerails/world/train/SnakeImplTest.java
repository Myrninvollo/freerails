package jfreerails.world.train;

import junit.framework.TestCase;

public class SnakeImplTest extends TestCase {

	public SnakeImplTest(String arg0) {
		super(arg0);
	}

	public void testSnakeImpl() {

		/*The default constructor should return a snake with 
		 * one section.  The section shsould be a line from 0,0 to 0,0.
		 */

		Snake s = new SnakeImpl();
		FreerailsPathIterator it = s.pathIterator();
		assertNotNull(it);

		assertTrue(it.hasNext());

		IntLine line = new IntLine();
		line.x1 = 100;
		line.x1 = 200;
		line.y1 = 300;
		line.y2 = 400;

		it.nextSegment(line);
		assertLineEquals(0, 0, 0, 0, line);

		assertTrue(!it.hasNext());

	}

	public void testAddToHead() {
		Snake s = new SnakeImpl();
		s.addToHead(10, 20);
		FreerailsPathIterator it = s.pathIterator();
		IntLine line = new IntLine();
		it.nextSegment(line);
		assertLineEquals(10, 20, 0, 0, line);
		assertTrue(it.hasNext());
		it.nextSegment(line);
		assertLineEquals(0, 0, 0, 0, line);
		assertTrue(!it.hasNext());

	}

	public void testRemoveFromHead() {

		/*Check that an exception is thrown when 
		*the snake only has two points
		*/

		Snake s = new SnakeImpl();
		try {
			s.removeFromHead();
			assertTrue(false);
		} catch (IllegalStateException ile) {
		} catch (Exception e) {
			assertTrue(false);
		}

		
		 
		/* Check that a valid change goes through.
		 */
		s = new SnakeImpl();
		s.addToTail(10, 20);
		try {
			s.removeFromHead();
		} catch (Exception e) {
			assertTrue(false);
		}
		FreerailsPathIterator it = s.pathIterator();
		IntLine line = new IntLine();
		it.nextSegment(line);
		assertLineEquals(0, 0, 10, 20, line);
		assertTrue(!it.hasNext());

	}

	public void testMoveHead() {
		Snake s = new SnakeImpl();
		s.moveHead(30, 40);
		FreerailsPathIterator it = s.pathIterator();
		IntLine line = new IntLine();
		it.nextSegment(line);
		assertLineEquals(30, 40, 0, 0, line);
		assertTrue(!it.hasNext());

	}

	public void testAddToTail() {
		Snake s = new SnakeImpl();
		s.addToTail(10, 20);
		FreerailsPathIterator it = s.pathIterator();
		IntLine line = new IntLine();
		it.nextSegment(line);
		assertLineEquals(0, 0, 0, 0, line);
		assertTrue(it.hasNext());
		it.nextSegment(line);
		assertLineEquals(0, 0, 10, 20, line);
		assertTrue(!it.hasNext());
	}

	public void testRemoveFromTail() {
		
		/*Check that an exception is thrown when 
		*the snake only has two points
		*/

		Snake s = new SnakeImpl();
		try {
			s.removeFromTail();
			assertTrue(false);
		} catch (IllegalStateException ile) {
		} catch (Exception e) {
			assertTrue(false);
		}

		
		 
		/* Check that a valid change goes through.
		 */
		s = new SnakeImpl();
		s.addToHead(10, 20);
		try {
			s.removeFromTail();
		} catch (Exception e) {
			assertTrue(false);
		}
		FreerailsPathIterator it = s.pathIterator();
		IntLine line = new IntLine();
		it.nextSegment(line);
		assertLineEquals(10, 20, 0,0, line);
		assertTrue(!it.hasNext());

	}

	public void testMoveTail() {
		Snake s = new SnakeImpl();
		s.moveTail(30, 40);
		FreerailsPathIterator it = s.pathIterator();
		IntLine line = new IntLine();
		it.nextSegment(line);
		assertLineEquals(0, 0, 30, 40, line);
		assertTrue(!it.hasNext());
	}

	public void testPathIterator() {

		Snake s = new SnakeImpl();
		FreerailsPathIterator it = s.pathIterator();
		assertNotNull(it);
	}

	private void assertLineEquals(int x1, int y1, int x2, int y2, IntLine line) {
		assertEquals(x1, line.x1);
		assertEquals(x2, line.x2);
		assertEquals(y1, line.y1);
		assertEquals(y2, line.y2);
	}

}
