package jfreerails.map;
import java.awt.Point;
import java.awt.Rectangle;

import jfreerails.element.TrackNode;
import jfreerails.enum.TrackRuleAtPoint;
import jfreerails.list.TrackList;
import jfreerails.list.TrackRuleList;
import jfreerails.type.TrackRule;

/**
 *  This class encapsulates the track-map. It is composed of a 2D array of
 *  tracknodes and provides methods to acesss individual nodes. Tiles where no
 *  track has been build are represented by a null referencepublic class TrackMapImpl extends java.lang.Object07 November 2001
 */
public class TrackSystemImpl extends java.lang.Object implements TrackSystem {

	private Rectangle trackNodeMapRect;

	private TrackNode[][] trackNodeMap;

	private TrackRuleList trackRuleList;

	private TerrainMap terrainMap;

	private TrackList trackList;

	/**
	 *  Gets the track node at the specified point.
	 *
	 *@param  point  Description of Parameter
	 *@return        The trackNode value
	 */

	public TrackNode getTrackNode(Point point) {
		if (trackNodeMapRect.contains(point)) {
			TrackNode trackNode = trackNodeMap[point.x][point.y];
			return trackNode;
		} else {

			System.out.println("Tried to get track node from a point outside the map");
			return null;
		}
	}

	/**
	 *  Gets the track node at the specified point.
	 *
	 *@param  x
	 *@param  y
	 *@return    The track node. (Null represents no track.)
	 */
	/*    public TrackNode getTrackNode(int x, int y) {
	
	        TrackNode trackNode = trackNodeMap[x][y];
	        return trackNode;
	    }
	*/

	/**
	 *  Gets the trackTypeNumber attribute of the TrackMap object
	 *
	 *@param  point  Description of Parameter
	 *@return        The trackTypeNumber value
	 */

	public int getTrackTypeNumber(Point point) {
		TrackNode trackNode = getTrackNode(point);
		if (trackNode == null) {
			return 0;
		} else {
			return trackNode.getTrackTypeNumber();
		}
	}

	/**
	 *  Description of the Method
	 *
	 *@param  point                   Description of Parameter
	
	 */

	public void removeTrackNode(Point point) {
		TrackNode trackNode = trackNodeMap[point.x][point.y];
		if (trackNode != null) {
			trackList.removeTrackNode(trackNode);
			trackNodeMap[point.x][point.y] = null;
		} else {
			throw new java.lang.IllegalArgumentException(
				"Tried to remove a track node at"
					+ point.x
					+ ", "
					+ point.x
					+ " but there isn't one there!");
		}
	}

	/**
	 *  Adds a feature to the TrackNode attribute of the TrackMap object
	 *
	 *@param  point                   The feature to be added to the TrackNode
	 *      attribute
	 *@param  trackNode               The feature to be added to the TrackNode
	 *      attribute
	
	 */

	public void addTrackNode(Point point, TrackNode trackNode) {
		if (trackNodeMap[point.x][point.y] == null) {
			if (trackList.contains(trackNode)) {
				throw new IllegalArgumentException("Tried to add a track node that is already part of the track system.");
			}
			trackNodeMap[point.x][point.y] = trackNode;
			trackList.addTrackNode(trackNode);
		} else {
			throw new java.lang.IllegalArgumentException(
				"Tried to add a track node at"
					+ point.x
					+ ", "
					+ point.x
					+ " but there is already one there");
		}
	}

	/**
	 *  Gets the trackGraphicNumber attribute of the TrackMap object
	 *
	 *@param  point  Description of Parameter
	 *@return        The trackGraphicNumber value
	 */

	public int getTrackGraphicNumber(Point point) {
		TrackNode trackNode = getTrackNode(point);
		if (trackNode == null) {
			return 0;
		} else {
			return trackNode.getTrackGraphicNumber();
		}
	}

	/**
	 *  Gets the height attribute of the TrackMap object
	 *
	 *@return    The height value
	 */

	public int getHeight() {
		return trackNodeMap[0].length;
	}

	/**
	 *  Description of the Method
	 *
	 *@param  point  Description of Parameter
	 *@return        Description of the Returned Value
	 */

	public int toggleDoubleTrack(Point point) {

		//TODO add code
		return 0;
	}

	/**
	 *  Gets the width attribute of the TrackMap object
	 *
	 *@return    The width value
	 */

	public int getWidth() {
		return trackNodeMap.length;
	}

	/**
	 *  Creates new TrackMap
	 *
	 *@param  x  Description of Parameter
	 *@param  y  Description of Parameter
	 */

	public TrackSystemImpl(TrackRuleList trl, TerrainMap terrainMap) {
		if (null == trl || null == terrainMap) {
			throw new NullPointerException("Null pointer passed to constructor:public TrackSystemImpl(TrackRuleList trl, TerrainMap terrainMap)");
		}
		this.trackList = new TrackList();
		this.trackRuleList = trl;
		this.terrainMap = terrainMap;

		trackNodeMap = new TrackNode[terrainMap.getWidth()][terrainMap.getHeight()];
		trackNodeMapRect =
			new java.awt.Rectangle(0, 0, terrainMap.getWidth(), terrainMap.getHeight());
	}

	public TrackRuleList getTrackRuleList() {
		return trackRuleList;
	}

	//	public TrackList getTrackList() {
	//		return trackList;
	//
	//	}
	public String getTerrainType(Point p) {
		return terrainMap.getTerrainTypeName(p.x, p.y);
	}
	public boolean contains(TrackNode trackNode) {
		return trackList.contains(trackNode);
	}
	public TrackRuleAtPoint trackIsAllowed(Point location, TrackRule type) {
		if (!terrainMap.contains(location)) {
			return TrackRuleAtPoint.outOfBounds;
		}
		if (!type
			.canBuildOnThisTerrainType(
				terrainMap.getTerrainTypeName(location.x, location.y))) {
			return TrackRuleAtPoint.notAllowedOnThisTerrain;
		} else {
			return TrackRuleAtPoint.buildingTrackIsOk;
		}

	}
	public boolean boundsContain(Point location){
		return terrainMap.contains(location);
	}

}