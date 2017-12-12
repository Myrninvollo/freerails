/**
 *
 *
 *
 *
 *
 */
package jfreerails.world.financial;



/**
 *
 *
 *
 * @author lindsal
 */

public class StockBroker {


   // associations

    public FinancialMarket financialMarket;
    public RRCompany rRCompany;



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





