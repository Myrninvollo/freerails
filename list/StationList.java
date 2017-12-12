/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.list;

import java.util.*;

import jfreerails.element.RRCompany;
import jfreerails.element.StationModelPublic;

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
    public StationModelPublic stationModelPublic;


   ///////////////////////////////////////
   // access methods for associations


    public RRCompany getRRCompany() {
        return rRCompany;
    }
    public void setRRCompany(RRCompany rRCompany) {
        if (this.rRCompany != rRCompany) {
            this.rRCompany = rRCompany;
            if (rRCompany != null)
                rRCompany.setStationList(this);  
        }      
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

    public StationModelPublic getStationModelPublic() {
        return stationModelPublic;
    }
    public void setStationModelPublic(StationModelPublic stationModelPublic) {
        if (this.stationModelPublic != stationModelPublic) {
            this.stationModelPublic = stationModelPublic;
            if (stationModelPublic != null)
                stationModelPublic.setStationList(this);  
        }      
    } 



}





