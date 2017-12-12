/*
 * TrackTileMap.java
 *
 * Created on 23 January 2002, 21:51
 */

package jfreerails.world.std_track;
import java.awt.Dimension;
import java.awt.Point;

import jfreerails.world.TrackRuleList;
import jfreerails.world.tilemap.TileMap;
/**
 *
 * @author  lindsal
 * @version 
 */
public interface TrackTileMap extends TileMap {

	TrackPiece getTrackPiece(Point point);

	int getTrackTypeNumber(Point point);

	int getTrackGraphicNumber(Point point);

	boolean trackIsAllowed(Point location, int type);

	void setTrackPiece(Point point, TrackPiece trackPiece);

	TrackRuleList getTrackRuleList();

}