/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class Schedule {

   ///////////////////////////////////////
   // associations

    public TrainModelPrivate trainModelPrivate;
    public TrainRoute trainRoute;
    public TrainRoute trainRoute_1;


   ///////////////////////////////////////
   // access methods for associations


    public TrainModelPrivate getTrainModelPrivate() {
        return trainModelPrivate;
    }
    public void setTrainModelPrivate(TrainModelPrivate trainModelPrivate) {
        if (this.trainModelPrivate != trainModelPrivate) {
            this.trainModelPrivate = trainModelPrivate;
            if (trainModelPrivate != null)
                trainModelPrivate.setSchedule(this);  
        }      
    } 

    public TrainRoute getTrainRoute() {
        return trainRoute;
    }
    public void setTrainRoute(TrainRoute trainRoute) {
        if (this.trainRoute != trainRoute) {
            if (this.trainRoute != null) 
                this.trainRoute.removeSchedule(this);     
            this.trainRoute = trainRoute;
            if (trainRoute != null)
                trainRoute.addSchedule(this);  
        }
    } 

    public TrainRoute getTrainRoute_1() {
        return trainRoute_1;
    }
    public void setTrainRoute_1(TrainRoute trainRoute) {
        if (this.trainRoute_1 != trainRoute) {
            if (this.trainRoute_1 != null) 
                this.trainRoute_1.removeSchedule_1(this);     
            this.trainRoute_1 = trainRoute;
            if (trainRoute != null)
                trainRoute.addSchedule_1(this);  
        }
    } 



}





