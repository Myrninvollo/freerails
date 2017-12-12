/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.*;

import jfreerails.misc.TrainPosition;
import jfreerails.element.container.TrainContainer;
import jfreerails.list.TrainList;
import jfreerails.type.TrainClass;

/**
 * Represents ...
 * 
 * Since trains have length,
 * they can straddle several 
 * track sections.
 * @see OtherClasses
 * @author lindsal
 */

public class TrainModelPublic {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private int trainLength;

   ///////////////////////////////////////
   // associations

    public Vector signalBlock = new Vector();
    public TrainModelPrivate trainModelPrivate;
    public TrainPosition trainPosition;
    public TrainContainer trainContainer;
    public Vector trainList = new Vector();
    public Vector wagonModel = new Vector();
    public Vector engineModel = new Vector();
    public TrainClass trainClass;


  ///////////////////////////////////////
  //access methods for attributes

    public int getTrainLength() {
        return trainLength;
    }
    public void setTrainLength(int trainLength) {
        this.trainLength = trainLength;
    }

   ///////////////////////////////////////
   // access methods for associations


    public Vector getSignalBlock() {
        return signalBlock;
    }
    public void addSignalBlock(SignalBlock signalBlock) {
        if (! this.signalBlock.contains(signalBlock)) {     
            this.signalBlock.addElement(signalBlock);  
            signalBlock.setTrainModelPublic(this);
        }
    }
    public void removeSignalBlock(SignalBlock signalBlock) {
        if (this.signalBlock.removeElement(signalBlock)) {      
            signalBlock.setTrainModelPublic(null);
        }
    }

    public TrainModelPrivate getTrainModelPrivate() {
        return trainModelPrivate;
    }
    public void setTrainModelPrivate(TrainModelPrivate trainModelPrivate) {
        if (this.trainModelPrivate != trainModelPrivate) {
            this.trainModelPrivate = trainModelPrivate;
            if (trainModelPrivate != null)
                trainModelPrivate.setTrainModelPublic(this);  
        }      
    } 

    public TrainPosition getTrainPosition() {
        return trainPosition;
    }
    public void setTrainPosition(TrainPosition trainPosition) {
            this.trainPosition = trainPosition;
    } 

    public TrainContainer getTrainContainer() {
        return trainContainer;
    }
    public void setTrainContainer(TrainContainer trainContainer) {
            this.trainContainer = trainContainer;
    } 

    public Vector getTrainList() {
        return trainList;
    }
    public void addTrainList(TrainList trainList) {
        if (! this.trainList.contains(trainList)) {     
            this.trainList.addElement(trainList);  
            trainList.setTrainModelPublic(this);
        }
    }
    public void removeTrainList(TrainList trainList) {
        if (this.trainList.removeElement(trainList)) {      
            trainList.setTrainModelPublic(null);
        }
    }

    public Vector getWagonModel() {
        return wagonModel;
    }
    public void addWagonModel(WagonModel wagonModel) {
        if (! this.wagonModel.contains(wagonModel)) {     
            this.wagonModel.addElement(wagonModel);  
            wagonModel.setTrainModelPublic(this);
        }
    }
    public void removeWagonModel(WagonModel wagonModel) {
        if (this.wagonModel.removeElement(wagonModel)) {      
            wagonModel.setTrainModelPublic(null);
        }
    }

    public Vector getEngineModel() {
        return engineModel;
    }
    public void addEngineModel(EngineModel engineModel) {
        if (! this.engineModel.contains(engineModel)) {     
            this.engineModel.addElement(engineModel);  
            engineModel.setTrainModelPublic(this);
        }
    }
    public void removeEngineModel(EngineModel engineModel) {
        if (this.engineModel.removeElement(engineModel)) {      
            engineModel.setTrainModelPublic(null);
        }
    }

    public TrainClass getTrainClass() {
        return trainClass;
    }
    public void setTrainClass(TrainClass trainClass) {
        if (this.trainClass != trainClass) {
            this.trainClass = trainClass;
            if (trainClass != null)
                trainClass.setTrainModelPublic(this);  
        }      
    } 


  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 

 */

    public void moveTrainToNextTrackSection() {
    }



}





