/**
 *
 *
 *
 *
 *
 */
package jfreerails.world;



/**
 *
 *
 *
 * @author lindsal
 */

public class GameWorldController {


   // associations

    public GameWorld gameWorld;



   // access methods for associations


    public GameWorld getGameWorld() {
        return gameWorld;
    }
    public void setGameWorld(GameWorld gameWorld) {
        if (this.gameWorld != gameWorld) {
            this.gameWorld = gameWorld;
            if (gameWorld != null)
                gameWorld.setGameWorldController(this);
        }
    }



}





