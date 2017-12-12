package jfreerails.world.track;


import java.awt.Point;

import jfreerails.world.misc.OneTileMoveVector;


public interface TrackExplorer {

 
    boolean hasNextBranch();

    OneTileMoveVector getBranchTrackSection();

    PositionOnTrack getCurrentPosition();

    void nextBranch();

    void moveForward();
   
    Point getBranchTrackSectionLocation();
    
}

