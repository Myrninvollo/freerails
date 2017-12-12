package jfreerails.world.station;

import jfreerails.world.cargo.CargoType;
import jfreerails.world.cargo.CargoBundle;
import jfreerails.world.cargo.CargoContainer;
public abstract class StationModelPrivate
	implements CargoContainer, StationModel {

	public SupplyAtStation stationCargoSupplyAndDemand;
	public StationModel stationModelPublic;
	private ImprovementsAtStation improvements;
	private CargoWaitingAtStation cargoWaiting;

	public SupplyAtStation getStationCargoSupplyAndDemand() {
		return stationCargoSupplyAndDemand;
	}

	public StationModel getStationModelPublic() {
		return stationModelPublic;
	}

	public abstract boolean canHold(CargoType type);

	public abstract CargoBundle getBundle(CargoType type);

	public abstract boolean addBundle(CargoBundle bundle);

	public abstract boolean removeBundle(CargoBundle bundle);

}