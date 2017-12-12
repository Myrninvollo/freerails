/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.type;

import java.util.*;

import jfreerails.massnoun.Money;
import jfreerails.element.EngineModel;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class EngineType extends RailwayVehicleType {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private int engineTypeName;
/**
 * Represents ...

 */

    private int powerAtDrawbar;
/**
 * Represents ...

 */

    private Money price;

   ///////////////////////////////////////
   // associations

    public EngineModel engineModel;


  ///////////////////////////////////////
  //access methods for attributes

    public int getEngineTypeName() {
        return engineTypeName;
    }
    public void setEngineTypeName(int engineTypeName) {
        this.engineTypeName = engineTypeName;
    }
    public int getPowerAtDrawbar() {
        return powerAtDrawbar;
    }
    public void setPowerAtDrawbar(int powerAtDrawbar) {
        this.powerAtDrawbar = powerAtDrawbar;
    }
    public Money getPrice() {
        return price;
    }
    public void setPrice(Money price) {
        this.price = price;
    }

   ///////////////////////////////////////
   // access methods for associations


    public EngineModel getEngineModel() {
        return engineModel;
    }
    public void setEngineModel(EngineModel engineModel) {
        if (this.engineModel != engineModel) {
            if (this.engineModel != null) 
                this.engineModel.removeEngineType(this);     
            this.engineModel = engineModel;
            if (engineModel != null)
                engineModel.addEngineType(this);  
        }
    } 


  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @param speed ...
 * @param grade ...
 */

    public void getRatedTrainSpeedAtGrade(int speed, int grade) {
    }



}





