
/*
*  TrackSetFactory.java
*
*  Created on 20 May 2001, 21:05
*/
package jfreerails.factory;
import java.awt.Point;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashSet;

import jfreerails.client.trackview.TrackPieceView;
import jfreerails.client.trackview.TrackPieceViewList;
import jfreerails.common.exception.FreerailsException;
import jfreerails.constructorparam.TrackRuleImplConstructorParameters;
import jfreerails.lib.DOMLoader;
import jfreerails.lib.ImageSplitter;
import jfreerails.list.TrackRuleList;
import jfreerails.type.TrackRule;
import jfreerails.type.TrackRuleImpl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
*  This class provides methods to generate a set of track rules whose
*  properites are specified in an XML file.
*
*@author     Luke Lindsay.
*@created    09 October 2001
*@version    1.0
*/

public class TrackSetFactoryImpl
	extends java.lang.Object
	implements TrackSetFactory {

	private NodeList trackRulesNodeList;

	private Point trackTileSize;

	private NamedNodeMap trackRuleSetAttributes;

	/**
	*  Gets the trackPieceSize attribute of the TrackSetFactory object
	*
	*@return                         The trackPieceSize value
	*@exception  FreerailsException  Description of Exception
	*/

	public Point getTrackPieceSize() throws FreerailsException {
		if (trackTileSize == null) {
			throw new FreerailsException("Error: TrackSetFactory.getTrackPieceSize called before trackRulesize had been set");
		}
		return trackTileSize;
	}

	/**
	*  Creates new TrackSetFactory. It loads an XML file that defines the
	*  terrain types in the trackRule-set
	*
	*@param  xml_url  The URL of the XML file defining the track rules.
	*/

	public TrackSetFactoryImpl(java.net.URL xml_url) {
		Element trackRules;
		org.w3c.dom.Document document = jfreerails.lib.DOMLoader.get_dom(xml_url);
		trackRules = document.getDocumentElement();
		trackRules.normalize();
		NodeList trackRulesetNodeList = trackRules.getElementsByTagName("TrackSet");
		Node node_trackRule_set = trackRulesetNodeList.item(0);
		this.trackRulesNodeList = trackRules.getElementsByTagName("TrackType");
		this.trackRuleSetAttributes = node_trackRule_set.getAttributes();
	}

	/**
	*  Gets the trackRuleList attribute of the TrackSetFactory object
	*
	*@return                         The trackRuleList value
	*@exception  FreerailsException  Description of Exception
	*/

	public TrackRuleList getTrackRuleList() throws FreerailsException {
		TrackRule[] trackRuleList = new TrackRule[trackRulesNodeList.getLength()];
		for (int i = 0; i < trackRulesNodeList.getLength(); i++) {
			Node trackRuleNode = trackRulesNodeList.item(i);
			trackRuleList[i] = createTrackRule(trackRuleNode, i);
		}
		return new TrackRuleList(trackRuleList);
	}

	/**
	*  Gets the trackViewList attribute of the TrackSetFactory object
	*
	*@param  trackImageSplitter      Description of Parameter
	*@return                         The trackViewList value
	*@exception  FreerailsException  Description of Exception
	*/

	public jfreerails.client.trackview.TrackPieceViewList getTrackViewList(
		ImageSplitter trackImageSplitter)
		throws FreerailsException {
		TrackPieceView[] trackPieceViewList =
			new TrackPieceView[trackRulesNodeList.getLength()];
		try {

			//Get values, then setup the ImageSplitter.
			String temp_number =
				trackRuleSetAttributes.getNamedItem("Height").getNodeValue();
			int height = NumberFormat.getInstance().parse(temp_number).intValue();
			temp_number = trackRuleSetAttributes.getNamedItem("Width").getNodeValue();
			int width = NumberFormat.getInstance().parse(temp_number).intValue();
			temp_number = trackRuleSetAttributes.getNamedItem("X").getNodeValue();
			int x = NumberFormat.getInstance().parse(temp_number).intValue();
			temp_number = trackRuleSetAttributes.getNamedItem("Y").getNodeValue();
			int y = NumberFormat.getInstance().parse(temp_number).intValue();
			trackImageSplitter.setTileGrid(x, y, width, height);
			this.trackTileSize = new java.awt.Point(width, height);
		} catch (java.text.ParseException pe) {
			throw new FreerailsException(
				"ParseException while parsing the xml"
					+ "file specifying the track tile sizes.  Check that the \"Tile_set\" attributes: \"Height\", \"Width\", \"X\", and \"Y\" are integers");
		}
		for (int i = 0; i < trackPieceViewList.length; i++) {
			Node trackViewNode = trackRulesNodeList.item(i);
			trackPieceViewList[i] = createTrackPieceView(trackViewNode, trackImageSplitter);
		}
		return new jfreerails.client.trackview.TrackPieceViewList(trackPieceViewList);
	}

	/**
	*  Gets the terrainTypesList attribute of the TrackSetFactory object
	*
	*@param  terrainTypesNode  Description of Parameter
	*@return                   The terrainTypesList value
	*/

	private HashSet getTerrainTypesList(Node terrainTypesNode) {
		Element terrainTypeListElement = (org.w3c.dom.Element) terrainTypesNode;
		NodeList terrainTypeNodeList =
			terrainTypeListElement.getElementsByTagName("TerrainType");
		HashSet terrainTypes = new HashSet();
		for (int i = 0; i < terrainTypeNodeList.getLength(); i++) {
			NamedNodeMap terrainTypeAttributes =
				terrainTypeNodeList.item(i).getAttributes();
			String nodeValue = terrainTypeAttributes.getNamedItem("name").getNodeValue();
			terrainTypes.add(nodeValue);
		}
		return terrainTypes;
	}

	/**
	*  Description of the Method
	*
	*@param  trackTypeNode           Description of Parameter
	*@param  trackImageSplitter      Description of Parameter
	*@return                         Description of the Returned Value
	*@exception  FreerailsException  Description of Exception
	*/

	private TrackPieceView createTrackPieceView(
		Node trackTypeNode,
		ImageSplitter trackImageSplitter)
		throws FreerailsException {
		String nodeValue;
		Element trackTypeElement = (org.w3c.dom.Element) trackTypeNode;
		NodeList trackPieceTemplateNodeList =
			trackTypeElement.getElementsByTagName("TrackPieceTemplate");
		NamedNodeMap trackTypeAttributes = trackTypeNode.getAttributes();
		nodeValue = trackTypeAttributes.getNamedItem("X").getNodeValue();
		int x = (int) Integer.parseInt(nodeValue);
		nodeValue = trackTypeAttributes.getNamedItem("Y").getNodeValue();
		int y = (int) Integer.parseInt(nodeValue);
		trackImageSplitter.setSubGridOffset(x, y);
		int[] trackPieceTemplateArray = new int[trackPieceTemplateNodeList.getLength()];
		for (int i = 0; i < trackPieceTemplateArray.length; i++) {
			NamedNodeMap trackPieceAttributes =
				trackPieceTemplateNodeList.item(i).getAttributes();
			nodeValue = trackPieceAttributes.getNamedItem("trackTemplate").getNodeValue();

			//Dirty hack - so that result is as expected by earlier written code.
			StringBuffer strb = new StringBuffer(nodeValue);
			strb = strb.reverse();
			nodeValue = strb.toString();

			//End of dirty hack
			trackPieceTemplateArray[i] = (int) Integer.parseInt(nodeValue, 2);
		}
		if (null == trackImageSplitter) {
			throw new FreerailsException("null==trackImageSplitter, cannot get track icons");
		}
		return new jfreerails.client.trackview.TrackPieceViewImpl(
			trackPieceTemplateArray,
			trackImageSplitter);
	}

	/**
	*  Description of the Method
	*
	*@param  trackTypeNode           Description of Parameter
	*@param  ruleNumber              Description of Parameter
	*@return                         Description of the Returned Value
	*@exception  FreerailsException  Description of Exception
	*/

	private TrackRule createTrackRule(Node trackTypeNode, int ruleNumber)
		throws FreerailsException {

		TrackRuleImplConstructorParameters trackRuleParams =
			new TrackRuleImplConstructorParameters();

		trackRuleParams.ruleNumber=ruleNumber;

		Element trackTypeElement = (org.w3c.dom.Element) trackTypeNode;
		NodeList trackPieceTemplateNodeList =
			trackTypeElement.getElementsByTagName("TrackPieceTemplate");

		trackRuleParams.legalTrackTemplates = new String[trackPieceTemplateNodeList.getLength()];
		trackRuleParams.legalRoutesAcrossNodeTemplates =
			new String[trackPieceTemplateNodeList.getLength()][];
		for (int i = 0; i < trackRuleParams.legalTrackTemplates.length; i++) {
			NamedNodeMap trackPieceAttributes =
				trackPieceTemplateNodeList.item(i).getAttributes();
			trackRuleParams.legalTrackTemplates[i] =
				trackPieceAttributes.getNamedItem("trackTemplate").getNodeValue();
			//trackPieceTemplateArray[i] = (int) Integer.parseInt(nodeValue, 2);
			trackRuleParams.legalRoutesAcrossNodeTemplates[i] =
				getLegalRoutesAcrossNode(trackPieceTemplateNodeList.item(i));
		}
		
		NamedNodeMap trackTypeAttributes = trackTypeNode.getAttributes();
		String attributeValue;
		/*
		*  A signal tower?
		*/
		attributeValue = trackTypeAttributes.getNamedItem("signalTower").getNodeValue();
		trackRuleParams.signalTower = Boolean.getBoolean(attributeValue);

		/*
		*  Is double track enabled?
		*/
		attributeValue = trackTypeAttributes.getNamedItem("doubleTrack").getNodeValue();
		trackRuleParams.enableDoubleTrack = Boolean.getBoolean(attributeValue);

		/*
		*  A station?
		*/
		attributeValue = trackTypeAttributes.getNamedItem("station").getNodeValue();
		trackRuleParams.station = Boolean.getBoolean(attributeValue);

		/*
		*  RGB value for this track type
		*/
		attributeValue = trackTypeAttributes.getNamedItem("RGBvalue").getNodeValue();
		int rgb = (int) Integer.parseInt(attributeValue, 16);

		/*
		*  We need to change the format of the rgb value to the same one as used
		*  by the the BufferedImage that stores the map.  See jfreerails.common.Map
		*/
		trackRuleParams.rGBvalue = new java.awt.Color(rgb).getRGB();

		/*
		*  The maxConsecuativePieces
		*/
		attributeValue =
			trackTypeAttributes.getNamedItem("maxConsecuativePieces").getNodeValue();
		trackRuleParams.maximumConsecutivePieces = (int) Integer.parseInt(attributeValue);

		/*
		*  The track type name
		*/
		trackRuleParams.typeName = trackTypeAttributes.getNamedItem("type").getNodeValue();
		NodeList notTheseNodeList =
			trackTypeElement.getElementsByTagName("CannotBuildOnTheseTerrainTypes");
		if (notTheseNodeList.getLength() > 0) {
			Node notTheseNode = notTheseNodeList.item(0);
			trackRuleParams.cannotBuildOnTheseTerrainTypes = getTerrainTypesList(notTheseNode);
		} else {
			NodeList onlyTheseNodeList =
				trackTypeElement.getElementsByTagName("CanOnlyBuildOnTheseTerrainTypes");
			if (onlyTheseNodeList.getLength() > 0) {
				Node onlyTheseNode = onlyTheseNodeList.item(0);
				trackRuleParams.canOnlyBuildOnTheseTerrainTypes = getTerrainTypesList(onlyTheseNode);
			}
		}
		TrackRule trackRule =
			new TrackRuleImpl(trackRuleParams);
				
		return trackRule;
	}

	/**
	*  Gets the trackAttributes attribute of the TrackSetFactory object
	*
	*@param  trackTypeNode           Description of Parameter
	*@param  trackRule               Description of Parameter
	*@exception  FreerailsException  Description of Exception
	*/

	private void getTrackAttributes(Node trackTypeNode, TrackRule trackRule)
		throws FreerailsException {

	}

	/**
	*  Gets the legalRoutesAcrossNode attribute of the TrackSetFactory object
	*
	*@param  trackPieceTemplate  Description of Parameter
	*@return                     The legalRoutesAcrossNode value
	*/

	private String[] getLegalRoutesAcrossNode(Node trackPieceTemplate) {
		Element trackPieceTemplateElement = (org.w3c.dom.Element) trackPieceTemplate;
		NodeList legalRoutesAcrossNodeNodeList =
			trackPieceTemplateElement.getElementsByTagName("LegalRouteAcrossNode");
		String[] legalRoutesAcrossNode =
			new String[legalRoutesAcrossNodeNodeList.getLength()];
		for (int i = 0; i < legalRoutesAcrossNode.length; i++) {
			NamedNodeMap legalRouteAttributes =
				legalRoutesAcrossNodeNodeList.item(i).getAttributes();
			legalRoutesAcrossNode[i] =
				legalRouteAttributes.getNamedItem("RouteTemplate").getNodeValue();
			
		}
		return legalRoutesAcrossNode;
	}
}