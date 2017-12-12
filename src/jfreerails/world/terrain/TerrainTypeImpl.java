package jfreerails.world.terrain;

/**
 * This class defines a terrain tile type.
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class TerrainTypeImpl implements TerrainType {

	private final String name;

	private final int rgb;

	public String getTerrainTypeName() {
		return name;
	}

	public int getRGB() {
		return rgb;
	}

	public TerrainTypeImpl(String name, int rgb) {
		this.name = name;
		this.rgb = rgb;
	}
}