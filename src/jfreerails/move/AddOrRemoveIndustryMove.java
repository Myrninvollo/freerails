package jfreerails.move;

import java.awt.Point;

import jfreerails.type.IndustryType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

final public class AddOrRemoveIndustryMove {

	private final Point location;

	private final IndustryType industry;

	private final IndustryType oldType;

	public Point getLocation() {
		return location;
	}

	public IndustryType getIndustry() {
		return industry;
	}

	public IndustryType getOldType() {
		return oldType;
	}

	public AddOrRemoveIndustryMove(
		Point p,
		IndustryType newType,
		IndustryType oldType) {
		location = p;
		industry = newType;
		this.oldType = oldType;
	}
}