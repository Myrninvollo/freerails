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

public class RRFinancialSummary {

   ///////////////////////////////////////
   // associations

    public Vector stockBrokersReport = new Vector();


   ///////////////////////////////////////
   // access methods for associations


    public Vector getStockBrokersReport() {
        return stockBrokersReport;
    }
    public void addStockBrokersReport(StockBrokersReport stockBrokersReport) {
        if (! this.stockBrokersReport.contains(stockBrokersReport)) {     
            this.stockBrokersReport.addElement(stockBrokersReport);  
            stockBrokersReport.setRRFinancialSummary(this);
        }
    }
    public void removeStockBrokersReport(StockBrokersReport stockBrokersReport) {
        if (this.stockBrokersReport.removeElement(stockBrokersReport)) {      
            stockBrokersReport.setRRFinancialSummary(null);
        }
    }



}





