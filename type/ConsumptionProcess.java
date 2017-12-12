/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.type;

import java.util.*;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ConsumptionProcess {

   ///////////////////////////////////////
   // associations

    public CargoType cargoType;
    public SideEffect sideEffect;
    public IndustryType industryType;


   ///////////////////////////////////////
   // access methods for associations


    public CargoType getCargoType() {
        return cargoType;
    }
    public void setCargoType(CargoType cargoType) {
        if (this.cargoType != cargoType) {
            if (this.cargoType != null) 
                this.cargoType.removeInput(this);     
            this.cargoType = cargoType;
            if (cargoType != null)
                cargoType.addInput(this);  
        }
    } 

    public SideEffect getSideEffect() {
        return sideEffect;
    }
    public void setSideEffect(SideEffect sideEffect) {
        if (this.sideEffect != sideEffect) {
            this.sideEffect = sideEffect;
            if (sideEffect != null)
                sideEffect.setConsumptionProcess(this);  
        }      
    } 

    public IndustryType getIndustryType() {
        return industryType;
    }
    public void setIndustryType(IndustryType industryType) {
        if (this.industryType != industryType) {
            this.industryType = industryType;
            if (industryType != null)
                industryType.setConsumptionProcess(this);  
        }      
    } 



}





