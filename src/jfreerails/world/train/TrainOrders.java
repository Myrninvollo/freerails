/*
 * TrainOrders.java
 *
 * Created on 31 March 2003, 23:17
 */

package jfreerails.world.train;

/**
 *
 * @author  Luke
 */
public class TrainOrders {
    
    public final boolean waitUntilFull;
    
    public final int[] consist;;   //The wagon types to add; if null, then no change.
    
    public final int station; //The number of the station to goto.
    
    /** Creates a new instance of TrainOrders */
    public TrainOrders(int station, int[] newConsist, boolean wait) {
        waitUntilFull=wait;
        consist= newConsist;
        this.station = station;
    }
    
}
