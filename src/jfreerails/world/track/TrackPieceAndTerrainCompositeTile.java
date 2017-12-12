
package jfreerails.world.track;

import jfreerails.world.misc.FreerailsSerializable;
import jfreerails.world.terrain.TerrainTile;
import jfreerails.world.terrain.TerrainType;


/**
 * @version 	1.0
 */
final public class TrackPieceAndTerrainCompositeTile
	implements TrackPiece, TerrainTile, FreerailsSerializable {


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