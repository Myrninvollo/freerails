/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.type.WaitUntilFullOrders;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class TrainRoute {

   ///////////////////////////////////////
   // associations

    public StationModelPrivate stationModelPrivate;
    public WaitUntilFullOrders waitUntilFullOrders;
    public Vector schedule = new Vector();
    public Vector schedule_1 = new Vector();


   ///////////////////////////////////////
   // access methods for associations


    public StationModelPrivate getStationModelPrivate() {
        return stationModelPrivate;
    }
    public void setStationModelPrivate(StationModelPrivate stationModelPrivate) {
        if (this.stationModelPrivate != stationModelPrivate) {
            if (this.stationModelPrivate != null) 
                this.stationModelPrivate.removeTrainRoute(this);     
            this.stationModelPrivate = stationModelPrivate;
            if (stationModelPrivate != null)
                stationModelPrivate.addTrainRoute(this);  
        }
    } 

    public WaitUntilFullOrders getWaitUntilFullOrders() {
        return waitUntilFullOrders;
    }
    public void setWaitUntilFullOrders(WaitUntilFullOrders waitUntilFullOrders) {
        if (this.waitUntilFullOrders != waitUntilFullOrders) {
            this.waitUntilFullOrders = waitUntilFullOrders;
            if (waitUntilFullOrders != null)
                waitUntilFullOrders.setTrainRoute(this);  
        }      
    } 

    public Vector getSchedule() {
        return schedule;
    }
    public void addSchedule(Schedule schedule) {
        if (! this.schedule.contains(schedule)) {     
            this.schedule.addElement(schedule);  
            schedule.setTrainRoute(this);
        }
    }
    public void removeSchedule(Schedule schedule) {
        if (this.schedule.removeElement(schedule)) {      
            schedule.setTrainRoute(null);
        }
    }

    public Vector getSchedule_1() {
        return schedule_1;
    }
    public void addSchedule_1(Schedule schedule) {
        if (! this.schedule_1.contains(schedule)) {     
            this.schedule_1.addElement(schedule);  
            schedule.setTrainRoute_1(this);
        }
    }
    public void removeSchedule_1(Schedule schedule) {
        if (this.schedule_1.removeElement(schedule)) {      
            schedule.setTrainRoute_1(null);
        }
    }



}





