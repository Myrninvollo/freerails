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

public class TransformationProcess {

   ///////////////////////////////////////
   // associations

    public Vector cargoType = new Vector();
    public CargoType cargoType_1;
    public SideEffect sideEffect;
    public CargoCollector cargoCollector;
    public IndustryType industryType;


   ///////////////////////////////////////
   // access methods for associations


    public Vector getCargoType() {
        return cargoType;
    }
    public void addCargoType(CargoType cargoType) {
        if (! this.cargoType.contains(cargoType)) {     
            this.cargoType.addElement(cargoType);  
            cargoType.addTransformationProcess(this);
        }
    }
    public void removeCargoType(CargoType cargoType) {
        if (this.cargoType.removeElement(cargoType)) {      
            cargoType.removeTransformationProcess(this);
        }
    }

    public CargoType getCargoType_1() {
        return cargoType_1;
    }
    public void setCargoType_1(CargoType cargoType) {
        if (this.cargoType_1 != cargoType) {
            if (this.cargoType_1 != null) 
                this.cargoType_1.removeTransformationProcess_1(this);     
            this.cargoType_1 = cargoType;
            if (cargoType != null)
                cargoType.addTransformationProcess_1(this);  
        }
    } 

    public SideEffect getSideEffect() {
        return sideEffect;
    }
    public void setSideEffect(SideEffect sideEffect) {
        if (this.sideEffect != sideEffect) {
            this.sideEffect = sideEffect;
            if (sideEffect != null)
                sideEffect.setTransformationProcess(this);  
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
                industryType.setTransformationProcess(this);  
        }      
    } 



}





