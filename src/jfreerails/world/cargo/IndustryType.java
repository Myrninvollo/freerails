package jfreerails.world.cargo;

import jfreerails.world.misc.Money;

final public class IndustryType {

	private final Money costToBuild;

	private final TransformationProcess transformationProcess;
	private final ProductionProcess productionProcess;
	private final ConsumptionProcess consumptionProcess;

	public Money getCostToBuild() {
		return costToBuild;
	}

	public TransformationProcess getTransformationProcess() {
		return transformationProcess;
	}

	public ProductionProcess getProductionProcess() {
		return productionProcess;
	}

	public ConsumptionProcess getConsumptionProcess() {
		return consumptionProcess;
	}
	public IndustryType(
		Money price,
		TransformationProcess t,
		ProductionProcess p,
		ConsumptionProcess c) {
		costToBuild = price;
		transformationProcess = t;
		productionProcess = p;
		consumptionProcess = c;
	}
}