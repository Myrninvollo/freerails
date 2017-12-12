/**
 *
 *
 *
 *
 *
 */
package jfreerails.client.view;



/**
 *
 *
 *
 * @author lindsal
 */

public class StationBoxPositioner {


   // associations

    public StationBox stationBox;
    public AbstractMapView abstractMapView;



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





