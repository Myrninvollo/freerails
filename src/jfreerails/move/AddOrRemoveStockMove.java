package jfreerails.move;

import jfreerails.world.financial.ListedCompany;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class AddOrRemoveStockMove implements FinancialMove {

	private final int totalSharesIssuedBefore;

	private final int totalSharesIssuedAfter;

	private final ListedCompany lcompany;

	public int getTotalSharesIssuedBefore() {
		return totalSharesIssuedBefore;
	}

	public int getTotalSharesIssuedAfter() {
		return totalSharesIssuedAfter;
	}

	public ListedCompany getLcompany() {
		return lcompany;
	}
        
        public ListedCompany getListedCompany() {
        	throw new java.lang.UnsupportedOperationException(
				"Method not implemented yet!");
        }
        
	public AddOrRemoveStockMove(int nBefore, int nAfter, ListedCompany lc) {
		
		totalSharesIssuedBefore = nBefore;
		totalSharesIssuedAfter = nAfter;
		lcompany = lc;
	}
}