package jfreerails.world.flat;

import java.awt.Point;

import jfreerails.world.track.PositionOnTrack;
import jfreerails.world.track.TrackExplorer;
import jfreerails.world.track.TrackSection;

public class FlatTrackExplorer implements TrackExplorer {
	
	LegalRouteAcrossTrackPiece legalRoutes;
	int index;
	OneTileMoveVector lastMove;

	public boolean hasNextBranch() {
		if(legalRoutes.a.length>index){
			return true;
		}else{
			return false;
		}
	}

	public TrackSection getBranchTrackSection() {
		return null;
	}

	public PositionOnTrack getCurrentPosition() {
		return null;
	}

	public void nextBranch() {
	}

	public void moveForward() {
	}

	public void turnAround() {
	}

	public Point getBranchTrackSectionLocation() {
		return null;
	}

}
