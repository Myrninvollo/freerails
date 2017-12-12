/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package jfreerails.world.terrain;

/**
 * @version 	1.0
 * @author
 */
public class TerrainTileImpl implements TerrainTile {
	
	private final TerrainType terrainType;

	public int getRGB() {
		return 0;
	}
	public String getTypeName(){
		return terrainType.getTerrainTypeName();
	}
	public TerrainType getTerrainType(){
		return terrainType;
	}
	
	public TerrainTileImpl(TerrainType terrainType){
		this.terrainType=terrainType;	
	}

}
