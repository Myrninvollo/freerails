package jfreerails.move.receiver;

import jfreerails.map.TrackSystem;
import jfreerails.move.AbstractTrackMove;
import jfreerails.move.Move;
import jfreerails.move.status.MoveStatus;

/**
 * @version 	1.0
 * @author
 */
public class TrackMoveExecutor implements MoveReceiver {

	private final TrackSystem trackSystem;

	private Class trackMoveClass;

	public TrackMoveExecutor(TrackSystem trackSystem) {
		this.trackSystem = trackSystem;
		try {
			trackMoveClass = Class.forName("jfreerails.move.AbstractTrackMove");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	/*
	 * @see MoveReceiver#processMove(Move)
	 */
	public MoveStatus processMove(Move move) {
		if (trackMoveClass.isInstance(move)) {
			return ((AbstractTrackMove)move).doMove(trackSystem);
		} else {
			System.out.println("is not a track move");
			return MoveStatus.MOVE_RECEIVED;
		}

		
	}

}