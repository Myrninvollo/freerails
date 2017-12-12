
package experimental;

import java.awt.Point;
import java.util.Iterator;

import jfreerails.world.track.PositionOnTrack;
import jfreerails.world.track.TrackExplorer;
import jfreerails.world.track.TrackMap;
import jfreerails.world.track.TrackSection;

/**
 * @version 	1.0
 * @author
 */
public class ExptTrackMap implements TrackMap {
	
	
	TrackSection[] track = new TrackSection[4];


	public ExptTrackMap() {
		
		track[0] = new TrackSectionImpl1(50, 50, Bearings.EAST,150, 50, Bearings.WEST);
		track[1] = new TrackSectionImpl1(150, 50, Bearings.EAST, 150, 150, Bearings.WEST);
		track[2] = new TrackSectionImpl1(150, 150, Bearings.EAST, 50, 150, Bearings.WEST);
		track[3] = new TrackSectionImpl1(50, 150, Bearings.EAST, 50, 50, Bearings.WEST);
	}

	/*
	 * @see TrackMap#getExplorer(PositionOnTrack)
	 */
	public TrackExplorer getExplorer(PositionOnTrack p) {
		return null;
	}

	/*
	 * @see TrackMap#getTrackNodeIterator()
	 */
	public Iterator getTrackNodeIterator() {
		return null;
	}

}
