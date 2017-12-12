package jfreerails.world.financial;

import jfreerails.world.GameTime;
final public class CompanyDetails {
	public GameTime getEstablished() {
		return established;
	}

	public String getRRName() {
		return null;
	}

	private final String rRName;
	private final GameTime established;

	protected CompanyDetails(String name, GameTime time) {
		rRName = name;
		established = time;
	}
}