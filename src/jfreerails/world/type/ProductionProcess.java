package jfreerails.world.type;

final public class ProductionProcess {

	private final CargoType cargoType;
	private final SideEffect sideEffect;

	public CargoType getCargoType() {
		return cargoType;
	}

	public SideEffect getSideEffect() {
		return sideEffect;
	}

	public ProductionProcess(CargoType c, SideEffect s) {
		cargoType = c;
		sideEffect = s;
	}

}