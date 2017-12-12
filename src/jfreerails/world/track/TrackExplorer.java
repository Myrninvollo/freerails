package jfreerails.world.track;

import java.awt.Point;

import jfreerails.world.common.OneTileMoveVector;
import jfreerails.world.common.PositionOnTrack;


public interface TrackExplorer {

 
    boolean hasNextBranch();

    OneTileMoveVector getBranchTrackSection();

    PositionOnTrack getCurrentPosition();

    void nextBranch();

    void moveForward();
   
    Point getBranchTrackSectionLocation();
    
    void setPosition(PositionOnTrack p);
    
}

