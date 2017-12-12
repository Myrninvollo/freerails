/*
 * File:           Track_TilesHandlerImpl.java
 * Date:           21 January 2002  18:00
 *
 * @author  lindsal
 * @version generated by FFJ XML module
 */
package jfreerails;

import java.awt.Point;
import java.util.ArrayList;

import jfreerails.client.common.ImageSplitter;
import jfreerails.client.renderer.TrackPieceRendererList;
import jfreerails.world.top.KEY;
import jfreerails.world.top.World;
import jfreerails.world.track.LegalTrackPlacement;
import jfreerails.world.track.TrackRule;
import jfreerails.world.track.TrackRuleProperties;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class Track_TilesHandlerImpl
	implements Track_TilesHandler, TrackSetFactory {

	int maxConsequ;

	protected ArrayList ruleList;
	protected jfreerails.world.track.TrackRuleProperties trackRuleProperties;
	protected jfreerails
		.world
		.track
		.LegalTrackConfigurations legalTrackConfigurations;
	public static final boolean DEBUG = false;
	protected World w;
	protected ArrayList legalTemplates;
	protected java.util.HashSet terrainTypes;
	protected LegalTrackPlacement legalTrackPlacement;
	//  protected LegalTrackPlacement.PlacementRule placementRule;

	public void start_CanOnlyBuildOnTheseTerrainTypes(final Attributes meta)
		throws SAXException {

		terrainTypes = new java.util.HashSet();
	}

	public void end_CanOnlyBuildOnTheseTerrainTypes() throws SAXException {

		legalTrackPlacement =
			new LegalTrackPlacement(
				terrainTypes,
				LegalTrackPlacement.PlacementRule.ONLY_ON_THESE);
		terrainTypes = null;
	}

	public void start_ListOfTrackPieceTemplates(final Attributes meta)
		throws SAXException {
		legalTemplates = new ArrayList();
	}

	public void end_ListOfTrackPieceTemplates() throws SAXException {
		legalTrackConfigurations =
			new jfreerails.world.track.LegalTrackConfigurations(
				maxConsequ,
				legalTemplates);
		legalTemplates = null;
	}

	public void start_ListOfLegalRoutesAcrossNode(final Attributes meta)
		throws SAXException {
		if (DEBUG)
			System.err.println("start_ListOfLegalRoutesAcrossNode: " + meta);
	}

	public void end_ListOfLegalRoutesAcrossNode() throws SAXException {
		if (DEBUG)
			System.err.println("end_ListOfLegalRoutesAcrossNode()");
	}

	public void handle_LegalRouteAcrossNode(final Attributes meta)
		throws SAXException {
		if (DEBUG)
			System.err.println("handle_LegalRouteAcrossNode: " + meta);
	}

	public void start_CannotBuildOnTheseTerrainTypes(final Attributes meta)
		throws SAXException {
		terrainTypes = new java.util.HashSet();
	}

	public void end_CannotBuildOnTheseTerrainTypes() throws SAXException {
		legalTrackPlacement =
			new LegalTrackPlacement(
				terrainTypes,
				LegalTrackPlacement.PlacementRule.ANYWHERE_EXCEPT_ON_THESE);
		terrainTypes = null;
	}

	public void start_TrackType(final Attributes meta) throws SAXException {

		int rGBvalue;
		String rgbString = meta.getValue("RGBvalue");
		rGBvalue = (int) Integer.parseInt(rgbString, 16);
		/*
		 *  We need to change the format of the rgb value to the same one as used
		 *  by the the BufferedImage that stores the map.  See jfreerails.common.Map
		 */
		rGBvalue = new java.awt.Color(rGBvalue).getRGB();

		boolean isStation =
			Boolean.valueOf(meta.getValue("station")).booleanValue();

		boolean enableDoubleTrack =
			Boolean.valueOf(meta.getValue("doubleTrack")).booleanValue();
		String typeName = meta.getValue("type");
		int ruleNumber = ruleList.size();
		maxConsequ =
			(int) Integer.parseInt(meta.getValue("maxConsecuativePieces"));
		String stationRadiusString = meta.getValue("stationRadius");
		int stationRadius;
		if (null != stationRadiusString) {
			stationRadius = (int) Integer.parseInt(stationRadiusString);
		} else {
			stationRadius = 0;
		}
		String priceString = meta.getValue("price");
		int price = Integer.parseInt(priceString);
		
		String maintenanceString = meta.getValue("maintenance");
		int maintenance = Integer.parseInt(priceString);
		
		trackRuleProperties =
			new TrackRuleProperties(
				rGBvalue,
				enableDoubleTrack,
				typeName,
				ruleNumber,
				isStation,
				stationRadius,
				price,
				maintenance);

	}

	public void end_TrackType() throws SAXException {

		ruleList.add(
			(Object) (new jfreerails
				.world
				.track
				.TrackRuleImpl(
					trackRuleProperties,
					legalTrackConfigurations,
					legalTrackPlacement)));

		legalTrackConfigurations = null;
		trackRuleProperties = null;
		legalTrackPlacement = null;
	}

	public void handle_TerrainType(final Attributes meta) throws SAXException {
		terrainTypes.add(meta.getValue("name"));
	}

	public void start_Tiles(final Attributes meta) throws SAXException {
		if (DEBUG)
			System.err.println("start_Tiles: " + meta);
	}

	public void end_Tiles() throws SAXException {
		if (DEBUG)
			System.err.println("end_Tiles()");
	}

	public void start_TrackPieceTemplate(final Attributes meta)
		throws SAXException {
		legalTemplates.add(meta.getValue("trackTemplate"));
	}

	public void end_TrackPieceTemplate() throws SAXException {
		//do nothing.
	}

	public void start_TrackSet(final Attributes meta) throws SAXException {
		ruleList = new ArrayList();
	}

	public void end_TrackSet() throws SAXException {

	}

	public Track_TilesHandlerImpl(java.net.URL trackXmlUrl) {
		try {
			Track_TilesParser.parse(
				trackXmlUrl,
				this,
				new Track_TilesParsletImpl());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected Track_TilesHandlerImpl() {
	}

	public TrackPieceRendererList getTrackViewList(ImageSplitter trackImageSplitter) {
		throw new UnsupportedOperationException();
	}

	public Point getTrackPieceSize() {
		throw new UnsupportedOperationException();
	}

	public void addTrackRules(World w) {
		for (int i = 0; i < this.ruleList.size(); i++) {
			TrackRule r = (TrackRule) ruleList.get(i);
			w.add(KEY.TRACK_RULES, r);
		}
	}
}
