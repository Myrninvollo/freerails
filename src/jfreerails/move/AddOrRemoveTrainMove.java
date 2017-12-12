
package jfreerails.move;


import java.awt.Point;
import jfreerails.type.EngineType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class AddOrRemoveTrainMove {


    private final Point location;


    private final EngineType engine;

    public Point getLocation() {
        return location;
    }

    public EngineType getEngine() {
        return engine;
    }
    
    public AddOrRemoveTrainMove(Point p, EngineType e){
    		location=p;
    		engine=e;
    }
}





