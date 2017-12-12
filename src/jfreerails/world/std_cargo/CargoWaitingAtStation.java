/** Java class "CargoWaitingAtStation.java" generated from Poseidon for UML. 
 *  Poseidon for UML is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 *  Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 */
package jfreerails.world.std_cargo;

import jfreerails.world.cargo.CargoBundle;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author Luke Lindsay
 */
public class CargoWaitingAtStation {

   ///////////////////////////////////////
   // associations

/**

 */
    private final CargoBundle[] m_cargoWaiting;


  ///////////////////////////////////////
  // operations


/**
 * Does ...
 * 
 * @return A CargoBundle[] with ...
 */
    public CargoBundle[] getCargoWaiting() {        
        		return m_cargoWaiting;
    } // end getCargoWaiting        

/**
 * Does ...
 * 
 * @return A CargoWaitingAtStation with ...
 * @param cargoWaiting ...
 */
    public  CargoWaitingAtStation(CargoBundle[] cargoWaiting) {        
        		m_cargoWaiting = (CargoBundle[]) cargoWaiting.clone();
    } // end CargoWaitingAtStation        

} // end CargoWaitingAtStation




