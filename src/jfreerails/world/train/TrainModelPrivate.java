package jfreerails.world.train;

import jfreerails.world.misc.GameTime;
import jfreerails.world.station.StationModel;
/**
 *
 *
 *
 * @author lindsal
 */

public class TrainModelPrivate {



    private StationModel lastStation;

    private GameTime timeDepartedFromLastStation;

    private int distanceTraveledSinceLastStation;

    public Schedule schedule;

private TrainModel trainModelPublic;

    public StationModel getLastStation() {
        return lastStation;
    }
    public void setLastStation(StationModel lastStation) {
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





