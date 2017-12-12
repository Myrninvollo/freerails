/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.type;

import java.util.*;

import jfreerails.list.IndustryList;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class IndustryType {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private int costToBuild;

   ///////////////////////////////////////
   // associations

    public TransformationProcess transformationProcess;
    public ProductionProcess productionProcess;
    public ConsumptionProcess consumptionProcess;
    public Vector industryList = new Vector();


  ///////////////////////////////////////
  //access methods for attributes

    public int getCostToBuild() {
        return costToBuild;
    }
    public void setCostToBuild(int costToBuild) {
        this.costToBuild = costToBuild;
    }

   ///////////////////////////////////////
   // access methods for associations


    public TransformationProcess getTransformationProcess() {
        return transformationProcess;
    }
    public void setTransformationProcess(TransformationProcess transformationProcess) {
        if (this.transformationProcess != transformationProcess) {
            this.transformationProcess = transformationProcess;
            if (transformationProcess != null)
                transformationProcess.setIndustryType(this);  
        }      
    } 

    public ProductionProcess getProductionProcess() {
        return productionProcess;
    }
    public void setProductionProcess(ProductionProcess productionProcess) {
        if (this.productionProcess != productionProcess) {
            this.productionProcess = productionProcess;
            if (productionProcess != null)
                productionProcess.setIndustryType(this);  
        }      
    } 

    public ConsumptionProcess getConsumptionProcess() {
        return consumptionProcess;
    }
    public void setConsumptionProcess(ConsumptionProcess consumptionProcess) {
        if (this.consumptionProcess != consumptionProcess) {
            this.consumptionProcess = consumptionProcess;
            if (consumptionProcess != null)
                consumptionProcess.setIndustryType(this);  
        }      
    } 

    public Vector getIndustryList() {
        return industryList;
    }
    public void addIndustryList(IndustryList industryList) {
        if (! this.industryList.contains(industryList)) {     
            this.industryList.addElement(industryList);  
            industryList.setIndustryType(this);
        }
    }
    public void removeIndustryList(IndustryList industryList) {
        if (this.industryList.removeElement(industryList)) {      
            industryList.setIndustryType(null);
        }
    }



}





