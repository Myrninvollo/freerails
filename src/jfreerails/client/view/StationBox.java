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

public class StationBox {


   // associations

    public StationBoxPositioner stationBoxPositioner;



   // access methods for associations


    public StationBoxPositioner getStationBoxPositioner() {
        return stationBoxPositioner;
    }
    public void setStationBoxPositioner(StationBoxPositioner stationBoxPositioner) {
        if (this.stationBoxPositioner != stationBoxPositioner) {
            this.stationBoxPositioner = stationBoxPositioner;
            if (stationBoxPositioner != null)
                stationBoxPositioner.setStationBox(this);
        }
    }



}





