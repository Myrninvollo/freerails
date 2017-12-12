
package jfreerails.world.financial;

import jfreerails.world.GameElementsList;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class StockList {


    public Stock stock;
    public GameElementsList gameElementsList;


    public Stock getStock() {
        return stock;
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





