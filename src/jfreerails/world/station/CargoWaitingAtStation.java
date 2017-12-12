package jfreerails.world.station;

import jfreerails.world.common.FreerailsSerializable;


/** This class represents the cargo waiting at a station. */
public class CargoWaitingAtStation implements FreerailsSerializable {
    final int[] m_cargoWaiting;

    public CargoWaitingAtStation(int[] cargoWaiting) {
        m_cargoWaiting = (int[])cargoWaiting.clone();
    }

    public int getAmountWeighting(int cargoType) {
        return m_cargoWaiting[cargoType];
    }

    public boolean equals(Object o) {
        if (o instanceof CargoWaitingAtStation) {
            CargoWaitingAtStation test = (CargoWaitingAtStation)o;

            return this.m_cargoWaiting.equals(test.m_cargoWaiting);
        } else {
            return false;
        }
    }
}