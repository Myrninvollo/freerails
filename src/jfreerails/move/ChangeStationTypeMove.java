
package jfreerails.move;

import jfreerails.world.type.StationType;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class ChangeStationTypeMove {

 

    private final StationType oldType;

    private final StationType newType;

  

    public StationType getOldType() {
        return oldType;
    }

    public StationType getNewType() {
        return newType;
    }
    public ChangeStationTypeMove (StationType before, StationType after){
    	oldType=before;
    	newType=after;
    }
}





