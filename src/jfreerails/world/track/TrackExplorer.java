package jfreerails.world.track;


import java.awt.Point;


public interface TrackExplorer {

 
    boolean hasNextBranch();

    TrackSection getBranchTrackSection();

    PositionOnTrack getCurrentPosition();

    void nextBranch();

    void moveForward();

    void turnAround();

    Point getBranchTrackSectionLocation();

}

