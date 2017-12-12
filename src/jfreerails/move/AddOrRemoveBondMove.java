package jfreerails.move;

import jfreerails.world.financial.ListedCompany;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class AddOrRemoveBondMove implements FinancialMove {

	private final int outstandingBondsBefore;

	private final int outstandingBondsAfter;

	public int getOutstandingBondsBefore() {
		return outstandingBondsBefore;
	}

	public int getOutstandingBondsAfter() {
		return outstandingBondsAfter;
	}

        public ListedCompany getListedCompany() {
        throw new java.lang.UnsupportedOperationException(
			"Method not implemented yet!");	
        }
        
	public AddOrRemoveBondMove(int before, int after, ListedCompany lc) {
		
		outstandingBondsBefore = before;
		outstandingBondsAfter = after;
	}
}