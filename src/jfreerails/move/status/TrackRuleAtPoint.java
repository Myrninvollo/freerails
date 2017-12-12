package jfreerails.move.status;

final public class TrackRuleAtPoint {

	public static final TrackRuleAtPoint buildingTrackIsOk =
		new TrackRuleAtPoint(true, "Track can be built here");
	public static final TrackRuleAtPoint outOfBounds =
		new TrackRuleAtPoint(false, "Outside the region in which track can be built");
	public static final TrackRuleAtPoint notAllowedOnThisTerrain =
		new TrackRuleAtPoint(false, "This track type is not allowed here.");

	private boolean trackAllowed;

	private String reason;

	private TrackRuleAtPoint(boolean trackAllowed, String reason) {
		this.trackAllowed = trackAllowed;
		this.reason = reason;
	}
	public boolean trackIsAllowed() {
		return trackAllowed;
	}

}