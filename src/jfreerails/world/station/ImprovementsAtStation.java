package jfreerails.world.station;

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