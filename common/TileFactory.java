
/*
* TileFactory.java
*
* Created on 20 May 2001, 21:05
*/
package jfreerails.common;

/**
*  This class provides methods to create a set of TileModel and TileView objects,
* whose properties are defined in an xml file.
*
* @author  Luke Lindsay.
* @version 
*/
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.Point;
import java.awt.Color;
import javax.swing.ImageIcon;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.*;
import java.util.HashMap;
import java.lang.Integer;
import org.w3c.dom.*;
import org.w3c.dom.DOMException;
import jfreerails.common.TileModel;
import jfreerails.lib.ImageSplitter;
import jfreerails.lib.DOMLoader;
import jfreerails.client.tileview.TileView;
import jfreerails.client.tileview.*;
import jfreerails.common.exception.FreerailsException;
import jfreerails.common.TerrainTileTypesList;


public class TileFactory extends java.lang.Object {

    private Point tileSize;

    private NamedNodeMap tileSetAttributes;

    private NodeList tilesNodeList;
    
    public Point getTileSize() throws FreerailsException {
        if( tileSize == null ) {
            throw new FreerailsException( "Error: TileFactory.getTileSize called before tilesize had been set" );
        }
        return tileSize;
    }
    
    public HashMap getTileViewHashMap( ImageSplitter terrain ) throws FreerailsException {
        System.out.print( "\nGetting terrain tiles from image" );
        try {
            
            //Get values, then setup the ImageSplitter.
            String  temp_number = tileSetAttributes.getNamedItem( "Height" ).getNodeValue();
            int  height = NumberFormat.getInstance().parse( temp_number ).intValue();
            temp_number = tileSetAttributes.getNamedItem( "Width" ).getNodeValue();
            int  width = NumberFormat.getInstance().parse( temp_number ).intValue();
            temp_number = tileSetAttributes.getNamedItem( "X" ).getNodeValue();
            int  x = NumberFormat.getInstance().parse( temp_number ).intValue();
            temp_number = tileSetAttributes.getNamedItem( "Y" ).getNodeValue();
            int  y = NumberFormat.getInstance().parse( temp_number ).intValue();
            terrain.setTileGrid( x, y, width, height );
            this.tileSize = new Point( width, height );
        }
        catch( java.text.ParseException pe ) {
            throw new FreerailsException( "ParseException while parsing the xml" + "file specifying the tile sizes.  Check that the \"Tile_set\" attributes: \"Height\", \"Width\", \"X\", and \"Y\" are integers" );
        }
        HashMap  tileViewHashMap = new HashMap();
        
        //TileView[] tileViewList=new TileView[tilesNodeList.getLength()];
        NamedNodeMap  namedNodeMap_tile_attributes;
        for( int  i = 0;i < tilesNodeList.getLength();i++ ) {
            namedNodeMap_tile_attributes = tilesNodeList.item( i ).getAttributes();
            TileModel  tileModel = getTileModel( namedNodeMap_tile_attributes );
            TileView  tileView = getTileView( namedNodeMap_tile_attributes, terrain, tileModel );
            if( tileView == null ) {
                throw new FreerailsException( "Error in TileFactory.getTileViewList - tileView==null" );
            }
            
            //tileViewList[i].setTileModel(tileModel);
            tileViewHashMap.put( new Integer( tileModel.getRGB() ), tileView );
        }
        return tileViewHashMap;
    }
    
    /** Creates new TileFactory.  It loads an XML file that defines the terrain 
    types in the tile-set*/
    
    public TileFactory( URL xml_url ) {
        Element  tiles;
        Document  document = DOMLoader.get_dom( xml_url );
        tiles = document.getDocumentElement();
        tiles.normalize();
        NodeList  tilesetNodeList = tiles.getElementsByTagName( "Tile_set" );
        Node  node_tile_set = tilesetNodeList.item( 0 );
        this.tilesNodeList = tiles.getElementsByTagName( "Tile" );
        this.tileSetAttributes = node_tile_set.getAttributes();
    }
    
    public TerrainTileTypesList getTerrainTileTypesList() {
        TileModel[]  tileModelList = new TileModel[ tilesNodeList.getLength() ];
        NamedNodeMap  namedNodeMap_tile_attributes;
        for( int  i = 0;i < tilesNodeList.getLength();i++ ) {
            namedNodeMap_tile_attributes = tilesNodeList.item( i ).getAttributes();
            tileModelList[ i ] = getTileModel( namedNodeMap_tile_attributes );
        }
        return new TerrainTileTypesList( tileModelList );
    }
    
