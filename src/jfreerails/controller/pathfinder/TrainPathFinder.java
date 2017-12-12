package jfreerails.controller.pathfinder;

import java.awt.Point;

import jfreerails.util.IntIterator;
import jfreerails.world.common.FreerailsSerializable;
import jfreerails.world.common.PositionOnTrack;

/** 
 * 
 * 28-Nov-2002 
 * @author Luke Lindsay
 */
public class TrainPathFinder implements IntIterator, FreerailsSerializable {
	
	private static int targetX=10, targetY=10;	
	
	public static void setTarget(int x, int y){
		targetX = x;
		targetY = y;		
	}

	NewFlatTrackExplorer trackExplorer;
	
	SimpleAStarPathFinder pathFinder = new SimpleAStarPathFinder(); 
	
	PositionOnTrack p1 = new PositionOnTrack();
	PositionOnTrack p2 = new PositionOnTrack();

	static final int tileSize=30;

	public TrainPathFinder(NewFlatTrackExplorer tx){
		trackExplorer=tx;		
	}


	public boolean hasNextInt() {
		return trackExplorer.hasNextBranch();
	}

	/*  legacy method from when this class implemented FreerailsPathIterator
	public void nextSegment(IntLine line) {
		p1.setValuesFromInt(p2.toInt());
		line.x1=p1.getX()*tileSize+tileSize/2;
		line.y1=p1.getY()*tileSize+tileSize/2;		
		p2.setValuesFromInt(getNextPosition());
		line.x2=p2.getX()*tileSize+tileSize/2;
		line.y2=p2.getY()*tileSize+tileSize/2;
	}
	*/
	
	public int nextInt(){
		
		PositionOnTrack[] t = NewFlatTrackExplorer.getPossiblePositions(trackExplorer.getWorld(), new Point(targetX, targetY));
		int [] targets = new int [t.length];
		for (int i =0; i<t.length; i++){
			targets[i]=t[i].getOpposite().toInt();			
		}
		PositionOnTrack tempP = new PositionOnTrack(trackExplorer.getPosition());
		int currentPosition = tempP.getOpposite().toInt();
		NewFlatTrackExplorer tempExplorer = new NewFlatTrackExplorer(trackExplorer.getWorld(), tempP);
		int next = pathFinder.findpath(currentPosition, targets, tempExplorer);
		if(next==SimpleAStarPathFinder.PATH_NOT_FOUND){
			trackExplorer.nextBranch();
			trackExplorer.moveForward();
			return trackExplorer.getBranchPosition();
		}else{
			tempP.setValuesFromInt(next);
			tempP = tempP.getOpposite();
			int nextPosition = tempP.toInt();
			trackExplorer.setPosition(nextPosition);
			return nextPosition;
		}	
	}


}
