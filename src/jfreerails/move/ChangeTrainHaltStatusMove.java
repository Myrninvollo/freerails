
package jfreerails.move;



/**
 *
 *
 *
 * @author lindsal
 */

final public class ChangeTrainHaltStatusMove {

    private final boolean newHalt;


    private final boolean oldHalt;


    public boolean getNewHalt() {
        return newHalt;
    }

    public boolean getOldHalt() {
        return oldHalt;
    }

    public ChangeTrainHaltStatusMove(boolean before, boolean after){
    	newHalt=after;
    	oldHalt=before;
    }
}





