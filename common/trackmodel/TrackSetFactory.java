
/*
* TrackSetFactory.java
*
* Created on 20 May 2001, 21:05
*/
package jfreerails.common.trackmodel;
import jfreerails.common.exception.FreerailsException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import java.text.NumberFormat;
import jfreerails.client.trackview.TrackPieceView;
import java.util.HashSet;

/** This class provides methods to generate a set of track rules
* whose properites are specified in an XML file.
*
* @author Luke Lindsay.
* @version 1.0
* 
*/


public class TrackSetFactory extends java.lang.Object {

    private java.awt.Point trackTileSize;

    private NamedNodeMap trackRuleSetAttributes;

    private NodeList trackRulesNodeList;
    
    public TrackRuleList getTrackRuleList() throws FreerailsException {
        TrackRule[]  trackRuleList = new TrackRule[ trackRulesNodeList.getLength() ];
        for( int  i = 0;i < trackRulesNodeList.getLength();i++ ) {
            Node  trackRuleNode = trackRulesNodeList.item( i );
            trackRuleList[ i ] = createTrackRule( trackRuleNode, i );
            getTrackAttributes( trackRuleNode, trackRuleList[ i ] );
        }
        return new TrackRuleList( trackRuleList );
    }
    
    public java.awt.Point getTrackPieceSize() throws FreerailsException {
        if( trackTileSize == null ) {
            throw new FreerailsException( "Error: TrackSetFactory.getTrackPieceSize called before trackRulesize had been set" );
        }
        return trackTileSize;
    }
    
    /** Creates new TrackSetFactory.  It loads an XML file that defines the terrain
    * types in the trackRule-set
    * @param xml_url The URL of the XML file defining the track rules.
    */
    
    public TrackSetFactory( java.net.URL xml_url ) {
        Element  trackRules;
        org.w3c.dom.Document  document = jfreerails.lib.DOMLoader.get_dom( xml_url );
        trackRules = document.getDocumentElement();
        trackRules.normalize();
        NodeList  trackRulesetNodeList = trackRules.getElementsByTagName( "TrackSet" );
        Node  node_trackRule_set = trackRulesetNodeList.item( 0 );
        this.trackRulesNodeList = trackRules.getElementsByTagName( "TrackType" );
        this.trackRuleSetAttributes = node_trackRule_set.getAttributes();
    }
    
    public jfreerails.client.trackview.TrackPieceViewList getTrackViewList( jfreerails.lib.ImageSplitter trackImageSplitter ) throws FreerailsException {
        TrackPieceView[]  trackPieceViewList = new TrackPieceView[ trackRulesNodeList.getLength() ];
        try {
            
            //Get values, then setup the ImageSplitter.
            String  temp_number = trackRuleSetAttributes.getNamedItem( "Height" ).getNodeValue();
            int  height = NumberFormat.getInstance().parse( temp_number ).intValue();
            temp_number = trackRuleSetAttributes.getNamedItem( "Width" ).getNodeValue();
            int  width = NumberFormat.getInstance().parse( temp_number ).intValue();
            temp_number = trackRuleSetAttributes.getNamedItem( "X" ).getNodeValue();
            int  x = NumberFormat.getInstance().parse( temp_number ).intValue();
            temp_number = trackRuleSetAttributes.getNamedItem( "Y" ).getNodeValue();
            int  y = NumberFormat.getInstance().parse( temp_number ).intValue();
            trackImageSplitter.setTileGrid( x, y, width, height );
            this.trackTileSize = new java.awt.Point( width, height );
        }
        catch( java.text.ParseException pe ) {
            throw new FreerailsException( "ParseException while parsing the xml" + "file specifying the track tile sizes.  Check that the \"Tile_set\" attributes: \"Height\", \"Width\", \"X\", and \"Y\" are integers" );
        }
        for( int  i = 0;i < trackPieceViewList.length;i++ ) {
            Node  trackViewNode = trackRulesNodeList.item( i );
            trackPieceViewList[ i ] = createTrackPieceView( trackViewNode, trackImageSplitter );
        }
        return new jfreerails.client.trackview.TrackPieceViewList( trackPieceViewList );
    }
    
    private TrackPieceView createTrackPieceView( Node trackTypeNode, jfreerails.lib.ImageSplitter trackImageSplitter ) throws FreerailsException {
        String  nodeValue;
        Element  trackTypeElement = (org.w3c.dom.Element)trackTypeNode;
        NodeList  trackPieceTemplateNodeList = trackTypeElement.getElementsByTagName( "TrackPieceTemplate" );
        NamedNodeMap  trackTypeAttributes = trackTypeNode.getAttributes();
        nodeValue = trackTypeAttributes.getNamedItem( "X" ).getNodeValue();
        int  x = (int)Integer.parseInt( nodeValue );
        nodeValue = trackTypeAttributes.getNamedItem( "Y" ).getNodeValue();
        int  y = (int)Integer.parseInt( nodeValue );
        trackImageSplitter.setSubGridOffset( x, y );
        int[]  trackPieceTemplateArray = new int[ trackPieceTemplateNodeList.getLength() ];
        for( int  i = 0;i < trackPieceTemplateArray.length;i++ ) {
            NamedNodeMap  trackPieceAttributes = trackPieceTemplateNodeList.item( i ).getAttributes();
            nodeValue = trackPieceAttributes.getNamedItem( "trackTemplate" ).getNodeValue();
            
            //Dirty hack - so that result is as expected by earlier written code.
            StringBuffer  strb = new StringBuffer( nodeValue );
            strb = strb.reverse();
            nodeValue = strb.toString(); //End of dirty hack
            trackPieceTemplateArray[ i ] = (int)Integer.parseInt( nodeValue, 2 );
        }
        if( null == trackImageSplitter ) {
            throw new FreerailsException( "null==trackImageSplitter, cannot get track icons" );
        }
        return new TrackPieceView( trackPieceTemplateArray, trackImageSplitter );
    }
    
