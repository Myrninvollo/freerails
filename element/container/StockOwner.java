package jfreerails.element.container;

import java.util.*;

import jfreerails.element.ListedCompany;
import jfreerails.element.Stock;

/**
 * Defining operations expected of ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public interface StockOwner {

   ///////////////////////////////////////
  // associations



  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @return A int with ...
 * @param inThisCompany ...
 */

    public int getNumberOfSharesOwned(ListedCompany inThisCompany);
/**
 * Does ...
 * 
 * @param stock ...
 */

    public void addStock(Stock stock);

}

