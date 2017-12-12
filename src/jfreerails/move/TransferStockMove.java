
package jfreerails.move;

import jfreerails.world.financial.Stock;
import jfreerails.world.financial.StockOwner;

/**
 *
 *
 *
 * @author lindsal
 */

final public class TransferStockMove  {


    private final StockOwner buyer;

    private final StockOwner seller;

    private final Stock stock;


    public StockOwner getBuyer() {
        return buyer;
    }

    public StockOwner getSeller() {
        return seller;
    }

    public Stock getStock() {
        return stock;
    }
    public TransferStockMove(StockOwner from, StockOwner to,Stock st){
    	buyer=to;
    	seller=from;
    	stock=st;

    }
}





