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

public class SideEffect {

   ///////////////////////////////////////
   // associations

    public ConsumptionProcess consumptionProcess;
    public TransformationProcess transformationProcess;
    public ProductionProcess productionProcess;


   ///////////////////////////////////////
   // access methods for associations


    public ConsumptionProcess getConsumptionProcess() {
        return consumptionProcess;
    }
    public void setConsumptionProcess(ConsumptionProcess consumptionProcess) {
        if (this.consumptionProcess != consumptionProcess) {
            this.consumptionProcess = consumptionProcess;
            if (consumptionProcess != null)
                consumptionProcess.setSideEffect(this);  
        }      
    } 

    public TransformationProcess getTransformationProcess() {
        return transformationProcess;
    }
    public void setTransformationProcess(TransformationProcess transformationProcess) {
        if (this.transformationProcess != transformationProcess) {
            this.transformationProcess = transformationProcess;
            if (transformationProcess != null)
                transformationProcess.setSideEffect(this);  
        }      
    } 

    public ProductionProcess getProductionProcess() {
        return productionProcess;
    }
    public void setProductionProcess(ProductionProcess productionProcess) {
        if (this.productionProcess != productionProcess) {
            this.productionProcess = productionProcess;
            if (productionProcess != null)
                productionProcess.setSideEffect(this);  
        }      
    } 



}





