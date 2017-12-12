package jfreerails.controller;


import jfreerails.move.Move;
import jfreerails.move.MoveStatus;
import jfreerails.move.NewTrackMove;
import jfreerails.world.top.World;

/**
 * @version 	1.0
 * 
 */
final public class TrackMoveExecutor implements MoveReceiver {

	private final World world;

	private Class trackMoveClass;

	public TrackMoveExecutor(World w) {
		this.world = w;
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
			return ((NewTrackMove)move).doMove(world);
		} else {
			System.out.println("is not a track move");
			return MoveStatus.MOVE_RECEIVED;
		}

		
	}

}