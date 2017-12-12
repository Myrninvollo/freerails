
package jfreerails.move;

import java.awt.Point;

import jfreerails.world.terrain.TerrainType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class ChangeTerrainTypeMove implements MapMove {



    private final Point location;


    private final TerrainType oldType;



    private final TerrainType newType;

    public Point getLocation() {
        return location;
    }

    public TerrainType getOldType() {
        return oldType;
    }

    public TerrainType getNewType() {
        return newType;
    }
    public ChangeTerrainTypeMove(Point p, TerrainType before, TerrainType after){
    	location=p;
    	oldType=before;
    	newType=after;
    	
    }
}





