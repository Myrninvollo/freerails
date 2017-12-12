/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package experimental;

import java.awt.Point;

import jfreerails.world.track.PositionOnTrack;
import jfreerails.world.track.TrackSection;

/**
 * @version 	1.0
 * @author
 */
public class TrackSectionImpl1 implements TrackSection {

	PositionOnTrack start, end;
	int length, width, height;

	public TrackSectionImpl1(int x1, int y1, int d1, int x2, int y2, int d2) {
		start = new PositionOnTrack(x1, y1, 0, d1);
		end = new PositionOnTrack(x2, y2, 0, d2);
		width = x2 - x1;
		height = y2 - y1;

		int sumOfSquares = (width * width) + (height * height);
		length = (int) Math.sqrt((double) sumOfSquares);

	}

	/*
	 * @see TrackSection#getStart(Point)
	 */
	public PositionOnTrack getStart(Point p) {
		return start;
	}

	/*
	 * @see TrackSection#getEnd(Point)
	 */
	public PositionOnTrack getEnd(Point p) {
		return end;
	}

	/*
	 * @see TrackSection#getLocation(Point, int)
	 */
	public PositionOnTrack getLocation(Point p, int distance) {
		double x, y;
		double relativeDistance;

		x = width * distance / length;
		y = height * distance / length;
		x += start.getX();
		y += start.getY();

		return new PositionOnTrack((int) x, (int) y, 0, 0);
	}

	/*
	 * @see TrackSection#getLength()
	 */
	public int getLength() {
		return length;
	}

}