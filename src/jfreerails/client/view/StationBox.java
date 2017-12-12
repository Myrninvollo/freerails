/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.client.view;



/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class StationBox {

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
                stationBoxPositioner.setStationBox(this);  
        }      
    } 



}





