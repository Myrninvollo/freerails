
package jfreerails.list;




/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class GameElementsList {

  

    public IndustryList industryList;
    public StationList stationList;
    public CityList cityList;
    public TrainList trainList;
    public RailwayVehicleList railwayVehicleList;
    public CompanyList companyList;
    //public TrackSystem trackSystem;
    public BondList bondList;
    public StockList stockList;


   ///////////////////////////////////////
   // access methods for associations


    public IndustryList getIndustryList() {
        return industryList;
    }
    public void setIndustryList(IndustryList industryList) {
        if (this.industryList != industryList) {
            this.industryList = industryList;
            if (industryList != null)
                industryList.setGameElementsList(this);  
        }      
    } 

    public StationList getStationList() {
        return stationList;
    }
    public void setStationList(StationList stationList) {
        if (this.stationList != stationList) {
            this.stationList = stationList;
            if (stationList != null)
                stationList.setGameElementsList(this);  
        }      
    } 

    public CityList getCityList() {
        return cityList;
    }
    public void setCityList(CityList cityList) {
        if (this.cityList != cityList) {
            this.cityList = cityList;
            if (cityList != null)
                cityList.setGameElementsList(this);  
        }      
    } 

    public TrainList getTrainList() {
        return trainList;
    }
    public void setTrainList(TrainList trainList) {
        if (this.trainList != trainList) {
            this.trainList = trainList;
            if (trainList != null)
                trainList.setGameElementsList(this);  
        }      
    } 

    public RailwayVehicleList getRailwayVehicleList() {
        return railwayVehicleList;
    }
    public void setRailwayVehicleList(RailwayVehicleList railwayVehicleList) {
       
    } 

    public CompanyList getCompanyList() {
        return companyList;
    }
    public void setCompanyList(CompanyList companyList) {
        if (this.companyList != companyList) {
            this.companyList = companyList;
            if (companyList != null)
                companyList.setGameElementsList(this);  
        }      
    } 

//    public TrackSystem getTrackSystem() {
//        return trackSystem;
//    }
//    public void setTrackSystem(TrackSystem trackSystem) {
//        if (this.trackSystem != trackSystem) {
//            this.trackSystem = trackSystem;
////            if (trackSystem != null)
////                trackSystem.setGameElements(this);  
//        }      
//    } 

    public BondList getBondList() {
        return bondList;
    }
    public void setBondList(BondList bondList) {
        if (this.bondList != bondList) {
            this.bondList = bondList;
            if (bondList != null)
                bondList.setGameElementsList(this);  
        }      
    } 

    public StockList getStockList() {
        return stockList;
    }
    public void setStockList(StockList stockList) {
        if (this.stockList != stockList) {
            this.stockList = stockList;
            if (stockList != null)
                stockList.setGameElementsList(this);  
        }      
    } 



}





