
package jfreerails.misc.stats;

import jfreerails.misc.Money;
import jfreerails.world.train.TrainModelPublic;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class TrainStats {

 
    private TrainModelPublic train;


    private Money revenueYTD;
/**
 * Represents ...

 */

    private Money revenueLastYear;
/**
 * Represents ...

 */

    private Money revenueLifeTime;

  

    public TrainModelPublic getTrain() {
        return train;
    }
    public void setTrain(TrainModelPublic train) {
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





