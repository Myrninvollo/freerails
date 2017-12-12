package jfreerails.world.flat;

import java.awt.Point;
import java.util.NoSuchElementException;

import jfreerails.world.std_track.NullTrackPiece;
import jfreerails.world.std_track.TrackPiece;
import jfreerails.world.std_track.TrackTileMap;
import jfreerails.world.track.PositionOnTrack;
import jfreerails.world.track.TrackExplorer;
import jfreerails.world.track.TrackSection;

public class FlatTrackExplorer implements TrackExplorer {
	
	static final int NO_MORE_BRANCHES=-99;

	OneTileMoveVector currentBranch = null;
	OneTileMoveVector lastMove = null;
	TrackTileMap map;
	Point position;
	int firstVectorToTry=0;
	

	public boolean hasNextBranch() {
		if(NO_MORE_BRANCHES==nextVectorNumber()){
			return false;
		}else{
			return true;
		}
	}

	private int nextVectorNumber() {
		TrackPiece tp = map.getTrackPiece(position);
		TrackConfiguration conf = tp.getTrackConfiguration();
		OneTileMoveVector[] vectors = OneTileMoveVector.getList();
		
		int vectorNo;
		if (null != currentBranch) {
			vectorNo = (currentBranch.getNumber()+1)%8;
			
			//This check is needed because vectorNo is incremented before 
			//the same test is applied in the while loop below.
			if (firstVectorToTry == vectorNo) {
				return NO_MORE_BRANCHES;
			}
		}else{			
			vectorNo=firstVectorToTry;
		}
		 
		
		
		 while (!conf.contains(vectors[vectorNo].getTemplate())){
			vectorNo++;
			vectorNo=vectorNo%8;
			if (firstVectorToTry == vectorNo) {
				return NO_MORE_BRANCHES;
			}
		}	
		
		return vectorNo;
	}

	public OneTileMoveVector getBranchTrackSection() {
		return currentBranch;
	}

	public PositionOnTrack getCurrentPosition() {
		return new PositionOnTrack(position.x, position.y, 0,0);
	}

	public void nextBranch() {
		int nextBranch=nextVectorNumber();
		if(NO_MORE_BRANCHES==nextBranch){
			throw new NoSuchElementException();
		}else{
			this.currentBranch=OneTileMoveVector.getList()[nextBranch];
		
		}		
	}

	public void moveForward() {
		
		position.x+=currentBranch.getDx();
		position.y+=currentBranch.getDy();
		firstVectorToTry=(currentBranch.getNumber()+5)%8;
		currentBranch=null;
		
	}

	public Point getBranchTrackSectionLocation() {
		return null;
	}

	public FlatTrackExplorer(TrackTileMap m, Point l) {

		this.map = m;
		this.position = new Point(l);
		if (map.getTrackPiece(l) == NullTrackPiece.getInstance()) {
			throw new IllegalArgumentException(
				"Cannot create a track explorer at location "
					+ l.x
					+ ", "
					+ l.y
					+ " since there is no track there.");
		}
	}

}
