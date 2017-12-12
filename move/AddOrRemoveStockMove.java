/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.element.ListedCompany;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class AddOrRemoveStockMove extends AbstractFinancialMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public int totalSharesIssuedBefore;
/**
 * Represents ...

 */

    public int totalSharesIssuedAfter;
/**
 * Represents ...

 */

    public ListedCompany lcompany;

   ///////////////////////////////////////
   // associations



  ///////////////////////////////////////
  //access methods for attributes

    public int getTotalSharesIssuedBefore() {
        return totalSharesIssuedBefore;
    }
    public void setTotalSharesIssuedBefore(int totalSharesIssuedBefore) {
        this.totalSharesIssuedBefore = totalSharesIssuedBefore;
    }
    public int getTotalSharesIssuedAfter() {
        return totalSharesIssuedAfter;
    }
    public void setTotalSharesIssuedAfter(int totalSharesIssuedAfter) {
        this.totalSharesIssuedAfter = totalSharesIssuedAfter;
    }
    public ListedCompany getLcompany() {
        return lcompany;
    }
    public void setLcompany(ListedCompany lcompany) {
        this.lcompany = lcompany;
    }

   ///////////////////////////////////////
   // access methods for associations




}





