package jfreerails.world.financial;

import jfreerails.world.common.Money;

public class CurrentAccount {

	private Money balance;

	public int rRCompany;

	public Money getBalance() {
		return balance;
	}
	public void setBalance(Money balance) {
		this.balance = balance;
	}

	public int getRRCompany() {
		return rRCompany;
	}

	public void creditAccount(Money amount, CreditTransactionType type) {
	}

	public void debitAccount(Money amount, DebitTransactionType type) {
	}

}
