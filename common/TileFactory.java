
/*
* TileFactory.java
*
* Created on 20 May 2001, 21:05
*/
package jfreerails.common;

import java.net.URL;
import java.text.NumberFormat;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import jfreerails.lib.ImageSplitter;
import java.util.ArrayList;
import jfreerails.client.tileview.TileView;
import jfreerails.client.tileview.StandardTileView;
import java.awt.Point;
import jfreerails.common.exception.FreerailsException;

import jfreerails.client.tileview.TileViewList;

/** This class provides methods to create a set of TileModel and TileView objects,
* whose  visual and non-visual properies are defined in an XML file.
* @author Luke Lindsay.
* @version 1.0
*/


public class TileFactory extends java.lang.Object {

    private Element tileSet;

    private ImageSplitter tilesImageSplitter;

    private TerrainTileTypesList terrainTileTypesList;

    private TileViewList tileViewList;

    private Point tileSize;
    

    private class ParsingVariables extends java.lang.Object {

        public TileView thisTileView;

        public TileModel parentTileModel;

        public TileView parentTileView;

        public Element tile;

        public java.util.HashMap tileViewHashMap;

        public ArrayList tileModelList;

        public TileModel thisTileModel;
        
        public ParsingVariables() {
            tileViewHashMap = new java.util.HashMap();
            tileModelList = new ArrayList();
        }
    }
    
    public TileFactory(URL xml_url, ImageSplitter terrain) throws FreerailsException {
        this( xml_url );
        if( terrain != null ) {
            this.tilesImageSplitter = terrain;
            getTileSize( tilesImageSplitter );
        }
        else {
            throw new FreerailsException( "Null imageSplitter passed to the TileFactory constructor" );
        }
        generateTileLists( tileSet.getChildNodes(), new ParsingVariables() );
    }
    
    /** Returns the tile size in pixels.
    * @throws FreerailsException Thrown when the tile size has not been set.
    * @return tilesize in pixels.
    */
    
    public Point getTileSize() throws FreerailsException {
        if( tileSize == null ) {
            throw new FreerailsException( "Error: TileFactory.getTileSize called before tilesize had been set" );
        }
        return tileSize;
    }
    
    /** This method is called by the client.  It returns
    * the visual properties of the terrain tiles.
    *
    * @param terrain The imageSplitter that holds the image from
    * which the tile icons are to be grabbed.
    * @throws FreerailsException Thrown if there is an error parsing the XML file.
    * @return The object encapsulating the list of tile views.
    */
    
    public TileViewList getTileViewList() throws FreerailsException {
        return this.tileViewList;
    }
    
    /** Creates new TileFactory.
    * @param xml_url The XML file specifying the terrain types.
    */
    
    public TileFactory(URL xml_url) throws FreerailsException {
        org.w3c.dom.Element  tiles;
        org.w3c.dom.Document  document = jfreerails.lib.DOMLoader.get_dom( xml_url );
        tiles = document.getDocumentElement();
        tiles.normalize();
        org.w3c.dom.NodeList  tilesetNodeList = tiles.getElementsByTagName( "Tile_set" );
        this.tileSet = (Element)tilesetNodeList.item( 0 );
        generateTileLists( tileSet.getChildNodes(), new ParsingVariables() );
    }
    
    /** This method returns the non-visual properties of the tile-set,
    * so it would be called by the client and the server.
    * @return The set of tile models.
    */
    
    public TerrainTileTypesList getTerrainTileTypesList() {
        return terrainTileTypesList;
    }
    
