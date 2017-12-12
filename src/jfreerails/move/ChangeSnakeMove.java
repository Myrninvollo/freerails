package jfreerails.move;

import java.awt.Point;

import jfreerails.world.misc.FreerailsPathIterator;
import jfreerails.world.misc.IntLine;
import jfreerails.world.train.Snake;

/**
 * @author Luke Lindsay 22-Oct-2002
 *
 */
public class ChangeSnakeMove {

	private final Snake snakePiece;
	//The piece of snake we will be adding or removing.

	private final int snakeNumber = 0;

	public ChangeSnakeMove(Snake piece) {
		this.snakePiece = piece;
	}

	public MoveStatus doMove(Snake s) {
		FreerailsPathIterator path;
		Point snakeHead = new Point();
		Point snakeTail = new Point();
		Point pieceHead = new Point();
		Point pieceTail = new Point();
		s.getHead(snakeHead);
		s.getTail(snakeTail);
		snakePiece.getHead(pieceHead);
		snakePiece.getTail(pieceTail);

		boolean iterateForwards =
			pieceHead.equals(snakeHead) || pieceHead.equals(snakeTail);
		boolean isAddition =
			!pieceHead.equals(snakeHead) && !pieceTail.equals(snakeTail);

		if (iterateForwards) {
			path = snakePiece.pathIterator();
		} else {

		}
		//Case 1: add piece to snake's head.
		if (snakeHead.equals(pieceTail)) {
			path = snakePiece.reversePathIterator();
			s.removeFromHead();
			IntLine line = new IntLine();
			while (path.hasNext()) {
				path.nextSegment(line);
				s.addToHead(line.x2, line.y2);
			}
		}

		//Case 2: add piece to snake's tail.
		else if (snakeTail.equals(pieceHead)) {
			path = snakePiece.pathIterator();
			s.removeFromTail();
			IntLine line = new IntLine();
			while (path.hasNext()) {
				path.nextSegment(line);
				s.addToTail(line.x2, line.y2);
			}
		}

		//Case 3: remove piece from snake's head.
		else if (snakeHead.equals(pieceHead)) {

			path = snakePiece.pathIterator();
			
			IntLine line = new IntLine();
		
			while(path.hasNext()){														
				path.nextSegment(line);
				if(path.hasNext()){
					s.removeFromHead();
				}else{
					s.moveHead(line.x2, line.y2);
				}		
			} 			
		}

		//Case 4: remove piece from snake's tail.
		else if (snakeTail.equals(pieceTail)) {
			path = snakePiece.reversePathIterator();
			s.removeFromTail();
			IntLine line = new IntLine();
			while (path.hasNext()) {
				path.nextSegment(line);
				s.removeFromHead();
			}
		}

		return null;
	}

	public MoveStatus undoMove(Snake s) {
		return null;
	}

	public MoveStatus tryDoMove(Snake s) {
		return null;
	}

	public MoveStatus tryUndoMove(Snake s) {
		return null;
	}

	private MoveStatus move(Snake piece, boolean isAdd) {
		return null;

	}

}
