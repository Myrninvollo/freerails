package jfreerails.world.train;

import jfreerails.world.common.GameTime;

/**
 *
 *
 *
 * @author lindsal
 */

public class TrainModelPrivate {
    
    
    
    private int lastStation;
    
    private GameTime timeDepartedFromLastStation;
    
    private int distanceTraveledSinceLastStation;
    
    public Schedule schedule;
    
    private TrainModel trainModelPublic;
    
    public int getLastStation() {
        return lastStation;
    }
    public void setLastStation(int lastStation) {
        this.lastStation = lastStation;
    }
    public GameTime getTimeDepartedFromLastStation() {
        return timeDepartedFromLastStation;
    }
    public void setTimeDepartedFromLastStation(GameTime timeDepartedFromLastStation) {
        this.timeDepartedFromLastStation = timeDepartedFromLastStation;
    }
    public int getDistanceTraveledSinceLastStation() {
        return distanceTraveledSinceLastStation;
    }
    public void setDistanceTraveledSinceLastStation(int distanceTraveledSinceLastStation) {
        this.distanceTraveledSinceLastStation = distanceTraveledSinceLastStation;
    }
    
    
    public TrainModel getTrainModelPublic() {
        return trainModelPublic;
    }
    
    public Schedule getSchedule() {
        return schedule;
    }
    public void setSchedule(Schedule schedule) {
        if (this.schedule != schedule) {
            this.schedule = schedule;
            if (schedule != null){
                //schedule.setTrainModelPrivate(this);
            }
        }
    }
    
    
    
}