    private void processList( NodeList tilesToProcess, ParsingVariables parsingVariables ) throws FreerailsException {
        for( int  i = 0;i < tilesToProcess.getLength();i++ ) {
            org.w3c.dom.Node  tile = tilesToProcess.item( i );
            String  str = tile.getNodeName();
            if( str.equalsIgnoreCase( "Tile" ) ) {
                TileModel  tileModel = getTileModel( (Element)tile );
                parsingVariables.tileModelList.add( tileModel );
                if( tilesImageSplitter != null ) {
                    parsingVariables.thisTileView = getTileView( (Element)tile, tilesImageSplitter, tileModel, parsingVariables.parentTileView );
                    if( ( parsingVariables.thisTileView == null ) || ( tileModel == null ) ) {
                        throw new FreerailsException( "Error in TileFactory.getTileViewList - (tileView == null)||(tileModel==null) " );
                    }
                    parsingVariables.tileViewHashMap.put( new Integer( tileModel.getRGB() ), parsingVariables.thisTileView );
                }
                
                /*We need to check whether this Tile has any specials. */
                NodeList  children = tile.getChildNodes();
                for( int  ii = 0;ii < children.getLength();ii++ ) {
                    String  childName = children.item( ii ).getNodeName();
                    if( childName.equalsIgnoreCase( "specials" ) ) {
                        TileView  tempThis = parsingVariables.thisTileView;
                        TileView  tempParent = parsingVariables.parentTileView;
                        parsingVariables.parentTileView = parsingVariables.thisTileView;
                        processList( children.item( ii ).getChildNodes(), parsingVariables );
                        parsingVariables.thisTileView = tempThis;
                        parsingVariables.parentTileView = tempParent;
                    }
                }
            }
        }
    }
    
    private void getTileSize( ImageSplitter terrain ) throws FreerailsException {
        try {
            
            //Get values, then setup the ImageSplitter.
            String  temp_number = tileSet.getAttribute( "Height" );
            int  height = NumberFormat.getInstance().parse( temp_number ).intValue();
            temp_number = tileSet.getAttribute( "Width" );
            int  width = NumberFormat.getInstance().parse( temp_number ).intValue();
            temp_number = tileSet.getAttribute( "X" );
            int  x = NumberFormat.getInstance().parse( temp_number ).intValue();
            temp_number = tileSet.getAttribute( "Y" );
            int  y = NumberFormat.getInstance().parse( temp_number ).intValue();
            terrain.setTileGrid( x, y, width, height );
            this.tileSize = new Point( width, height );
        }
        catch( java.text.ParseException pe ) {
            throw new FreerailsException( "ParseException while parsing the xml" + "file specifying the tile sizes.  Check that the \"Tile_set\" attributes: \"Height\", \"Width\", \"X\", and \"Y\" are integers" );
        }
    }
    
    private TileView getTileView( Element tile, ImageSplitter imageSplitter, TileModel tileModel, TileView parentTileView ) throws FreerailsException {
        TileView  tileView;
        int  x, y; //the tile icon position in the grid.
        try {
            String  temp_number = tile.getAttribute( "X" );
            x = NumberFormat.getInstance().parse( temp_number ).intValue();
            temp_number = tile.getAttribute( "Y" );
            y = NumberFormat.getInstance().parse( temp_number ).intValue();
        }
        catch( java.text.ParseException pe ) {
            throw new FreerailsException( "ParseException while parsing the xml" + "file specifying the tile sizes.  Check that the \"Tile\" attributes: \"X\", and \"Y\" are integers" + "and that the attribute \"rgb\" is a hex value in the same format as the following: \"fcfc48\"" );
        }
        imageSplitter.setSubGridOffset( x, y );
        int[]  rgbValues = getRGBValuesToCheckFor( tileModel, tile );
        java.awt.Image[]  tileIcons;
        jfreerails.client.tileview.TileIconSelector  tileIconSelector;
        String  tileSelectorName = tile.getAttribute( "tileSelector" );
        tileSelectorName.equalsIgnoreCase( "Standard" );
        if( tileSelectorName.equalsIgnoreCase( "Standard" ) ) {
            tileView = new StandardTileView( imageSplitter, rgbValues, tileModel );
        }
        else {
            if( tileSelectorName.equalsIgnoreCase( "Chequered" ) ) {
                tileView = new jfreerails.client.tileview.ChequeredTileView( imageSplitter, rgbValues, tileModel );
            }
            else {
                if( tileSelectorName.equalsIgnoreCase( "ForestStyle" ) ) {
                    tileView = new jfreerails.client.tileview.ForestStyleTileView( imageSplitter, rgbValues, tileModel );
                }
                else {
                    if( tileSelectorName.equalsIgnoreCase( "RiverStyle" ) ) {
                        tileView = new jfreerails.client.tileview.RiverStyleTileView( imageSplitter, rgbValues, tileModel );
                    }
                    else {
                        if( tileSelectorName.equalsIgnoreCase( "special" ) ) {
                            tileView = new jfreerails.client.tileview.SpecialTileView( imageSplitter, rgbValues, tileModel, parentTileView );
                        }
                        
                        //Insert more TileIconSelector types here..
                        else {
                            System.out.println( "Error: the TileIconSelector's type was either not recognised or not specified" );
                            System.out.println( "Forced to use Standard TileIconSelector" );
                            tileView = new StandardTileView( imageSplitter, rgbValues, tileModel );
                        }
                    }
                }
            }
        }
        return tileView;
    }
    
