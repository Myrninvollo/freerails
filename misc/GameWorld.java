/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.misc;

import java.util.*;

import jfreerails.element.RRCompany;
import jfreerails.list.CityList;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class GameWorld {

   ///////////////////////////////////////
   // associations

    public GameWorldController gameWorldController;
    public Vector rRCompany = new Vector();
    public CityList cityList;


   ///////////////////////////////////////
   // access methods for associations


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
            rRCompany.setGameWorld(this);
        }
    }
    public void removeRRCompany(RRCompany rRCompany) {
        if (this.rRCompany.removeElement(rRCompany)) {      
            rRCompany.setGameWorld(null);
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





