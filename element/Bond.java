/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.massnoun.GameTime;
import jfreerails.list.BondList;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class Bond {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private GameTime issueDate;
/**
 * Represents ...

 */

    private int interestRate;

   ///////////////////////////////////////
   // associations

    public ListedCompany listedCompany;
    public FinancialMarket financialMarket;
    public BondList bondList;


  ///////////////////////////////////////
  //access methods for attributes

    public GameTime getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(GameTime issueDate) {
        this.issueDate = issueDate;
    }
    public int getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

   ///////////////////////////////////////
   // access methods for associations


    public ListedCompany getListedCompany() {
        return listedCompany;
    }
    public void setListedCompany(ListedCompany listedCompany) {
            this.listedCompany = listedCompany;
    } 

    public FinancialMarket getFinancialMarket() {
        return financialMarket;
    }
    public void setFinancialMarket(FinancialMarket financialMarket) {
        if (this.financialMarket != financialMarket) {
            if (this.financialMarket != null) 
                this.financialMarket.removeBond(this);     
            this.financialMarket = financialMarket;
            if (financialMarket != null)
                financialMarket.addBond(this);  
        }
    } 

    public BondList getBondList() {
        return bondList;
    }
    public void setBondList(BondList bondList) {
        if (this.bondList != bondList) {
            this.bondList = bondList;
            if (bondList != null)
                bondList.setBond(this);  
        }      
    } 



}





