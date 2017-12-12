/**
 *
 *
 *
 *
 *
 */
package jfreerails.world.financial;


import java.util.Vector;

/**
 *
 *
 *
 * @author lindsal
 */

public class ThePublic implements StockOwner {


   // associations

    public Vector rRCompany = new Vector();



   // access methods for associations


    public Vector getRRCompany() {
        return rRCompany;
    }


    public int getNumberOfSharesOwned(ListedCompany inThisCompany) {
        return 0;
    }
/**
 * Does ...
 *
 * @param stock ...
 */




        public void addStock(Stock stock){}


}