    private void getTrackAttributes( Node trackTypeNode, TrackRule trackRule ) throws FreerailsException {
        String  attributeValue;
        NamedNodeMap  trackTypeAttributes = trackTypeNode.getAttributes();
        attributeValue = trackTypeAttributes.getNamedItem( "signalTower" ).getNodeValue();
        trackRule.setSignalTower( Boolean.getBoolean( attributeValue ) );
        attributeValue = trackTypeAttributes.getNamedItem( "doubleTrack" ).getNodeValue();
        trackRule.setEnableDoubleTrack( Boolean.getBoolean( attributeValue ) );
        attributeValue = trackTypeAttributes.getNamedItem( "station" ).getNodeValue();
        trackRule.setStation( Boolean.getBoolean( attributeValue ) );
        attributeValue = trackTypeAttributes.getNamedItem( "RGBvalue" ).getNodeValue();
        int  rgb = (int)Integer.parseInt( attributeValue, 16 );
        
        /* We need to change the format of the rgb value to the same one as used 
        by the the BufferedImage that stores the map.  See jfreerails.common.Map
        */
        rgb = new java.awt.Color( rgb ).getRGB();
        trackRule.setRGBvalue( rgb );
        attributeValue = trackTypeAttributes.getNamedItem( "maxConsecuativePieces" ).getNodeValue();
        int  maximumConsecutivePieces = (int)Integer.parseInt( attributeValue );
        trackRule.setMaximumConsecutivePieces( maximumConsecutivePieces );
        attributeValue = trackTypeAttributes.getNamedItem( "type" ).getNodeValue();
        trackRule.setTypeName( attributeValue );
        Element  trackTypeElement = (org.w3c.dom.Element)trackTypeNode;
        NodeList  notTheseNodeList = trackTypeElement.getElementsByTagName( "CannotBuildOnTheseTerrainTypes" );
        if( notTheseNodeList.getLength() > 0 ) {
            Node  notTheseNode = notTheseNodeList.item( 0 );
            HashSet  terrainTypes = getTerrainTypesList( notTheseNode );
            trackRule.setCannotBuildOnTheseTerrainTypes( terrainTypes );
        }
        else {
            NodeList  onlyTheseNodeList = trackTypeElement.getElementsByTagName( "CanOnlyBuildOnTheseTerrainTypes" );
            if( onlyTheseNodeList.getLength() > 0 ) {
                Node  onlyTheseNode = onlyTheseNodeList.item( 0 );
                HashSet  terrainTypes = getTerrainTypesList( onlyTheseNode );
                trackRule.setCanOnlyBuildOnTheseTerrainTypes( terrainTypes );
            }
        }
    }
    
    private TrackRule createTrackRule( Node trackTypeNode, int ruleNumber ) throws FreerailsException {
        Element  trackTypeElement = (org.w3c.dom.Element)trackTypeNode;
        NodeList  trackPieceTemplateNodeList = trackTypeElement.getElementsByTagName( "TrackPieceTemplate" );
        int[]  trackPieceTemplateArray = new int[ trackPieceTemplateNodeList.getLength() ];
        int[][]  legalRoutesAcrossNodeArray = new int[ trackPieceTemplateNodeList.getLength() ][];
        for( int  i = 0;i < trackPieceTemplateArray.length;i++ ) {
            NamedNodeMap  trackPieceAttributes = trackPieceTemplateNodeList.item( i ).getAttributes();
            String  nodeValue = trackPieceAttributes.getNamedItem( "trackTemplate" ).getNodeValue();
            trackPieceTemplateArray[ i ] = (int)Integer.parseInt( nodeValue, 2 );
            legalRoutesAcrossNodeArray[ i ] = getLegalRoutesAcrossNode( trackPieceTemplateNodeList.item( i ) );
        }
        return new TrackRule( trackPieceTemplateArray, legalRoutesAcrossNodeArray, ruleNumber );
    }
    
    private int[] getLegalRoutesAcrossNode( Node trackPieceTemplate ) {
        Element  trackPieceTemplateElement = (org.w3c.dom.Element)trackPieceTemplate;
        NodeList  legalRoutesAcrossNodeNodeList = trackPieceTemplateElement.getElementsByTagName( "LegalRouteAcrossNode" );
        int[]  legalRoutesAcrossNode = new int[ legalRoutesAcrossNodeNodeList.getLength() ];
        for( int  i = 0;i < legalRoutesAcrossNode.length;i++ ) {
            NamedNodeMap  legalRouteAttributes = legalRoutesAcrossNodeNodeList.item( i ).getAttributes();
            String  nodeValue = legalRouteAttributes.getNamedItem( "RouteTemplate" ).getNodeValue();
            legalRoutesAcrossNode[ i ] = (int)Integer.parseInt( nodeValue, 2 );
        }
        return legalRoutesAcrossNode;
    }
    
    private HashSet getTerrainTypesList( Node terrainTypesNode ) {
        Element  terrainTypeListElement = (org.w3c.dom.Element)terrainTypesNode;
        NodeList  terrainTypeNodeList = terrainTypeListElement.getElementsByTagName( "TerrainType" );
        HashSet  terrainTypes = new HashSet();
        for( int  i = 0;i < terrainTypeNodeList.getLength();i++ ) {
            NamedNodeMap  terrainTypeAttributes = terrainTypeNodeList.item( i ).getAttributes();
            String  nodeValue = terrainTypeAttributes.getNamedItem( "name" ).getNodeValue();
            terrainTypes.add( nodeValue );
        }
        return terrainTypes;
    }
}
