package jfreerails.controller;


import jfreerails.move.Move;
import jfreerails.move.MoveStatus;
import jfreerails.move.TrackMove;
import jfreerails.world.top.World;

/**
 * @version 	1.0
 * 
 */
final public class TrackMoveExecutor implements MoveReceiver {

	private final World world;

	
	public TrackMoveExecutor(World w) {
		this.world = w;
		
	}

	/*
	 * @see MoveReceiver#processMove(Move)
	 */
	public MoveStatus processMove(Move move) {
		if (move instanceof TrackMove) {
			return ((TrackMove)move).doMove(world);
		} else {
			System.out.println("is not a track move");
			return MoveStatus.MOVE_RECEIVED;
		}

		
	}

}