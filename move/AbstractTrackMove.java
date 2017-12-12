/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import jfreerails.map.TrackSystem;
import jfreerails.move.status.MoveStatus;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public abstract class AbstractTrackMove extends AbstractRRmove {

	abstract public MoveStatus tryDoMove(TrackSystem trackSystem);

	abstract public MoveStatus tryUndoMove(TrackSystem trackSystem);

	abstract public MoveStatus doMove(TrackSystem trackSystem);

	abstract public MoveStatus undoMove(TrackSystem trackSystem);
}