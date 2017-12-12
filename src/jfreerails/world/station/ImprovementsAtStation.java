package jfreerails.world.station;

import jfreerails.type.EngineShop;
import jfreerails.type.MaintenanceShop;
import jfreerails.type.SwitchingYard;
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