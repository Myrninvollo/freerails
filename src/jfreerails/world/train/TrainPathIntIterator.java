package jfreerails.world.train;

import jfreerails.misc.NewFlatTrackExplorer;
import jfreerails.util.IntIterator;

/**
 * 30-Nov-2002
 * @author Luke Lindsay
 *
 */
public class TrainPathIntIterator implements IntIterator {
	
	public NewFlatTrackExplorer trackExplorer;
	
	public TrainPathIntIterator(NewFlatTrackExplorer t){
		trackExplorer = t;		
	}
	
	public boolean hasNextInt(){
		return trackExplorer.hasNextBranch();
	}
	
	public int nextInt(){
		trackExplorer.nextBranch();
		trackExplorer.moveForward();
		return trackExplorer.getPosition();
	}		
}
