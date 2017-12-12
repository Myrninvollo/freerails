/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.controller;

import java.util.*;

import jfreerails.element.FinancialMarket;
import jfreerails.element.RRCompany;
import jfreerails.element.ListedCompany;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class StockBroker {

   ///////////////////////////////////////
   // associations

    public FinancialMarket financialMarket;
    public RRCompany rRCompany;


   ///////////////////////////////////////
   // access methods for associations


    public FinancialMarket getFinancialMarket() {
        return financialMarket;
    }
    public void setFinancialMarket(FinancialMarket financialMarket) {
        if (this.financialMarket != financialMarket) {
            this.financialMarket = financialMarket;
            if (financialMarket != null)
                financialMarket.setStockBroker(this);  
        }      
    } 

    public RRCompany getRRCompany() {
        return rRCompany;
    }
    public void setRRCompany(RRCompany rRCompany) {
        if (this.rRCompany != rRCompany) {
            this.rRCompany = rRCompany;
            if (rRCompany != null)
                rRCompany.setStockBroker(this);  
        }      
    } 


  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @param company ...
 */

    public void buyStock(ListedCompany company) {
    }
/**
 * Does ...
 * 
 * @param company ...
 */

    public void sellStock(ListedCompany company) {
    }
/**
 * Does ...
 * 

 */

    public void sellBond() {
    }
/**
 * Does ...
 * 

 */

    public void repayBond() {
    }



}





