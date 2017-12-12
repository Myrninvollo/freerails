/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.massnoun.GameTime;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class TrainModelPrivate {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private StationModelPublic lastStation;
/**
 * Represents ...

 */

    private GameTime timeDepartedFromLastStation;
/**
 * Represents ...

 */

    private int distanceTraveledSinceLastStation;

   ///////////////////////////////////////
   // associations

    public TrainModelPublic trainModelPublic;
    public Schedule schedule;


  ///////////////////////////////////////
  //access methods for attributes

    public StationModelPublic getLastStation() {
        return lastStation;
    }
    public void setLastStation(StationModelPublic lastStation) {
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

   ///////////////////////////////////////
   // access methods for associations


    public TrainModelPublic getTrainModelPublic() {
        return trainModelPublic;
    }
    public void setTrainModelPublic(TrainModelPublic trainModelPublic) {
        if (this.trainModelPublic != trainModelPublic) {
            this.trainModelPublic = trainModelPublic;
            if (trainModelPublic != null)
                trainModelPublic.setTrainModelPrivate(this);  
        }      
    } 

    public Schedule getSchedule() {
        return schedule;
    }
    public void setSchedule(Schedule schedule) {
        if (this.schedule != schedule) {
            this.schedule = schedule;
            if (schedule != null)
                schedule.setTrainModelPrivate(this);  
        }      
    } 



}





