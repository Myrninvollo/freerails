
/*
* TrackNode.java
*
* Created on 10 July 2001, 12:47
*/
package jfreerails.element;
import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;
import jfreerails.list.TrackList;
import jfreerails.misc.OneTileMoveVector;
import jfreerails.type.TrackRule;

/** This class encapsulates the type and configuation
* of the track on a square.  For every square on
* which track is layed, an object of this class is
* created.
* @author Luke Lindsay
* @version 0.1
*/

public class TrackNode extends java.lang.Object {

	public Vector uses = new Vector();
	//TODO reconsider the organsiation of track sections.
	//public HashMap trackSections=new HashMap();
	public TrackList trackList;
	public Vector getUses() {
		return uses;
	}
	public void addUses(TrackRule trackRule) {
		if (!this.uses.contains(trackRule)) {
			this.uses.addElement(trackRule);
			trackRule.setTrackRule(this);
		}
	}
	public void removeUses(TrackRule trackRule) {
		if (this.uses.removeElement(trackRule)) {
			trackRule.setTrackRule(null);
		}
	}

	public TrackList getTrackList() {
		return trackList;
	}
	public void setTrackList(TrackList trackList) {
		if (this.trackList != trackList) {
			if (this.trackList != null)
				this.trackList.removeTrackNode(this);
			this.trackList = trackList;
			if (trackList != null)
				trackList.addTrackNode(this);
		}
	}

	///////////////////////////////////////
	// operations

	/**
	 * Does ...
	 * 
	 * @return A Company with ...
	 */

	public Company getOwner() {
		return null;
	}
	/**
	 * Does ...
	 * 
	 * @return A Iterator with ...
	 */

	public Iterator getTrackSectionsIterator() {
		return null;
	}

	private java.awt.Point position;

	private TrackRule trackType;

	private TrackSection[][] railsArrangement = new TrackSection[3][3];

	public boolean isOrphaned() {
		final int orphanedTrackNode = (1 << 4);
		if (this.getTrackGraphicNumber() == orphanedTrackNode) {
			return true;
		} else {
			return false;
		}
	}
	public int getNumberOfTrackSections() {
		int n = 0;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if (1 != x || 1 != y) {

					if (null != railsArrangement[x][y]) {
						n++;
					}
				}
			}
		}
		return n;

	}

	/** Creates new TrackNode */

	public TrackNode(java.awt.Point position, TrackRule trackRule) {
		this.position = position;
		this.trackType = trackRule;
	}

	public TrackRule getTrackRule() {
		return this.trackType;
	}

	public boolean addingTrackSectionIsLegal(
		jfreerails.misc.OneTileMoveVector rail,
		TrackRule trackRule) {
		int trackGraphicNumber = this.getTrackGraphicNumber();
		trackGraphicNumber =
			trackGraphicNumber | (1 << (3 * (1 + rail.getY()) + (1 + rail.getX())));
		trackGraphicNumber = trackGraphicNumber | (1 << 4); //The centre square!
		return trackRule.testTrackPieceLegality(trackGraphicNumber);
	}
	public boolean addingTrackSectionIsLegal(
		jfreerails.misc.OneTileMoveVector rail) {
		return this.addingTrackSectionIsLegal(rail, this.trackType);
	}

	//This value determines which track icon to display.

	public int getTrackGraphicNumber() {
		return getTrackGraphicNumber(railsArrangement);
	}

	public int getTrackTypeNumber() {
		return this.trackType.getRuleNumber();
	}

	public java.awt.Point getPosition() {
		return position;
	}

	/** Returns a 9-bit value specifying  the track configuration, and
	* hence the appropriate icon, for the track at this node.  E.g.
	* the binary representation of a vertical straight would be:
	* 010
	* 010
	* 010 i.e. 010010010
	* @param railsList The rail list that is used ot generate the track
	* graphic number.
	* @return The track graphic number.
	*/

	public static int getTrackGraphicNumber(TrackSection[][] railsList) {
		int trackGraphicNumber = 0;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if (1 == x && 1 == y) {
					trackGraphicNumber = trackGraphicNumber | (1 << (3 * y + x));
				} else {
					if (null != railsList[x][y]) {
						trackGraphicNumber = trackGraphicNumber | (1 << (3 * y + x));
					}
				}
			}
		}
		return trackGraphicNumber;
	}

	public boolean hasTrackSection(jfreerails.misc.OneTileMoveVector rail) {
		if (null == railsArrangement[1 + rail.getX()][1 + rail.getY()]) {
			return false;
		} else {
			return true;
		}
	}

	public TrackSection getTrackSection(OneTileMoveVector rail) {
		return railsArrangement[1 + rail.getX()][1 + rail.getY()];

	}

	public boolean upgrade(TrackRule newTrackRule) {
		int trackGraphicNumber = this.getTrackGraphicNumber();
		if (newTrackRule.testTrackPieceLegality(trackGraphicNumber)) {
			this.trackType = newTrackRule;
			return true;
		} else {
			return false;
		}
	}

	public void removeTrackSection(jfreerails.misc.OneTileMoveVector direction) {
		railsArrangement[1 + direction.getX()][1 + direction.getY()] = null;
	}

	public void addTrackSection(
		OneTileMoveVector rail,
		TrackSection trackSection) {
		//railsArrangement[ 1 ][ 1 ] = true; //The central piece.
		railsArrangement[1 + rail.getX()][1 + rail.getY()] = trackSection;
	}
	public int getRGB(){
		return 0;
	}

	public TrackNode() {
	}
}