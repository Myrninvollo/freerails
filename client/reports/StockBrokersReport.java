/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.client.reports;

import java.util.*;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class StockBrokersReport {

   ///////////////////////////////////////
   // associations

    public RRFinancialSummary rRFinancialSummary;


   ///////////////////////////////////////
   // access methods for associations


    public RRFinancialSummary getRRFinancialSummary() {
        return rRFinancialSummary;
    }
    public void setRRFinancialSummary(RRFinancialSummary rRFinancialSummary) {
        if (this.rRFinancialSummary != rRFinancialSummary) {
            if (this.rRFinancialSummary != null) 
                this.rRFinancialSummary.removeStockBrokersReport(this);     
            this.rRFinancialSummary = rRFinancialSummary;
            if (rRFinancialSummary != null)
                rRFinancialSummary.addStockBrokersReport(this);  
        }
    } 



}





