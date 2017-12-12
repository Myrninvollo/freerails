/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.list;


import jfreerails.misc.GameWorld;
import jfreerails.world.terrain.City;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class CityList {

   ///////////////////////////////////////
   // associations

    public GameWorld gameWorld;
    public GameElementsList gameElementsList;
    public City city;


   ///////////////////////////////////////
   // access methods for associations


    public GameWorld getGameWorld() {
        return gameWorld;
    }
    public void setGameWorld(GameWorld gameWorld) {
        if (this.gameWorld != gameWorld) {
            this.gameWorld = gameWorld;
            if (gameWorld != null)
                gameWorld.setCityList(this);  
        }      
    } 

    public GameElementsList getGameElementsList() {
        return gameElementsList;
    }
    public void setGameElementsList(GameElementsList gameElementsList) {
        if (this.gameElementsList != gameElementsList) {
            this.gameElementsList = gameElementsList;
            if (gameElementsList != null)
                gameElementsList.setCityList(this);  
        }      
    } 

    public City getCity() {
        return city;
    }


}





