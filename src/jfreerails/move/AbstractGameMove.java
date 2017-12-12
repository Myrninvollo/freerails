package jfreerails.move;


import jfreerails.world.*;
import jfreerails.world.GameTime;

/**
 * Represents ...
 *
 * @see OtherClasses
 * @author lindsal
 */

public abstract class AbstractGameMove implements Move {

	private final int serial;

	private final GameTime timeStamp;

	public int getSerial() {
		return serial;
	}

	public GameTime getTimeStamp() {
		return timeStamp;
	}

	public AbstractGameMove() {
		serial = 0;
		timeStamp = new GameTime(0);
	}
}