    private TileModel getTileModel( NamedNodeMap namedNodeMap_tile_attributes ) {
        String  terrainType = namedNodeMap_tile_attributes.getNamedItem( "Type" ).getNodeValue();
        String  temp_number = namedNodeMap_tile_attributes.getNamedItem( "rgb" ).getNodeValue();
        int  rgb = (int)Integer.parseInt( temp_number, 16 );
        
        /* We need to change the format of the rgb value to the same one as used 
        by the the BufferedImage that stores the map.  See jfreerails.common.Map
        */
        rgb = new Color( rgb ).getRGB();
        TileModel  tileModel = new TileModel( rgb, terrainType );
        return tileModel;
    }
    
    private TileView getTileView( NamedNodeMap namedNodeMap_tile_attributes, ImageSplitter imageSplitter, TileModel tileModel ) throws FreerailsException {
        int  x, y; //the tile icon position in the grid.
        try {
            String  temp_number = namedNodeMap_tile_attributes.getNamedItem( "X" ).getNodeValue();
            x = NumberFormat.getInstance().parse( temp_number ).intValue();
            temp_number = namedNodeMap_tile_attributes.getNamedItem( "Y" ).getNodeValue();
            y = NumberFormat.getInstance().parse( temp_number ).intValue();
        }
        catch( java.text.ParseException pe ) {
            throw new FreerailsException( "ParseException while parsing the xml" + "file specifying the tile sizes.  Check that the \"Tile\" attributes: \"X\", and \"Y\" are integers" + "and that the attribute \"rgb\" is a hex value in the same format as the following: \"fcfc48\"" );
        }
        int  rgb = tileModel.getRGB();
        int[]  rgbValues =  {
            rgb
        };
        ImageIcon[]  tileIcons;
        TileIconSelector  tileIconSelector;
        String  tileSelectorName = namedNodeMap_tile_attributes.getNamedItem( "tileSelector" ).getNodeValue();
        tileSelectorName.equalsIgnoreCase( "Standard" );
        if( tileSelectorName.equalsIgnoreCase( "Standard" ) ) {
            tileIconSelector = new StandardTileIconSelector( rgbValues );
            tileIcons = new ImageIcon[ 1 ];
            tileIcons[ 0 ] = imageSplitter.getTileFromGrid( x, y );
        }
        else {
            if( tileSelectorName.equalsIgnoreCase( "Chequered" ) ) {
                tileIconSelector = new ChequeredTileIconSelector( rgbValues );
                tileIcons = new ImageIcon[ 2 ];
                for( int  i = 0;i < tileIcons.length;i++ ) {
                    tileIcons[ i ] = imageSplitter.getTileFromGrid( x + i, y );
                }
            }
            else {
                if( tileSelectorName.equalsIgnoreCase( "ForestStyle" ) ) {
                    tileIconSelector = new ForestStyleTileIconSelector( rgbValues );
                    tileIcons = new ImageIcon[ 4 ];
                    
                    //Grap them in this order so that they display correctly :)
                    tileIcons[ 0 ] = imageSplitter.getTileFromGrid( x + 2, y );
                    tileIcons[ 1 ] = imageSplitter.getTileFromGrid( x + 3, y );
                    tileIcons[ 2 ] = imageSplitter.getTileFromGrid( x + 1, y );
                    tileIcons[ 3 ] = imageSplitter.getTileFromGrid( x, y );
                }
                else {
                    if( tileSelectorName.equalsIgnoreCase( "RiverStyle" ) ) {
                        tileIconSelector = new RiverStyleTileIconSelector( rgbValues );
                        tileIcons = new ImageIcon[ 16 ];
                        for( int  i = 0;i < tileIcons.length;i++ ) {
                            tileIcons[ i ] = imageSplitter.getTileFromGrid( x + i, y );
                        }
                    }
                    
                    //Insert more TileIconSelector types here..
                    else {
                        System.out.println( "Error: the TileIconSelector's type was either not recognised or not specified" );
                        System.out.println( "Forced to use Standard TileIconSelector" );
                        tileIconSelector = new StandardTileIconSelector( rgbValues );
                        tileIcons = new ImageIcon[ 1 ];
                        tileIcons[ 0 ] = imageSplitter.getTileFromGrid( x, y );
                    }
                }
            }
        }
        TileView  tileView = new TileView( tileIcons, tileIconSelector, tileModel );
        return tileView;
    }
}
