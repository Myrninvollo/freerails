
package jfreerails.misc;

import java.util.Vector;

import jfreerails.list.CityList;
import jfreerails.world.financial.RRCompany;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class GameWorld {

   
    public GameWorldController gameWorldController;
    public Vector rRCompany = new Vector();
    public CityList cityList;


    public GameWorldController getGameWorldController() {
        return gameWorldController;
    }
    public void setGameWorldController(GameWorldController gameWorldController) {
        if (this.gameWorldController != gameWorldController) {
            this.gameWorldController = gameWorldController;
            if (gameWorldController != null)
                gameWorldController.setGameWorld(this);  
        }      
    } 

    public Vector getRRCompany() {
        return rRCompany;
    }
    public void addRRCompany(RRCompany rRCompany) {
        if (! this.rRCompany.contains(rRCompany)) {     
            this.rRCompany.addElement(rRCompany);  
           
        }
    }
    public void removeRRCompany(RRCompany rRCompany) {
        if (this.rRCompany.removeElement(rRCompany)) {      
            
        }
    }

    public CityList getCityList() {
        return cityList;
    }
    public void setCityList(CityList cityList) {
        if (this.cityList != cityList) {
            this.cityList = cityList;
            if (cityList != null)
                cityList.setGameWorld(this);  
        }      
    } 



}





