/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package jfreerails.move;

import jfreerails.world.track.TrackPiece;
import jfreerails.world.track.TrackTileMap;

/**
 * @version 	1.0
 * @author
 */
public class NewTrackMoveImpl implements NewTrackMove {

	/*
	 * @see NewTrackMove#tryDoMove(TrackTileMap)
	 */
	public MoveStatus tryDoMove(TrackTileMap trackTileMap) {
		return null;
	}

	/*
	 * @see NewTrackMove#tryUndoMove(TrackTileMap)
	 */
	public MoveStatus tryUndoMove(TrackTileMap trackTileMap) {
		return null;
	}

	/*
	 * @see NewTrackMove#doMove(TrackTileMap)
	 */
	public MoveStatus doMove(TrackTileMap trackTileMap) {
		return null;
	}

	/*
	 * @see NewTrackMove#undoMove(TrackTileMap)
	 */
	public MoveStatus undoMove(TrackTileMap trackTileMap) {
		return null;
	}
	
	private MoveStatus tryMove(TrackTileMap trackTileMap, TrackPiece before, TrackPiece after){
		return null;	
		
	} 
	private MoveStatus executeMove(TrackTileMap trackTileMap, TrackPiece before, TrackPiece after){
		return null;	
		
	} 

}
