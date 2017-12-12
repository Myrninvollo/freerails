package jfreerails.world.track;

import jfreerails.misc.NewFlatTrackExplorer;
import jfreerails.world.misc.FreerailsPathIterator;
import jfreerails.world.misc.IntLine;

/**
 * @author Luke Lindsay 13-Oct-2002
 *
 */
public class NewRandomPathFinder implements FreerailsPathIterator {

	NewFlatTrackExplorer trackExplorer;
	
	PositionOnTrack p1 = new PositionOnTrack();
	PositionOnTrack p2 = new PositionOnTrack();

	static final int tileSize=30;

	public NewRandomPathFinder(NewFlatTrackExplorer tx){
		trackExplorer=tx;
	}


	public boolean hasNext() {
		return trackExplorer.hasNextBranch();
	}


	public void nextSegment(IntLine line) {
		p1.setValuesFromInt(trackExplorer.getPosition());
		line.x1=p1.getX()*tileSize+tileSize/2;
		line.y1=p1.getY()*tileSize+tileSize/2;
		trackExplorer.nextBranch();
		trackExplorer.moveForward();
		p2.setValuesFromInt(trackExplorer.getPosition());
		line.x2=p2.getX()*tileSize+tileSize/2;
		line.y2=p2.getY()*tileSize+tileSize/2;
	}

}
