package jfreerails.client;

import jfreerails.client.tileview.TileViewList;
import jfreerails.client.trackview.TrackPieceViewList;
import jfreerails.world.Types;


public interface ViewLists {
	
	TileViewList getTileViewList();
	TrackPieceViewList getTrackPieceViewList();
	boolean validate(Types t);

}
