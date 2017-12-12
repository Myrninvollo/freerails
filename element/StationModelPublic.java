/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.element.container.TrainContainer;
import jfreerails.massnoun.GameTime;
import java.lang.String;
import jfreerails.type.StationType;
import jfreerails.list.StationList;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class StationModelPublic implements TrainContainer {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public GameTime builtDate;
/**
 * Represents ...

 */

    private String stationName;

   ///////////////////////////////////////
   // associations

    public StationModelPrivate stationModelPrivate;
    public StationType stationType;
    public City city;
    public StationList stationList;


  ///////////////////////////////////////
  //access methods for attributes

    public GameTime getBuiltDate() {
        return builtDate;
    }
    public void setBuiltDate(GameTime builtDate) {
        this.builtDate = builtDate;
    }
    public String getStationName() {
        return stationName;
    }
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

   ///////////////////////////////////////
   // access methods for associations


    public StationModelPrivate getStationModelPrivate() {
        return stationModelPrivate;
    }
    public void setStationModelPrivate(StationModelPrivate stationModelPrivate) {
        if (this.stationModelPrivate != stationModelPrivate) {
            this.stationModelPrivate = stationModelPrivate;
            if (stationModelPrivate != null)
                stationModelPrivate.setStationModelPublic(this);  
        }      
    } 

    public StationType getStationType() {
        return stationType;
    }
    public void setStationType(StationType stationType) {
        if (this.stationType != stationType) {
            if (this.stationType != null) 
                this.stationType.removeStationModelPublic(this);     
            this.stationType = stationType;
            if (stationType != null)
                stationType.addStationModelPublic(this);  
        }
    } 

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        if (this.city != city) {
            if (this.city != null) 
                this.city.removeStationModelPublic(this);     
            this.city = city;
            if (city != null)
                city.addStationModelPublic(this);  
        }
    } 

    public StationList getStationList() {
        return stationList;
    }
    public void setStationList(StationList stationList) {
        if (this.stationList != stationList) {
            this.stationList = stationList;
            if (stationList != null)
                stationList.setStationModelPublic(this);  
        }      
    } 



}





