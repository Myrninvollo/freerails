package jfreerails.world.station;

import jfreerails.world.type.EngineShop;
import jfreerails.world.type.MaintenanceShop;
import jfreerails.world.type.SwitchingYard;
final public class ImprovementsAtStation {
	public MaintenanceShop getMaintenanceShop() {
		return maintenanceShop;
	}

	public SwitchingYard getSwitchingYard() {
		return switchingYard;
	}

	public EngineShop getEngineShop() {
		return engineShop;
	}

	private final MaintenanceShop maintenanceShop;
	private final SwitchingYard switchingYard;
	private final EngineShop engineShop;

	public ImprovementsAtStation() {
		maintenanceShop = null;
		switchingYard = null;
		engineShop = null;
	}
}