package jfreerails.move;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class ChangeTrainName {

	private final String oldName;

	private final String newName;

	public String getOldName() {
		return oldName;
	}

	public String getNewName() {
		return newName;
	}

	public ChangeTrainName(String before, String after) {
		oldName = before;
		newName = after;
	}
}