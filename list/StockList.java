/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.list;

import java.util.*;

import jfreerails.element.Stock;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class StockList {

   ///////////////////////////////////////
   // associations

    public Stock stock;
    public GameElementsList gameElementsList;


   ///////////////////////////////////////
   // access methods for associations


    public Stock getStock() {
        return stock;
    }
    public void setStock(Stock stock) {
        if (this.stock != stock) {
            this.stock = stock;
            if (stock != null)
                stock.setStockList(this);  
        }      
    } 

    public GameElementsList getGameElementsList() {
        return gameElementsList;
    }
    public void setGameElementsList(GameElementsList gameElementsList) {
        if (this.gameElementsList != gameElementsList) {
            this.gameElementsList = gameElementsList;
            if (gameElementsList != null)
                gameElementsList.setStockList(this);  
        }      
    } 



}





