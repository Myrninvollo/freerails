/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import jfreerails.controller.RROperator;
import jfreerails.controller.StockBroker;
import jfreerails.controller.TrackBuilderRules;
import jfreerails.element.container.RROwner;
import jfreerails.element.container.StockOwner;
import jfreerails.list.IndustryList;
import jfreerails.list.StationList;
import jfreerails.list.TrainList;
import jfreerails.map.TrackSystem;
import jfreerails.massnoun.Money;
import jfreerails.misc.GameWorld;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class RRCompany implements StockOwner, ListedCompany, RROwner {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private int numberOfBankruptcies;
/**
 * Represents ...

 */

    private Money netWorth;
/**
 * Represents ...

 */

    private String rRName;

   ///////////////////////////////////////
   // associations

    public TrackBuilderRules trackBuilderRules;
    public ThePublic thePublic;
    public CurrentAccount currentAccount;
    public GameWorld gameWorld;
    public StationList stationList;
    public TrackSystem trackSystem;
    public IndustryList industryList;
    public TrainList trainList;
    public StockBroker stockBroker;
    public RROwner controlledRRs;
    public RROperator rROperator;


  ///////////////////////////////////////
  //access methods for attributes

    public int getNumberOfBankruptcies() {
        return numberOfBankruptcies;
    }
    public void setNumberOfBankruptcies(int numberOfBankruptcies) {
        this.numberOfBankruptcies = numberOfBankruptcies;
    }
    public Money getNetWorth() {
        return netWorth;
    }
    public void setNetWorth(Money netWorth) {
        this.netWorth = netWorth;
    }
    public String getRRName() {
        return rRName;
    }
    public void setRRName(String rRName) {
        this.rRName = rRName;
    }

   ///////////////////////////////////////
   // access methods for associations


    public TrackBuilderRules getTrackBuilderRules() {
        return trackBuilderRules;
    }
    public void setTrackBuilderRules(TrackBuilderRules trackBuilderRules) {
        if (this.trackBuilderRules != trackBuilderRules) {
            this.trackBuilderRules = trackBuilderRules;
            if (trackBuilderRules != null)
                trackBuilderRules.setRRCompany(this);  
        }      
    } 

    public ThePublic getThePublic() {
        return thePublic;
    }
    public void setThePublic(ThePublic thePublic) {
        if (this.thePublic != thePublic) {
            if (this.thePublic != null) 
                this.thePublic.removeRRCompany(this);     
            this.thePublic = thePublic;
            if (thePublic != null)
                thePublic.addRRCompany(this);  
        }
    } 

    public CurrentAccount getCurrentAccount() {
        return currentAccount;
    }
    public void setCurrentAccount(CurrentAccount currentAccount) {
        if (this.currentAccount != currentAccount) {
            this.currentAccount = currentAccount;
            if (currentAccount != null)
                currentAccount.setRRCompany(this);  
        }      
    } 

    public GameWorld getGameWorld() {
        return gameWorld;
    }
    public void setGameWorld(GameWorld gameWorld) {
        if (this.gameWorld != gameWorld) {
            if (this.gameWorld != null) 
                this.gameWorld.removeRRCompany(this);     
            this.gameWorld = gameWorld;
            if (gameWorld != null)
                gameWorld.addRRCompany(this);  
        }
    } 

    public StationList getStationList() {
        return stationList;
    }
    public void setStationList(StationList stationList) {
        if (this.stationList != stationList) {
            this.stationList = stationList;
            if (stationList != null)
                stationList.setRRCompany(this);  
        }      
    } 

    

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

    public TrainList getTrainList() {
        return trainList;
    }
    public void setTrainList(TrainList trainList) {
        if (this.trainList != trainList) {
            this.trainList = trainList;
            if (trainList != null)
                trainList.setRRCompany(this);  
        }      
    } 

    public StockBroker getStockBroker() {
        return stockBroker;
    }
    public void setStockBroker(StockBroker stockBroker) {
        if (this.stockBroker != stockBroker) {
            this.stockBroker = stockBroker;
            if (stockBroker != null)
                stockBroker.setRRCompany(this);  
        }      
    } 

    public RROwner getControlledRRs() {
        return controlledRRs;
    }
    public void setControlledRRs(RROwner rROwner) {
            this.controlledRRs = rROwner;
    } 

    public RROperator getRROperator() {
        return rROperator;
    }
    public void setRROperator(RROperator rROperator) {
        if (this.rROperator != rROperator) {
            this.rROperator = rROperator;
            if (rROperator != null)
                rROperator.setRRCompany(this);  
        }      
    } 


  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @param inThisCompany ...
 * @return A int with ...
 */

    public int getNumberOfSharesOwned(ListedCompany inThisCompany) {
        return 0;
    }
/**
 * Does ...
 * 
 * @param stock ...
 */

    public void addStock(Stock stock) {
    }
/**
 * Does ...
 * 

 */

    public void getNumberOfOutstandingBonds() {
    }
/**
 * Does ...
 * 

 */

    public void getStockPrice() {
    }
/**
 * Does ...
 * 
 * @return A Company with ...
 */

    public Company getOwner() {
        return null;
    }



}





