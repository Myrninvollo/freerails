
package jfreerails.move;

import jfreerails.world.track.TrackTileMap;

/**
 *
 *
 *
 * @author lindsal
 */

public interface NewTrackMove extends Move{

	  MoveStatus tryDoMove(TrackTileMap trackTileMap);

	  MoveStatus tryUndoMove(TrackTileMap trackTileMap);

	  MoveStatus doMove(TrackTileMap trackTileMap);

	  MoveStatus undoMove(TrackTileMap trackTileMap);

}