/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import java.lang.String;
import java.awt.Point;
import jfreerails.list.CityList;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class City {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private String name;
/**
 * Represents ...

 */

    private Point position;

   ///////////////////////////////////////
   // associations

    public Vector stationModelPublic = new Vector();
    public CityList cityList;


  ///////////////////////////////////////
  //access methods for attributes

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Point getPosition() {
        return position;
    }
    public void setPosition(Point position) {
        this.position = position;
    }

   ///////////////////////////////////////
   // access methods for associations


    public Vector getStationModelPublic() {
        return stationModelPublic;
    }
    public void addStationModelPublic(StationModelPublic stationModelPublic) {
        if (! this.stationModelPublic.contains(stationModelPublic)) {     
            this.stationModelPublic.addElement(stationModelPublic);  
            stationModelPublic.setCity(this);
        }
    }
    public void removeStationModelPublic(StationModelPublic stationModelPublic) {
        if (this.stationModelPublic.removeElement(stationModelPublic)) {      
            stationModelPublic.setCity(null);
        }
    }

    public CityList getCityList() {
        return cityList;
    }
    public void setCityList(CityList cityList) {
        if (this.cityList != cityList) {
            this.cityList = cityList;
            if (cityList != null)
                cityList.setCity(this);  
        }      
    } 



}





