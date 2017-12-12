package jfreerails.world.track;

final public class SignalTowerPublic implements SignalTower {


	private final Signal signal;

	/*
	public TrackNode getStandardTrackNode() {
		return trackNode;
	}
	*/

	public Signal getSignal() {
		return signal;
	}
	public SignalTowerPublic() {
		//trackNode = null;
		signal = null;
	}
}