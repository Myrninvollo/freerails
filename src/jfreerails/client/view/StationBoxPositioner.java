/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.client.view;


import jfreerails.client.displays.AbstractMapView;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class StationBoxPositioner {

   ///////////////////////////////////////
   // associations

    public StationBox stationBox;
    public AbstractMapView abstractMapView;


   ///////////////////////////////////////
   // access methods for associations


    public StationBox getStationBox() {
        return stationBox;
    }
    public void setStationBox(StationBox stationBox) {
        if (this.stationBox != stationBox) {
            this.stationBox = stationBox;
            if (stationBox != null)
                stationBox.setStationBoxPositioner(this);  
        }      
    } 

    public AbstractMapView getAbstractMapView() {
        return abstractMapView;
    }
    public void setAbstractMapView(AbstractMapView abstractMapView) {
        if (this.abstractMapView != abstractMapView) {
            this.abstractMapView = abstractMapView;
            if (abstractMapView != null)
                abstractMapView.setStationBoxPositioner(this);  
        }      
    } 



}





