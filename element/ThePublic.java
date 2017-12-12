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
    public void addRRCompany(RRCompany rRCompany) {
        if (! this.rRCompany.contains(rRCompany)) {     
            this.rRCompany.addElement(rRCompany);  
            rRCompany.setThePublic(this);
        }
    }
    public void removeRRCompany(RRCompany rRCompany) {
        if (this.rRCompany.removeElement(rRCompany)) {      
            rRCompany.setThePublic(null);
        }
    }


  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @param inThisCompany ...
 * @return A int with ...
 */

    public int getNumberOfSharesOwned(ListedCompany inThisCompany) {
        return 0;
    }
/**
 * Does ...
 * 
 * @param stock ...
 */

    public void addStock(Stock stock) {
    }



}





