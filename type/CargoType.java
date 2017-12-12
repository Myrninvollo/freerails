/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.type;

import java.util.*;

import java.lang.String;
import jfreerails.massnoun.CargoBatch;
import jfreerails.element.CargoBundle;
import jfreerails.element.StationModelPrivate;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class CargoType {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public int unitWeight;
/**
 * Represents ...

 */

    public int unitVolume;
/**
 * Represents ...

 */

    public String name;

   ///////////////////////////////////////
   // associations

    public Vector cargoBatch = new Vector();
    public CargoTypeList cargoTypeList;
    public Vector cargoBundle = new Vector();
    public StationModelPrivate stationModelPrivate;
    public StationModelPrivate stationModelPrivate_1;
    public Vector productionProcess = new Vector();
    public Vector transformationProcess = new Vector();
    public Vector transformationProcess_1 = new Vector();
    public Vector input = new Vector();


  ///////////////////////////////////////
  //access methods for attributes

    public int getUnitWeight() {
        return unitWeight;
    }
    public void setUnitWeight(int unitWeight) {
        this.unitWeight = unitWeight;
    }
    public int getUnitVolume() {
        return unitVolume;
    }
    public void setUnitVolume(int unitVolume) {
        this.unitVolume = unitVolume;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

   ///////////////////////////////////////
   // access methods for associations


    public Vector getCargoBatch() {
        return cargoBatch;
    }
    public void addCargoBatch(CargoBatch cargoBatch) {
        if (! this.cargoBatch.contains(cargoBatch)) {     
            this.cargoBatch.addElement(cargoBatch);  
            cargoBatch.setCargoType(this);
        }
    }
    public void removeCargoBatch(CargoBatch cargoBatch) {
        if (this.cargoBatch.removeElement(cargoBatch)) {      
            cargoBatch.setCargoType(null);
        }
    }

    public CargoTypeList getCargoTypeList() {
        return cargoTypeList;
    }
    public void setCargoTypeList(CargoTypeList cargoTypeList) {
        if (this.cargoTypeList != cargoTypeList) {
            if (this.cargoTypeList != null) 
                this.cargoTypeList.removeCargoType(this);     
            this.cargoTypeList = cargoTypeList;
            if (cargoTypeList != null)
                cargoTypeList.addCargoType(this);  
        }
    } 

    public Vector getCargoBundle() {
        return cargoBundle;
    }
    public void addCargoBundle(CargoBundle cargoBundle) {
        if (! this.cargoBundle.contains(cargoBundle)) {     
            this.cargoBundle.addElement(cargoBundle);  
            cargoBundle.setCargoType(this);
        }
    }
    public void removeCargoBundle(CargoBundle cargoBundle) {
        if (this.cargoBundle.removeElement(cargoBundle)) {      
            cargoBundle.setCargoType(null);
        }
    }

    public StationModelPrivate getStationModelPrivate() {
        return stationModelPrivate;
    }
    public void setStationModelPrivate(StationModelPrivate stationModelPrivate) {
        if (this.stationModelPrivate != stationModelPrivate) {
            this.stationModelPrivate = stationModelPrivate;
            if (stationModelPrivate != null)
                stationModelPrivate.setCargoType(this);  
        }      
    } 

    public StationModelPrivate getStationModelPrivate_1() {
        return stationModelPrivate_1;
    }
    public void setStationModelPrivate_1(StationModelPrivate stationModelPrivate) {
        if (this.stationModelPrivate_1 != stationModelPrivate) {
            this.stationModelPrivate_1 = stationModelPrivate;
            if (stationModelPrivate != null)
                stationModelPrivate.setCargoType_1(this);  
        }      
    } 

    public Vector getProductionProcess() {
        return productionProcess;
    }
    public void addProductionProcess(ProductionProcess productionProcess) {
        if (! this.productionProcess.contains(productionProcess)) {     
            this.productionProcess.addElement(productionProcess);  
            productionProcess.setCargoType(this);
        }
    }
    public void removeProductionProcess(ProductionProcess productionProcess) {
        if (this.productionProcess.removeElement(productionProcess)) {      
            productionProcess.setCargoType(null);
        }
    }

    public Vector getTransformationProcess() {
        return transformationProcess;
    }
    public void addTransformationProcess(TransformationProcess transformationProcess) {
        if (! this.transformationProcess.contains(transformationProcess)) {     
            this.transformationProcess.addElement(transformationProcess);  
            transformationProcess.addCargoType(this);
        }
    }
    public void removeTransformationProcess(TransformationProcess transformationProcess) {
        if (this.transformationProcess.removeElement(transformationProcess)) {      
            transformationProcess.removeCargoType(this);
        }
    }

    public Vector getTransformationProcess_1() {
        return transformationProcess_1;
    }
    public void addTransformationProcess_1(TransformationProcess transformationProcess) {
        if (! this.transformationProcess_1.contains(transformationProcess)) {     
            this.transformationProcess_1.addElement(transformationProcess);  
            transformationProcess.setCargoType_1(this);
        }
    }
    public void removeTransformationProcess_1(TransformationProcess transformationProcess) {
        if (this.transformationProcess_1.removeElement(transformationProcess)) {      
            transformationProcess.setCargoType_1(null);
        }
    }

    public Vector getInput() {
        return input;
    }
    public void addInput(ConsumptionProcess consumptionProcess) {
        if (! this.input.contains(consumptionProcess)) {     
            this.input.addElement(consumptionProcess);  
            consumptionProcess.setCargoType(this);
        }
    }
    public void removeInput(ConsumptionProcess consumptionProcess) {
        if (this.input.removeElement(consumptionProcess)) {      
            consumptionProcess.setCargoType(null);
        }
    }



}





