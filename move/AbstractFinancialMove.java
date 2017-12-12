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

public abstract class AbstractFinancialMove extends AbstractGameMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public ListedCompany listedCompany;

  ///////////////////////////////////////
  //access methods for attributes

    public ListedCompany getListedCompany() {
        return listedCompany;
    }
    public void setListedCompany(ListedCompany listedCompany) {
        this.listedCompany = listedCompany;
    }


}





