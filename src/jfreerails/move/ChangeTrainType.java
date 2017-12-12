
package jfreerails.move;


import jfreerails.type.TrainType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class ChangeTrainType {

 
    private final TrainType oldTrainType;

    private final TrainType newTrainType;

 

    public TrainType getOldTrainType() {
        return oldTrainType;
    }

    public TrainType getNewTrainType() {
        return newTrainType;
    }
    
    public ChangeTrainType(TrainType before, TrainType after){
    	oldTrainType=before;
    	newTrainType=after;
    }
}





