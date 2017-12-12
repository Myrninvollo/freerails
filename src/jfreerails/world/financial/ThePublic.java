/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.world.financial;


import java.util.Vector;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ThePublic implements StockOwner {

   ///////////////////////////////////////
   // associations

    public Vector rRCompany = new Vector();


   ///////////////////////////////////////
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





