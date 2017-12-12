package jfreerails.move;

import jfreerails.world.cargo.CargoType;

/**
 *
 *
 *
 * @author lindsal
 */

final public class ChangeSupplyAndDemandAtStationMove {

	private final CargoType[] newCargoType;
	private final CargoType[] oldcargoType;

	public CargoType[] getCargoTypesBefore() {
		return newCargoType;
	}
	public CargoType[] getCargoTypeAfter() {
		return oldcargoType;
	}
	public ChangeSupplyAndDemandAtStationMove(
		CargoType[] before,
		CargoType[] after) {
		newCargoType = after;
		oldcargoType = before;

	}

}