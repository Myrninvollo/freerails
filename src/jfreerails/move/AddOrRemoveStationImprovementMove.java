
package jfreerails.move;


import jfreerails.world.station.StationImprovement;

/**
 *
 *
 *
 * @author lindsal
 */

final public class AddOrRemoveStationImprovementMove {

    //private final boolean create;

    private final StationImprovement improvement;



    public StationImprovement getImprovement() {
        return improvement;
    }
    public AddOrRemoveStationImprovementMove(StationImprovement im){
    	improvement=im;
    }
}



