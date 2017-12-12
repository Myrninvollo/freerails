/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.misc.stats;

import java.util.*;

import jfreerails.element.TrainModelPublic;
import jfreerails.massnoun.Money;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class TrainStats {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private TrainModelPublic train;
/**
 * Represents ...

 */

    private Money revenueYTD;
/**
 * Represents ...

 */

    private Money revenueLastYear;
/**
 * Represents ...

 */

    private Money revenueLifeTime;

  ///////////////////////////////////////
  //access methods for attributes

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





