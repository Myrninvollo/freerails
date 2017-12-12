/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.element.container.StockOwner;
import jfreerails.list.StockList;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class Stock {

   ///////////////////////////////////////
   // associations

    public ListedCompany listedCompany;
    public StockOwner stockOwner;
    public StockList stockList;


   ///////////////////////////////////////
   // access methods for associations


    public ListedCompany getListedCompany() {
        return listedCompany;
    }
    public void setListedCompany(ListedCompany listedCompany) {
            this.listedCompany = listedCompany;
    } 

    public StockOwner getStockOwner() {
        return stockOwner;
    }
    public void setStockOwner(StockOwner stockOwner) {
            this.stockOwner = stockOwner;
    } 

    public StockList getStockList() {
        return stockList;
    }
    public void setStockList(StockList stockList) {
        if (this.stockList != stockList) {
            this.stockList = stockList;
            if (stockList != null)
                stockList.setStock(this);  
        }      
    } 



}





