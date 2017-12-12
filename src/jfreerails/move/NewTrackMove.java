
package jfreerails.move;

import jfreerails.move.status.MoveStatus;
import jfreerails.world.std_track.TrackTileMap;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public interface NewTrackMove extends Move{

	  MoveStatus tryDoMove(TrackTileMap trackTileMap);

	  MoveStatus tryUndoMove(TrackTileMap trackTileMap);

	  MoveStatus doMove(TrackTileMap trackTileMap);

	  MoveStatus undoMove(TrackTileMap trackTileMap);
	 	
}