/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.type;

import java.util.*;

import jfreerails.element.TrainRoute;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class WaitUntilFullOrders {

   ///////////////////////////////////////
   // associations

    public TrainRoute trainRoute;


   ///////////////////////////////////////
   // access methods for associations


    public TrainRoute getTrainRoute() {
        return trainRoute;
    }
    public void setTrainRoute(TrainRoute trainRoute) {
        if (this.trainRoute != trainRoute) {
            this.trainRoute = trainRoute;
            if (trainRoute != null)
                trainRoute.setWaitUntilFullOrders(this);  
        }      
    } 



}





