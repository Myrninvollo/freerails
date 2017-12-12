/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.misc;

import java.util.*;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class GameWorldController {

   ///////////////////////////////////////
   // associations

    public GameWorld gameWorld;


   ///////////////////////////////////////
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





