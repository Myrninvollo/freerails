package jfreerails.unittest.fixture;

import java.util.HashSet;

import jfreerails.common.exception.FreerailsException;
import jfreerails.constructorparam.TrackRuleImplConstructorParameters;
import jfreerails.list.TerrainTileTypesList;
import jfreerails.list.TrackRuleList;
import jfreerails.map.FreerailsMap;
import jfreerails.map.FreerailsMapImpl;
import jfreerails.type.TileType;
import jfreerails.type.TrackRule;
import jfreerails.type.TrackRuleImpl;

public class MapFixtureFactory {

	public int w = 10;
	public int h = 10;

	public TrackRuleList trackRuleList;

	public TerrainTileTypesList terrainTileTypesList;

	public MapFixtureFactory() {
		try {
			trackRuleList = generateTrackRuleList();
			terrainTileTypesList = getTerrainTileTypesList();
		} catch (FreerailsException e) {
			e.printStackTrace();
		}
	}

	public FreerailsMap generateMap() {
		try {
			return new FreerailsMapImpl(w, h, terrainTileTypesList, trackRuleList);
		} catch (FreerailsException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected TerrainTileTypesList getTerrainTileTypesList() {

		//Quickly knock up a terrain types list...
		TerrainTileTypesList terrainTileTypesList = new TerrainTileTypesList() {
			TileType tileType = new TileType() {
				public int getRGB() {
					return 0;
				}
				public String getTerrainType() {
					return "mountain";
				}
			};
			public String getTerrainName(int rgb) {
				return "mountain";
			}
			public int getTerrainRGBValue(String name) {
				return 0;
			}
			public TileType getTerrainModel(int rgb) {
				return tileType;
			}
		};
		return terrainTileTypesList;
	}

	protected TrackRuleList generateTrackRuleList() throws FreerailsException {

		TrackRule[] trackRulesArray = new TrackRule[3];

		//1st track type..
		TrackRuleImplConstructorParameters[] params =
			new TrackRuleImplConstructorParameters[3];
		params[0] = new TrackRuleImplConstructorParameters();

		String[] trackTemplates0 =
			{
				"000010000",
				"010010000",
				"010010010",
				"100111000",
				"001111000",
				"010110000",
				"100110000" };
		params[0].legalTrackTemplates = trackTemplates0;

		trackRulesArray[0] = new TrackRuleImpl(params[0]);

		//2nd track type..
		params[1] = new TrackRuleImplConstructorParameters();

		String[] trackTemplates1 = { "000010000", "010010000", "010010010" };

		params[1].legalTrackTemplates = trackTemplates1;

		params[1].cannotBuildOnTheseTerrainTypes = new HashSet();
		params[1].cannotBuildOnTheseTerrainTypes.add("mountain");
		trackRulesArray[1] = new TrackRuleImpl(params[1]);

		//3rd track type..
		params[2] = new TrackRuleImplConstructorParameters();

		String[] trackTemplates2 = { "000010000" };

		params[2].legalTrackTemplates = trackTemplates2;

		trackRulesArray[2] = new TrackRuleImpl(params[2]);

		return new TrackRuleList(trackRulesArray);
	}

}