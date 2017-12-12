package jfreerails.world.financial;

import jfreerails.world.GameElementsList;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class BondList {

	public Bond bond;
	public GameElementsList gameElementsList;

	public Bond getBond() {
		return bond;
	}

	public GameElementsList getGameElementsList() {
		return gameElementsList;
	}
	public void setGameElementsList(GameElementsList gameElementsList) {
		if (this.gameElementsList != gameElementsList) {
			this.gameElementsList = gameElementsList;
			if (gameElementsList != null)
				gameElementsList.setBondList(this);
		}
	}

}