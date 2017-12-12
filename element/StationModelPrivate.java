/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.element.container.CargoContainer;
import java.util.Hashtable;
import jfreerails.type.MaintenanceShop;
import jfreerails.type.SwitchingYard;
import jfreerails.type.EngineShop;
import jfreerails.type.CargoType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public abstract class StationModelPrivate implements CargoContainer {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private Hashtable cargoWaiting;
/**
 * Represents ...

 */

    private boolean rateWar;
/**
 * Represents ...

 */

    private boolean firstYear;

   ///////////////////////////////////////
   // associations

    public Vector trainRoute = new Vector();
    public MaintenanceShop maintenanceShop;
    public SwitchingYard switchingYard;
    public EngineShop engineShop;
    public StationCargoSupplyAndDemand stationCargoSupplyAndDemand;
    public StationModelPublic stationModelPublic;
    public CargoType cargoType;
    public CargoType cargoType_1;


  ///////////////////////////////////////
  //access methods for attributes

    public Hashtable getCargoWaiting() {
        return cargoWaiting;
    }
    public void setCargoWaiting(Hashtable cargoWaiting) {
        this.cargoWaiting = cargoWaiting;
    }
    public boolean getRateWar() {
        return rateWar;
    }
    public void setRateWar(boolean rateWar) {
        this.rateWar = rateWar;
    }
    public boolean getFirstYear() {
        return firstYear;
    }
    public void setFirstYear(boolean firstYear) {
        this.firstYear = firstYear;
    }

   ///////////////////////////////////////
   // access methods for associations


    public Vector getTrainRoute() {
        return trainRoute;
    }
    public void addTrainRoute(TrainRoute trainRoute) {
        if (! this.trainRoute.contains(trainRoute)) {     
            this.trainRoute.addElement(trainRoute);  
            trainRoute.setStationModelPrivate(this);
        }
    }
    public void removeTrainRoute(TrainRoute trainRoute) {
        if (this.trainRoute.removeElement(trainRoute)) {      
            trainRoute.setStationModelPrivate(null);
        }
    }

    public MaintenanceShop getMaintenanceShop() {
        return maintenanceShop;
    }
    public void setMaintenanceShop(MaintenanceShop maintenanceShop) {
            this.maintenanceShop = maintenanceShop;
    } 

    public SwitchingYard getSwitchingYard() {
        return switchingYard;
    }
    public void setSwitchingYard(SwitchingYard switchingYard) {
            this.switchingYard = switchingYard;
    } 

    public EngineShop getEngineShop() {
        return engineShop;
    }
    public void setEngineShop(EngineShop engineShop) {
            this.engineShop = engineShop;
    } 

    public StationCargoSupplyAndDemand getStationCargoSupplyAndDemand() {
        return stationCargoSupplyAndDemand;
    }
    public void setStationCargoSupplyAndDemand(StationCargoSupplyAndDemand stationCargoSupplyAndDemand) {
        if (this.stationCargoSupplyAndDemand != stationCargoSupplyAndDemand) {
            this.stationCargoSupplyAndDemand = stationCargoSupplyAndDemand;
            if (stationCargoSupplyAndDemand != null)
                stationCargoSupplyAndDemand.setStationModelPrivate(this);  
        }      
    } 

    public StationModelPublic getStationModelPublic() {
        return stationModelPublic;
    }
    public void setStationModelPublic(StationModelPublic stationModelPublic) {
        if (this.stationModelPublic != stationModelPublic) {
            this.stationModelPublic = stationModelPublic;
            if (stationModelPublic != null)
                stationModelPublic.setStationModelPrivate(this);  
        }      
    } 

    public CargoType getCargoType() {
        return cargoType;
    }
    public void setCargoType(CargoType cargoType) {
        if (this.cargoType != cargoType) {
            this.cargoType = cargoType;
            if (cargoType != null)
                cargoType.setStationModelPrivate(this);  
        }      
    } 

    public CargoType getCargoType_1() {
        return cargoType_1;
    }
    public void setCargoType_1(CargoType cargoType) {
        if (this.cargoType_1 != cargoType) {
            this.cargoType_1 = cargoType;
            if (cargoType != null)
                cargoType.setStationModelPrivate_1(this);  
        }      
    } 


  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @param cargoType ...
 * @return A int with ...
 */

    public int getAmountWaiting(CargoType cargoType) {
        return 0;
    }
/**
 * Does ...
 * 
 * @param cargo ...
 * @return A int with ...
 */

    public int getDemand(CargoType cargo) {
        return 0;
    }
/**
 * Does ...
 * 
 * @param train ...
 */

    public void trainPassed(TrainModelPrivate train) {
    }
/**
 * Does ...
 * 
 * @param train ...
 */

    public void trainStopped(TrainModelPrivate train) {
    }



}