    private int[] getRGBValuesToCheckFor( TileModel tileModel, Element tile ) {
        int  rgb = tileModel.getRGB();
        ArrayList  rgbValuesArrayList = new ArrayList();
        rgbValuesArrayList.add( new Integer( rgb ) );
        NodeList  children = tile.getChildNodes();
        for( int  ii = 0;ii < children.getLength();ii++ ) {
            String  childName = children.item( ii ).getNodeName();
            if( childName.equalsIgnoreCase( "treatTheseAsTheSameType" ) ) {
                NodeList  treatTheseAsTheSameType = children.item( ii ).getChildNodes();
                for( int  i = 0;i < treatTheseAsTheSameType.getLength();i++ ) {
                    String  nodeName = treatTheseAsTheSameType.item( i ).getNodeName();
                    if( nodeName.equalsIgnoreCase( "Type" ) ) {
                        Element  terrainType = (Element)treatTheseAsTheSameType.item( i );
                        String  temp_number = terrainType.getAttribute( "rgb" );
                        int  _rgb = string2RGBValue( temp_number );
                        rgbValuesArrayList.add( new Integer( _rgb ) );
                    }
                }
            }
            if( childName.equalsIgnoreCase( "specials" ) ) {
                NodeList  treatTheseAsTheSameType = children.item( ii ).getChildNodes();
                for( int  i = 0;i < treatTheseAsTheSameType.getLength();i++ ) {
                    String  nodeName = treatTheseAsTheSameType.item( i ).getNodeName();
                    if( nodeName.equalsIgnoreCase( "Tile" ) ) {
                        Element  terrainType = (Element)treatTheseAsTheSameType.item( i );
                        String  temp_number = terrainType.getAttribute( "rgb" );
                        int  _rgb = string2RGBValue( temp_number );
                        rgbValuesArrayList.add( new Integer( _rgb ) );
                    }
                }
            }
        }
        int[]  rgbValues = new int[ rgbValuesArrayList.size() ];
        for( int  i = 0;i < rgbValuesArrayList.size();i++ ) {
            Integer  tempInt = (Integer)rgbValuesArrayList.get( i );
            rgbValues[ i ] = tempInt.intValue();
        }
        return rgbValues;
    }
    
    private TileModel getTileModel( Element tile ) {
        String  terrainType = tile.getAttribute( "Type" );
        String  temp_number = tile.getAttribute( "rgb" );
        int  rgb = string2RGBValue( temp_number );
        TileModel  tileModel = new TileModel( rgb, terrainType );
        return tileModel;
    }
    
    private int string2RGBValue( String temp_number ) {
        int  rgb = (int)Integer.parseInt( temp_number, 16 );
        
        /* We need to change the format of the rgb value to the same one as used
        by the the BufferedImage that stores the map.  See jfreerails.common.Map
        */
        rgb = new java.awt.Color( rgb ).getRGB();
        return rgb;
    }
    
    private void generateTileLists( NodeList tilesToProcess, ParsingVariables parsingVariables ) throws FreerailsException {
        processList( tilesToProcess, parsingVariables );
        this.tileViewList = new jfreerails.client.tileview.TileViewList( parsingVariables.tileViewHashMap );
        this.terrainTileTypesList = new TerrainTileTypesList( parsingVariables.tileModelList );
    }
}
