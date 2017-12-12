package jfreerails.world.stats;

import jfreerails.world.common.Money;

public class TrainStats {

	private int train;

	private Money revenueYTD;

	private Money revenueLastYear;

	private Money revenueLifeTime;

	public int getTrain() {
		return train;
	}
	public void setTrain(int train) {
		this.train = train;
	}
	public Money getRevenueYTD() {
		return revenueYTD;
	}
	public void setRevenueYTD(Money revenueYTD) {
		this.revenueYTD = revenueYTD;
	}
	public Money getRevenueLastYear() {
		return revenueLastYear;
	}
	public void setRevenueLastYear(Money revenueLastYear) {
		this.revenueLastYear = revenueLastYear;
	}
	public Money getRevenueLifeTime() {
		return revenueLifeTime;
	}
	public void setRevenueLifeTime(Money revenueLifeTime) {
		this.revenueLifeTime = revenueLifeTime;
	}

}
