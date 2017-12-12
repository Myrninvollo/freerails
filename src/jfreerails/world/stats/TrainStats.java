
package jfreerails.world.stats;

import jfreerails.world.misc.Money;
import jfreerails.world.train.TrainModel;

/**
 *
 *
 *
 * @author lindsal
 */

public class TrainStats {


    private TrainModel train;


    private Money revenueYTD;
/**
 *

 */

    private Money revenueLastYear;
/**
 *

 */

    private Money revenueLifeTime;



    public TrainModel getTrain() {
        return train;
    }
    public void setTrain(TrainModel train) {
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





