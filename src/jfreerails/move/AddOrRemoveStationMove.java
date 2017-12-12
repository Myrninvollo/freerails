
package jfreerails.move;

import java.awt.Point;

import jfreerails.type.StationType;
import jfreerails.type.TrackRule;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class AddOrRemoveStationMove {


    private final Point location;

    private final TrackRule oldType;


    private final StationType newType;


    private final String name;


    public Point getLocation() {
        return location;
    }

    public TrackRule getOldType() {
        return oldType;
    }

    public StationType getNewType() {
        return newType;
    }

    public String getName() {
        return name;
    }
    public AddOrRemoveStationMove(Point location, TrackRule oldType, StationType newType, String name){
    	//trackNode=node;
    	this.location=location;
    	this.oldType=oldType;
    	this.newType=newType;
    	this.name=name;
    }
}





