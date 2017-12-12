/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package jfreerails.world.std_track;

import jfreerails.type.TrackRule;
import jfreerails.world.terrain.TerrainType;
import jfreerails.world.terrain.TerrainTile;

/**
 * @version 	1.0
 * @author
 */
final public class TrackPieceAndTerrainCompositeTile
	implements TrackPiece, TerrainTile {
		
	
	private final TrackPiece trackPiece;

	private final TerrainType terrainType;

	public TrackPieceAndTerrainCompositeTile(TerrainType terrainType) {
		this.terrainType = terrainType;
		this.trackPiece = NullTrackPiece.getInstance();

	}
	public TrackPieceAndTerrainCompositeTile(
		TerrainType terrainType,
		TrackPiece trackPiece) {
		this.terrainType = terrainType;
		this.trackPiece = trackPiece;

	}
	

	/*
	 * @see TrackPiece#getTrackGraphicNumber()
	 */
	public int getTrackGraphicNumber() {
		return trackPiece.getTrackGraphicNumber();
	}

	/*
	 * @see Tile#getRGB()
	 */
	public int getRGB() {
		if(trackPiece== NullTrackPiece.getInstance()){
			return terrainType.getRGB();
		}else{
			return 0;
		}		
	}

	/*
	 * @see TrackPiece#getTrackRule()
	 */
	public TrackRule getTrackRule() {
		return trackPiece.getTrackRule();
	}

	/*
	 * @see TrackPiece#getTrackConfiguration()
	 */
	public TrackConfiguration getTrackConfiguration() {
		return trackPiece.getTrackConfiguration();
	}

	/*
	 * @see TerrainType#getTerrainType()
	 */
	public TerrainType getTerrainType() {
		return terrainType;
	}
	public String getTypeName(){
		return terrainType.getTerrainTypeName();
	}

}