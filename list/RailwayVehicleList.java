/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.list;

import java.util.*;

import jfreerails.element.RailwayVehicleModel;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class RailwayVehicleList {

   ///////////////////////////////////////
   // associations

    public GameElementsList gameElementsList;
    public RailwayVehicleModel railwayVehicleModel;


   ///////////////////////////////////////
   // access methods for associations


    public GameElementsList getGameElementsList() {
        return gameElementsList;
    }
    public void setGameElementsList(GameElementsList gameElementsList) {
        if (this.gameElementsList != gameElementsList) {
            this.gameElementsList = gameElementsList;
            if (gameElementsList != null)
                gameElementsList.setRailwayVehicleList(this);  
        }      
    } 

    public RailwayVehicleModel getRailwayVehicleModel() {
        return railwayVehicleModel;
    }
    public void setRailwayVehicleModel(RailwayVehicleModel railwayVehicleModel) {
        if (this.railwayVehicleModel != railwayVehicleModel) {
            this.railwayVehicleModel = railwayVehicleModel;
            if (railwayVehicleModel != null)
                railwayVehicleModel.setRailwayVehicleList(this);  
        }      
    } 



}





