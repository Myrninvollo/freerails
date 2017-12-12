/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.world.financial;


import jfreerails.world.type.EconomicClimate;
import jfreerails.world.StockBroker;

import java.util.Vector;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class FinancialMarket {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private int interestRate;
   ///////////////////////////////////////
   // associations

    public EconomicClimate economicClimate;
    public StockBroker stockBroker;
    public Vector listedCompany = new Vector();


  ///////////////////////////////////////
  //access methods for attributes

    public int getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

   ///////////////////////////////////////
   // access methods for associations



    public EconomicClimate getEconomicClimate() {
        return economicClimate;
    }


    public StockBroker getStockBroker() {
        return stockBroker;
    }
    public void setStockBroker(StockBroker stockBroker) {
        if (this.stockBroker != stockBroker) {
            this.stockBroker = stockBroker;
            if (stockBroker != null)
                stockBroker.setFinancialMarket(this);  
        }      
    } 

    public Vector getListedCompany() {
        return listedCompany;
    }
    public void addListedCompany(ListedCompany listedCompany) {
        if (! this.listedCompany.contains(listedCompany)) {     
            this.listedCompany.addElement(listedCompany);  
        }
    }
    public void removeListedCompany(ListedCompany listedCompany) {    
        this.listedCompany.removeElement(listedCompany);        
    }


  ///////////////////////////////////////
  // operations




}





