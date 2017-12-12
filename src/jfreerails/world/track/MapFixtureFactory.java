package jfreerails.world.track;

import java.util.HashSet;
import java.util.Iterator;

import jfreerails.world.terrain.TerrainTileTypesList;
import jfreerails.world.terrain.TerrainType;

public class MapFixtureFactory {

	public int w = 10;
	public int h = 10;

	public TrackRuleList trackRuleList;

	public TerrainTileTypesList terrainTileTypesList;

	public MapFixtureFactory() {

		trackRuleList = generateTrackRuleList();
		terrainTileTypesList = getTerrainTileTypesList();

	}

	public TrackAndTerrainTileMap generateMap() {
//		try {
//			return new FreerailsMapImpl(w, h, terrainTileTypesList, trackRuleList);
//		} catch (FreerailsException e) {
//			e.printStackTrace();
			return null;
		//}
	}

	public static TerrainTileTypesList getTerrainTileTypesList() {

		//Quickly knock up a terrain types list...
		return new TerrainTileTypesList() {
			TerrainType tileType = new TerrainType() {
				public int getRGB() {
					return 0;
				}
				public String getTerrainTypeName() {
					return "mountain";
				}
			};
			public String getTerrainName(int rgb) {
				return "mountain";
			}
			public int getTerrainRGBValue(String name) {
				return 0;
			}
			public TerrainType getTerrainModel(int rgb) {
				return tileType;
			}
			public Iterator getIterator(){
				throw new UnsupportedOperationException();	
			}
		};

	}

	public static TrackRuleList generateTrackRuleList() {

		TrackRule[] trackRulesArray = new TrackRule[3];
		TrackRuleProperties[] trackRuleProperties = new TrackRuleProperties[3];
		LegalTrackConfigurations[] legalTrackConfigurations =
			new LegalTrackConfigurations[3];
		LegalTrackPlacement[] legalTrackPlacement = new LegalTrackPlacement[3];

		//1st track type..
		String[] trackTemplates0 =
			{
				"000010000",
				"010010000",
				"010010010",
				"100111000",
				"001111000",
				"010110000",
				"100110000" };

		legalTrackConfigurations[0] = new LegalTrackConfigurations(-1, trackTemplates0);
		trackRuleProperties[0] = new TrackRuleProperties(1, false, "type0", 0, false);
		legalTrackPlacement[0] =
			new LegalTrackPlacement(
				new HashSet(),
				LegalTrackPlacement.PlacementRule.ANYWHERE_EXCEPT_ON_THESE);
		trackRulesArray[0] =
			new TrackRuleImpl(
				trackRuleProperties[0],
				legalTrackConfigurations[0],
				legalTrackPlacement[0]);

		//2nd track type..
		String[] trackTemplates1 = { "000010000", "010010000", "010010010" };
		legalTrackConfigurations[1] = new LegalTrackConfigurations(-1, trackTemplates1);
		trackRuleProperties[1] = new TrackRuleProperties(2, false, "type1", 1, false);
		HashSet cannotBuildOnTheseTerrainTypes = new HashSet();
		cannotBuildOnTheseTerrainTypes.add("mountain");
		legalTrackPlacement[1] =
			new LegalTrackPlacement(
				cannotBuildOnTheseTerrainTypes,
				LegalTrackPlacement.PlacementRule.ANYWHERE_EXCEPT_ON_THESE);
		trackRulesArray[1] =
			new TrackRuleImpl(
				trackRuleProperties[1],
				legalTrackConfigurations[1],
				legalTrackPlacement[1]);

		//3rd track type..
		trackRuleProperties[2] = new TrackRuleProperties(3, false, "type2", 2, false);
		String[] trackTemplates2 = { "000010000" };
		legalTrackConfigurations[2] = new LegalTrackConfigurations(-1, trackTemplates2);
		legalTrackPlacement[2] =
			new LegalTrackPlacement(
				new HashSet(),
				LegalTrackPlacement.PlacementRule.ANYWHERE_EXCEPT_ON_THESE);
		trackRulesArray[2] =
			new TrackRuleImpl(
				trackRuleProperties[2],
				legalTrackConfigurations[2],
				legalTrackPlacement[2]);

		return new TrackRuleList(trackRulesArray);
	}

}