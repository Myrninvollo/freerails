/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.element.StationModelPrivate;
import jfreerails.type.CargoType;
import jfreerails.massnoun.Money;
import java.lang.String;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class AddOrRemoveBonusMove extends AbstractRRmove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public StationModelPrivate pickupStation;
/**
 * Represents ...

 */

    public StationModelPrivate deliverStation;
/**
 * Represents ...

 */

    public CargoType cargo;
/**
 * Represents ...

 */

    public Money initialReward;
/**
 * Represents ...

 */

    public String reason;
/**
 * Represents ...

 */

    public boolean cancel;

  ///////////////////////////////////////
  //access methods for attributes

    public StationModelPrivate getPickupStation() {
        return pickupStation;
    }
    public void setPickupStation(StationModelPrivate pickupStation) {
        this.pickupStation = pickupStation;
    }
    public StationModelPrivate getDeliverStation() {
        return deliverStation;
    }
    public void setDeliverStation(StationModelPrivate deliverStation) {
        this.deliverStation = deliverStation;
    }
    public CargoType getCargo() {
        return cargo;
    }
    public void setCargo(CargoType cargo) {
        this.cargo = cargo;
    }
    public Money getInitialReward() {
        return initialReward;
    }
    public void setInitialReward(Money initialReward) {
        this.initialReward = initialReward;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public boolean getCancel() {
        return cancel;
    }
    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }


}





