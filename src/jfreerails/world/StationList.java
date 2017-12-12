/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.world;


import jfreerails.world.financial.RRCompany;
import jfreerails.world.station.StationModel;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class StationList {

   ///////////////////////////////////////
   // associations

    public RRCompany rRCompany;
    public GameElementsList gameElementsList;
    public StationModel stationModelPublic;


   ///////////////////////////////////////
   // access methods for associations


    public RRCompany getRRCompany() {
        return rRCompany;
    }


    public GameElementsList getGameElementsList() {
        return gameElementsList;
    }
    public void setGameElementsList(GameElementsList gameElementsList) {
        if (this.gameElementsList != gameElementsList) {
            this.gameElementsList = gameElementsList;
            if (gameElementsList != null)
                gameElementsList.setStationList(this);  
        }      
    } 

    public StationModel getStationModelPublic() {
        return stationModelPublic;
    }



}





