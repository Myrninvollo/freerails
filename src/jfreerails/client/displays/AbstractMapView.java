/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.client.displays;


import jfreerails.client.view.StationBoxPositioner;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public abstract class AbstractMapView {

   ///////////////////////////////////////
   // associations

    public StationBoxPositioner stationBoxPositioner;


   ///////////////////////////////////////
   // access methods for associations


    public StationBoxPositioner getStationBoxPositioner() {
        return stationBoxPositioner;
    }
    public void setStationBoxPositioner(StationBoxPositioner stationBoxPositioner) {
        if (this.stationBoxPositioner != stationBoxPositioner) {
            this.stationBoxPositioner = stationBoxPositioner;
            if (stationBoxPositioner != null)
                stationBoxPositioner.setAbstractMapView(this);  
        }      
    } 



}





