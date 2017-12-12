/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.type;

import java.util.*;

import jfreerails.element.container.CargoCollector;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ProductionProcess {

   ///////////////////////////////////////
   // associations

    public CargoType cargoType;
    public SideEffect sideEffect;
    public CargoCollector cargoCollector;
    public IndustryType industryType;


   ///////////////////////////////////////
   // access methods for associations


    public CargoType getCargoType() {
        return cargoType;
    }
    public void setCargoType(CargoType cargoType) {
        if (this.cargoType != cargoType) {
            if (this.cargoType != null) 
                this.cargoType.removeProductionProcess(this);     
            this.cargoType = cargoType;
            if (cargoType != null)
                cargoType.addProductionProcess(this);  
        }
    } 

    public SideEffect getSideEffect() {
        return sideEffect;
    }
    public void setSideEffect(SideEffect sideEffect) {
        if (this.sideEffect != sideEffect) {
            this.sideEffect = sideEffect;
            if (sideEffect != null)
                sideEffect.setProductionProcess(this);  
        }      
    } 

    public CargoCollector getCargoCollector() {
        return cargoCollector;
    }
    public void setCargoCollector(CargoCollector cargoCollector) {
            this.cargoCollector = cargoCollector;
    } 

    public IndustryType getIndustryType() {
        return industryType;
    }
    public void setIndustryType(IndustryType industryType) {
        if (this.industryType != industryType) {
            this.industryType = industryType;
            if (industryType != null)
                industryType.setProductionProcess(this);  
        }      
    } 



}





