package jfreerails.world.track;

import java.util.Iterator;

public interface TrackMap {

	TrackExplorer getExplorer(PositionOnTrack p);
	
	Iterator getTrackNodeIterator();

}