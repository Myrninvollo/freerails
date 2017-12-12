package jfreerails.world.cargo;



final public class ConsumptionProcess {

	private final CargoType cargoType;
	private final SideEffect sideEffect;
	private final IndustryType industryType;

	public CargoType getCargoType() {
		return cargoType;
	}

	public SideEffect getSideEffect() {
		return sideEffect;
	}

	public IndustryType getIndustryType() {
		return industryType;
	}

	public ConsumptionProcess(CargoType c, SideEffect s, IndustryType i) {
		cargoType = c;
		sideEffect = s;
		industryType = i;

	}
}