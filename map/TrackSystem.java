package jfreerails.map;
import java.awt.Point;

import jfreerails.element.TrackNode;
import jfreerails.enum.TrackRuleAtPoint;
import jfreerails.list.TrackRuleList;
import jfreerails.type.TrackRule;

/**
 *  Description of the Interface
 *
 *@author     Luke Lindsay
 *@created    07 November 2001
 */
public interface TrackSystem {
	/**
	 *  Gets the trackNode attribute of the TrM object
	 *
	 *@param  point  Description of the Parameter
	 *@return        The trackNode value
	 */
	public TrackNode getTrackNode(Point point);

	/**
	 *  Gets the trackTypeNumber attribute of the TrM object
	 *
	 *@param  point  Description of the Parameter
	 *@return        The trackTypeNumber value
	 */
	public int getTrackTypeNumber(Point point);

	/**
	 *  Description of the Method
	 *
	 *@param  point                   Description of the Parameter
	
	 */
	public void removeTrackNode(Point point);

	/**
	 *  Adds a feature to the TrackNode attribute of the TrM object
	 *
	 *@param  point                   The feature to be added to the TrackNode
	 *      attribute
	 *@param  trackNode               The feature to be added to the TrackNode
	 *      attribute
	 */
	public void addTrackNode(Point point, TrackNode trackNode);

	/**
	 *  Gets the trackGraphicNumber attribute of the TrM object
	 *
	 *@param  point  Description of the Parameter
	 *@return        The trackGraphicNumber value
	 */
	public int getTrackGraphicNumber(Point point);

	/**
	 *  Gets the height attribute of the TrM object
	 *
	 *@return    The height value
	 */
	public int getHeight();

	/**
	 *  Description of the Method
	 *
	 *@param  point  Description of the Parameter
	 *@return        Description of the Return Value
	 */
	public int toggleDoubleTrack(Point point);
	
	public TrackRuleList getTrackRuleList();
	
	//public TrackList getTrackList();
	
	public String getTerrainType(Point p);
	
	public boolean contains(TrackNode trackNode);
	
	public boolean boundsContain(Point location);
		

	public TrackRuleAtPoint trackIsAllowed(Point location, TrackRule type);

}