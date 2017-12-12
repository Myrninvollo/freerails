package jfreerails.world.station;

import jfreerails.world.cargo.CargoBundle;

public class CargoWaitingAtStation {

	private final CargoBundle[] m_cargoWaiting;

	public CargoBundle[] getCargoWaiting() {
		return m_cargoWaiting;
	}

	public CargoWaitingAtStation(CargoBundle[] cargoWaiting) {
		m_cargoWaiting = (CargoBundle[]) cargoWaiting.clone();
	}

}