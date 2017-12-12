/**
 *
 *
 *
 *
 *
 */
package jfreerails.client.reports;



/**
 *
 *
 *
 * @author lindsal
 */

public class StockBrokersReport {


   // associations

    public RRFinancialSummary rRFinancialSummary;



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





