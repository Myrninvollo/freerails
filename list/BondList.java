/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.list;

import java.util.*;

import jfreerails.element.Bond;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class BondList {

   ///////////////////////////////////////
   // associations

    public Bond bond;
    public GameElementsList gameElementsList;


   ///////////////////////////////////////
   // access methods for associations


    public Bond getBond() {
        return bond;
    }
    public void setBond(Bond bond) {
        if (this.bond != bond) {
            this.bond = bond;
            if (bond != null)
                bond.setBondList(this);  
        }      
    } 

    public GameElementsList getGameElementsList() {
        return gameElementsList;
    }
    public void setGameElementsList(GameElementsList gameElementsList) {
        if (this.gameElementsList != gameElementsList) {
            this.gameElementsList = gameElementsList;
            if (gameElementsList != null)
                gameElementsList.setBondList(this);  
        }      
    } 



}





