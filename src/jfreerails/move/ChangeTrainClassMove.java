
package jfreerails.move;


import jfreerails.world.type.TrainClass;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public final class ChangeTrainClassMove {

    private final TrainClass oldClass;

    private final TrainClass newClass;

  
    public TrainClass getOldClass() {
        return oldClass;
    }

    public TrainClass getNewClass() {
        return newClass;
    }
    
    public ChangeTrainClassMove(TrainClass before, TrainClass after){
    	oldClass=before;
    	newClass=after;
    }
}





