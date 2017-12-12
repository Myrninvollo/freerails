package jfreerails.move;

/**
 *
 *
 *
 * @author lindsal
 */

final public class ChangeStationNameMove {

	private final String oldName;

	private final String newName;

	public String getOldName() {
		return oldName;
	}

	public String getNewName() {
		return newName;
	}
	public ChangeStationNameMove(String before, String after) {
		oldName = before;
		newName = after;
	}
}