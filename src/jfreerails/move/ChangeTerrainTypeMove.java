package jfreerails.move;

import java.awt.Point;
import jfreerails.world.terrain.TerrainType;


/**
 *
 *
 *
 * @author lindsal
 */
final public class ChangeTerrainTypeMove {
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

    public ChangeTerrainTypeMove(Point p, TerrainType before, TerrainType after) {
        location = p;
        oldType = before;
        newType = after;
    }
}