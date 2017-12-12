package jfreerails.controller;

import jfreerails.move.Move;
import jfreerails.move.MoveStatus;
import jfreerails.move.NewTrackMove;
import jfreerails.world.track.TrackTileMap;

/**
 * @version 	1.0
 * @author
 */
final public class TrackMoveExecutor implements MoveReceiver {

	private final TrackTileMap TrackTileMap;

	private Class trackMoveClass;

	public TrackMoveExecutor(TrackTileMap TrackTileMap) {
		this.TrackTileMap = TrackTileMap;
		try {
			trackMoveClass = Class.forName("jfreerails.move.NewTrackMove");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	/*
	 * @see MoveReceiver#processMove(Move)
	 */
	public MoveStatus processMove(Move move) {
		if (trackMoveClass.isInstance(move)) {
			return ((NewTrackMove)move).doMove(TrackTileMap);
		} else {
			System.out.println("is not a track move");
			return MoveStatus.MOVE_RECEIVED;
		}

		
	}

}