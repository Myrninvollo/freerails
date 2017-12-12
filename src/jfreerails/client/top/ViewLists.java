package jfreerails.client.top;


import jfreerails.client.renderer.TileRendererList;
import jfreerails.client.renderer.TrackPieceRendererList;
import jfreerails.world.top.World;


public interface ViewLists {
	
	TileRendererList getTileViewList();
	TrackPieceRendererList getTrackPieceViewList();
	boolean validate(World world);

}
