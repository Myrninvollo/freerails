package jfreerails.world.financial;

import jfreerails.world.common.GameTime;

/**
 *
 *
 *
 * @author lindsal
 */

final public class Bond {

	private final GameTime issueDate;

	private final int interestRate;

	private final ListedCompany listedCompany;


	public GameTime getIssueDate() {
		return issueDate;
	}

	public int getInterestRate() {
		return interestRate;
	}

	public ListedCompany getListedCompany() {
		return listedCompany;
	}
	public Bond(int rate, GameTime issued, ListedCompany lc){
		interestRate=rate;
		issueDate=issued;
		listedCompany=lc;
	}


}