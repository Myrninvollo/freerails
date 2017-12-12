package jfreerails.world.cargo;

import java.util.Iterator;

import jfreerails.type.CargoType;

public interface CargoContainer {

	boolean canHold(CargoType type);

	Iterator getBundleIterator();

	CargoBundle getBundle(CargoType type);

	boolean addBundle(CargoBundle bundle);

	boolean removeBundle(CargoBundle bundle);
}