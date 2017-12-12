/**
 *
 *
 *
 *
 *
 */
package jfreerails.world;


import jfreerails.world.terrain.City;

/** This class is a list of all the cities on the map.
 * 
 * @author lindsal
 */

public class CityList {


   // associations

    public GameWorld gameWorld;

    public City city;



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



    public City getCity() {
        return city;
    }


}





