/**
 *
 *
 *
 *
 *
 */
package jfreerails.client.reports;

import java.util.Vector;


/**
 *
 *
 *
 * @author lindsal
 */

public class RRFinancialSummary {


   // associations

    public Vector stockBrokersReport = new Vector();



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





