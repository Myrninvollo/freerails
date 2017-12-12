package jfreerails.controller.pathfinder;

import java.awt.Point;

import jfreerails.util.FreerailsIntIterator;
import jfreerails.world.common.FreerailsSerializable;
import jfreerails.world.common.PositionOnTrack;
import jfreerails.world.station.StationModel;
import jfreerails.world.top.KEY;
import jfreerails.world.top.World;
import jfreerails.world.train.Schedule;
import jfreerails.world.train.TrainModel;

/**
 *
 * 28-Nov-2002
 * @author Luke Lindsay
 */
public class TrainPathFinder implements FreerailsIntIterator, FreerailsSerializable {
    
    //private static int[] targetX = new int[4], targetY = new int[4];
    
    //public static void setTarget(int i, int x, int y) {
    //	targetX[i] = x;
    //	targetY[i] = y;
    //	System.out.println("The target " + i + " for the train pathfinder is now: " + x + ", " + y);
    //}
    
    private final int train;
    
    private final World world;
    
    //private int targetNumber = 0;
    
    //private int targetX, targetY;
    
    FlatTrackExplorer trackExplorer;
    
    SimpleAStarPathFinder pathFinder = new SimpleAStarPathFinder();
    
    PositionOnTrack p1 = new PositionOnTrack();
    PositionOnTrack p2 = new PositionOnTrack();
    
    static final int TILE_SIZE = 30;
    
    public TrainPathFinder(FlatTrackExplorer tx, World w, int trainNumber) {
        this.train = trainNumber;
        this.world = w;
        trackExplorer = tx;
        updateTarget();
    }
    
    public boolean hasNextInt() {
        return trackExplorer.hasNextBranch();
    }
    
    /** updates the targetX and targetY values based on the train's schedule */
    private void updateTarget() {
        TrainModel train = (TrainModel) world.get(KEY.TRAINS, this.train);
        Schedule schedule = train.getSchedule();
        StationModel station = null;
        scheduledStop();
        schedule.gotoNextStaton();
        int stationNumber = schedule.getStationToGoto();
        station = (StationModel) world.get(KEY.STATIONS, stationNumber);
        if(null == station){
            System.out.println("null == station, train "+train+" doesn't know where to go next!");
        }else{
            //this.targetX = station.x;
            //this.targetY = station.y;
        }
    }
    
    private Point getTarget(){
        TrainModel train = (TrainModel) world.get(KEY.TRAINS, this.train);
        Schedule schedule = train.getSchedule();
        int stationNumber = schedule.getStationToGoto();
        StationModel station = (StationModel) world.get(KEY.STATIONS, stationNumber);
        return new Point(station.x, station.y);
    }
    
    private void scheduledStop(){
        TrainModel train = (TrainModel) world.get(KEY.TRAINS, this.train);
        Schedule schedule = train.getSchedule();
        StationModel station = null;
        int stationNumber = schedule.getStationToGoto();
        station = (StationModel) world.get(KEY.STATIONS, stationNumber);
        train.addWagons(schedule.getWagonsToAdd());
    }
    
    
    public int nextInt() {
        
        PositionOnTrack tempP = new PositionOnTrack(trackExplorer.getPosition());
        Point targetPoint = getTarget();
        if (tempP.getX() == targetPoint.x && tempP.getY() == targetPoint.y) {
            updateTarget();
        }
        int currentPosition = tempP.getOpposite().toInt();
        
        PositionOnTrack[] t =
        FlatTrackExplorer.getPossiblePositions(
        trackExplorer.getWorld(),
        new Point(targetPoint.x, targetPoint.y));
        int[] targets = new int[t.length];
        for (int i = 0; i < t.length; i++) {
            int target = t[i].getOpposite().toInt();
            if (target == currentPosition) {
                System.out.println("Reached target!");
            }
            targets[i] = target;
        }
        
        FlatTrackExplorer tempExplorer = new FlatTrackExplorer(trackExplorer.getWorld(), tempP);
        int next = pathFinder.findpath(currentPosition, targets, tempExplorer);
        if (next == SimpleAStarPathFinder.PATH_NOT_FOUND) {
            trackExplorer.nextBranch();
            trackExplorer.moveForward();
            return trackExplorer.getBranchPosition();
        } else {
            tempP.setValuesFromInt(next);
            tempP = tempP.getOpposite();
            int nextPosition = tempP.toInt();
            trackExplorer.setPosition(nextPosition);
            return nextPosition;
        }
    }
    
}
