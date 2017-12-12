/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.element.container.StockOwner;
import jfreerails.element.Stock;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class TransferStockMove extends AbstractFinancialMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public StockOwner buyer;
/**
 * Represents ...

 */

    public StockOwner seller;
/**
 * Represents ...

 */

    public Stock stock;

  ///////////////////////////////////////
  //access methods for attributes

    public StockOwner getBuyer() {
        return buyer;
    }
    public void setBuyer(StockOwner buyer) {
        this.buyer = buyer;
    }
    public StockOwner getSeller() {
        return seller;
    }
    public void setSeller(StockOwner seller) {
        this.seller = seller;
    }
    public Stock getStock() {
        return stock;
    }
    public void setStock(Stock stock) {
        this.stock = stock;
    }


}





