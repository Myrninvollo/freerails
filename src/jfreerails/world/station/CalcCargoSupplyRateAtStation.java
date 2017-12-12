/** 
 * @author Scott Bennett
 * Created: 9th May 2003
 * 
 * This class probes the tiles adjacent to a station for what cargo they supply
 * and then returns a vector of these cargo rates
 */

package jfreerails.world.station;

import java.util.Vector;

import jfreerails.world.cargo.CargoType;
import jfreerails.world.terrain.Consumption;
import jfreerails.world.terrain.Conversion;
import jfreerails.world.terrain.Production;
import jfreerails.world.terrain.TerrainType;
import jfreerails.world.top.KEY;
import jfreerails.world.top.World;

public class CalcCargoSupplyRateAtStation {

	/** The threshold that demand for a cargo must exceed before the station demands the cargo */
	private static final int PREREQUISITE_FOR_DEMAND = 16;

	private World w;
	private int x;
	private int y;

	Vector supplies;
	private int[] demand;

	public CalcCargoSupplyRateAtStation(World world, int X, int Y) {

		this.w = world;
		this.x = X;
		this.y = Y;

		if (x < 2) {
			x = 2;
		}
		if (y < 2) {
			y = 2;
		}

		supplies = new Vector();
		PopulateSuppliesVector();
		demand = new int[w.size(KEY.CARGO_TYPES)];
		//test output
		//System.out.println("size of supplies is: " + supplies.size());

	}

	public void PopulateSuppliesVector() {
		//fill supplies vector with 0 values for all cargo types
		//get the correct list of cargoes from the world object

		CargoElementObject tempCargoElement;
		CargoType cT;
		int type;

		for (int i = 0; i < w.size(KEY.CARGO_TYPES); i++) {
			cT = (CargoType) w.get(KEY.CARGO_TYPES, i);

			//test output
			//System.out.println("cT: " + i + " " + cT.getName() + 
			//	" " + cT.getCategory() + " " + cT.getUnitWeight());

			tempCargoElement = new CargoElementObject(0, i);
			supplies.add(tempCargoElement);
		}
	}

	public Vector ScanAdjacentTiles() {

		//look at the terrain type of each tile and retrieve the cargo supplied
		//Changed to look 2 tiles either side, LL 
		for (int i = x - 2; i < x + 3; i++) {
			for (int j = y - 2; j < y + 3; j++) {
				incrementSupplyAndDemand(i, j);
			}
		}

		//test output - display the supply rates
		/*System.out.println("supples:");
		for (int a=0; a<supplies.size(); a++) {
			CargoElementObject tempElement = (CargoElementObject)supplies.elementAt(a);
			System.out.println("type:" + tempElement.getType() + " rate:" + tempElement.getRate());
		}*/

		//test output
		System.out.println("all processin done, updating station supply values");

		//return the supplied cargo rates
		return supplies;
	}

	public DemandAtStation getDemand() {
		boolean[] demandboolean = new boolean[w.size(KEY.CARGO_TYPES)];
		for (int i = 0; i < w.size(KEY.CARGO_TYPES); i++) {
			if(demand[i] >= PREREQUISITE_FOR_DEMAND){
				demandboolean[i] = true;
				CargoType ct = (CargoType)w.get(KEY.CARGO_TYPES, i);
				System.out.println("Station demands "+ct.getDisplayName()); 
			}
		}
		return new DemandAtStation(demandboolean);
	}

	private void incrementSupplyAndDemand(int i, int j) {

		int tileTypeNumber = w.getTile(i, j).getTerrainTypeNumber();

		//test output - is there anything getting through???
		System.out.println("tileTypeNumber" + tileTypeNumber);

		TerrainType terrainType = (TerrainType) w.get(KEY.TERRAIN_TYPES, tileTypeNumber);

		//Calculate supply.

		Production[] production = terrainType.getProduction();

		//loop throught the production array and increment 
		//the supply rates for the station
		for (int m = 0; m < production.length; m++) {
			int type = production[m].getCargoType();
			int rate = production[m].getRate();

			//loop through supplies vector and increment the cargo values as required
			updateSupplyRate(type, rate);
		}

		//Now calculate demand.

		Consumption[] consumption = terrainType.getConsumption();
		for (int m = 0; m < consumption.length; m++) {
			int type = consumption[m].getCargoType();
			int prerequisite = consumption[m].getPrerequisite();
			//The prerequisite is the number tiles of this type that must 
			//be within the station radius before the station demands the cargo.			
			demand[type] += PREREQUISITE_FOR_DEMAND / prerequisite;
		}

		Conversion[] conversion = terrainType.getConversion();
		for (int m = 0; m < conversion.length; m++) {
			int type = conversion[m].getInput();
			//Only one tile that converts the cargo type is needed for the station to demand the cargo type.				
			demand[type] += PREREQUISITE_FOR_DEMAND;
		}
	}

	private void updateSupplyRate(int type, int rate) {

		//loop through supplies vector and increment the cargo values as required	
		for (int n = 0; n < supplies.size(); n++) {
			CargoElementObject tempElement = (CargoElementObject) supplies.elementAt(n);

			if (tempElement.getType() == type) {

				//cargo types are the same, so increment the rate in supply
				//with the rate.
				tempElement.setRate(tempElement.getRate() + rate);

				break; //no need to go through the rest if we've found a match
			}
		}
	}
}