package jfreerails.world.terrain;

/**
 * This class defines a terrain tile type.
 *
 *
 * @author lindsal
 */

final public class TerrainTypeImpl implements TerrainType {

	private final String name;
	private final String category;
	private final int rgb;

	public String getTerrainTypeName() {
		return name;
	}

	public String getTerrainCategory() {
		return category;
	}

	public int getRGB() {
		return rgb;
	}

	public TerrainTypeImpl(String name, String category, int rgb) {
		this.name = name;
		this.category = category;
		this.rgb = rgb;
	}
}