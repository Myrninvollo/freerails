/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.type.CargoStorage;
import jfreerails.type.RevenueBooster;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class StationCargoSupplyAndDemand {

   ///////////////////////////////////////
   // associations

    public CargoStorage cargoStorage;
    public RevenueBooster revenueBooster;
    public StationModelPrivate stationModelPrivate;


   ///////////////////////////////////////
   // access methods for associations


    public CargoStorage getCargoStorage() {
        return cargoStorage;
    }
    public void setCargoStorage(CargoStorage cargoStorage) {
            this.cargoStorage = cargoStorage;
    } 

    public RevenueBooster getRevenueBooster() {
        return revenueBooster;
    }
    public void setRevenueBooster(RevenueBooster revenueBooster) {
            this.revenueBooster = revenueBooster;
    } 

    public StationModelPrivate getStationModelPrivate() {
        return stationModelPrivate;
    }
    public void setStationModelPrivate(StationModelPrivate stationModelPrivate) {
        if (this.stationModelPrivate != stationModelPrivate) {
            this.stationModelPrivate = stationModelPrivate;
            if (stationModelPrivate != null)
                stationModelPrivate.setStationCargoSupplyAndDemand(this);  
        }      
    } 



}





