package jfreerails.world.financial;

public class StockBroker {

	public FinancialMarket financialMarket;
	public int rRCompany;

	public FinancialMarket getFinancialMarket() {
		return financialMarket;
	}
	public void setFinancialMarket(FinancialMarket financialMarket) {
		if (this.financialMarket != financialMarket) {
			this.financialMarket = financialMarket;
			if (financialMarket != null)
				financialMarket.setStockBroker(this);
		}
	}

	public int getRRCompany() {
		return rRCompany;
	}

	public void buyStock(ListedCompany company) {
	}

	public void sellStock(ListedCompany company) {
	}

	public void sellBond() {
	}

	public void repayBond() {
	}

}
