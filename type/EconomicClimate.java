/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.type;

import java.util.*;

import jfreerails.element.FinancialMarket;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class EconomicClimate {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private int climateInterestRate;
/**
 * Represents ...

 */

    private int climateName;

   ///////////////////////////////////////
   // associations

    public FinancialMarket financialMarket;


  ///////////////////////////////////////
  //access methods for attributes

    public int getClimateInterestRate() {
        return climateInterestRate;
    }
    public void setClimateInterestRate(int climateInterestRate) {
        this.climateInterestRate = climateInterestRate;
    }
    public int getClimateName() {
        return climateName;
    }
    public void setClimateName(int climateName) {
        this.climateName = climateName;
    }

   ///////////////////////////////////////
   // access methods for associations


    public FinancialMarket getFinancialMarket() {
        return financialMarket;
    }
    public void setFinancialMarket(FinancialMarket financialMarket) {
        if (this.financialMarket != financialMarket) {
            this.financialMarket = financialMarket;
            if (financialMarket != null)
                financialMarket.setEconomicClimate(this);  
        }      
    } 



}





