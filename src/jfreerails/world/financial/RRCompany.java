package jfreerails.world.financial;

import jfreerails.list.IndustryList;

public class RRCompany implements StockOwner, RROwner {

	public IndustryList industryList;
	private CompanyDetails details;
	private CompanyFinancialPosition financialPosition;
	private Railroad railroad;

	public IndustryList getIndustryList() {
		return industryList;
	}
	public void setIndustryList(IndustryList industryList) {
		if (this.industryList != industryList) {
			this.industryList = industryList;
			if (industryList != null)
				industryList.setRRCompany(this);
		}
	}

	public CompanyDetails getDetails() {
		return details;
	}

	public void setDetails(CompanyDetails details) {
		this.details = details;
	}

	public CompanyFinancialPosition getFinancialPosition() {
		return financialPosition;
	}

	public void setFinancialPosition(CompanyFinancialPosition financialPosition) {
		this.financialPosition = financialPosition;
	}

	public Railroad getRailroad() {
		return railroad;
	}

	public void setRailroad(Railroad railroad) {
		this.railroad = railroad;
	}

	public int getNumberOfSharesOwned(ListedCompany inThisCompany) {
		return 0;
	}

	public void addStock(Stock stock) {
	}

}