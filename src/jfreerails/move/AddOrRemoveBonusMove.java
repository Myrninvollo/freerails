package jfreerails.move;

import jfreerails.misc.Money;
import jfreerails.type.CargoType;
import jfreerails.world.station.StationModelPrivate;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class AddOrRemoveBonusMove {

	private final StationModelPrivate pickupStation;

	private final StationModelPrivate deliverStation;

	private final CargoType cargo;

	private final Money initialReward;

	private final String reason;

	private final boolean cancel;

	public StationModelPrivate getPickupStation() {
		return pickupStation;
	}

	public StationModelPrivate getDeliverStation() {
		return deliverStation;
	}

	public CargoType getCargo() {
		return cargo;
	}

	public Money getInitialReward() {
		return initialReward;
	}

	public String getReason() {
		return reason;
	}

	public boolean getCancel() {
		return cancel;
	}
	public AddOrRemoveBonusMove(
		StationModelPrivate to,
		StationModelPrivate from,
		CargoType c,
		Money amount,
		String str, 
		boolean can) {
		deliverStation = to;
		pickupStation = from;
		cargo = c;
		initialReward = amount;
		reason = str;
		cancel=can;

	}
}