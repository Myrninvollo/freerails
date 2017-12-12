
/*
*  TileFactory.java
*
*  Created on 20 May 2001, 21:05
*/
package jfreerails;
import java.awt.Point;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;

import jfreerails.client.common.ImageSplitter;
import jfreerails.client.renderer.StandardTileRenderer;
import jfreerails.client.renderer.TileRenderer;
import jfreerails.client.renderer.TileRendererList;
import jfreerails.world.terrain.TerrainType;
import jfreerails.world.terrain.TileTypeImpl;
import jfreerails.world.top.KEY;
import jfreerails.world.top.World;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;





/**
*  This class provides methods to create a set of TileModel and TileView
*  objects, whose visual and non-visual properies are defined in an XML file.
*
*@author     Luke Lindsay.
*     09 October 2001
*@version    1.0
*/


final public class TileSetFactoryImpl extends java.lang.Object implements TileSetFactory {

    private World w;

    private TileRendererList tileViewList;
    
    private ArrayList tileTypes;

    private Element tileSet;

    private Point tileSize;

    private ImageSplitter tilesImageSplitter;


    final private class ParsingVariables extends java.lang.Object {


        /**
        *  Description of the Field
        */
        public TileRenderer parentTileView;


        /**
        *  Description of the Field
        */
        public Element tile;


        /**
        *  Description of the Field
        */
        public java.util.HashMap tileViewHashMap;


        /**
        *  Description of the Field
        */
        public ArrayList tileModelList;


        /**
        *  Description of the Field
        */
        public TerrainType thisTileModel;


        /**
        *  Description of the Field
        */
        public TileRenderer thisTileView;


        /**
        *  Description of the Field
        */
        public TerrainType parentTileModel;

        /**
        *  Constructor for the ParsingVariables object
        */

        public ParsingVariables() {
            tileViewHashMap = new java.util.HashMap();
            tileModelList = new ArrayList();
        }
    }

    /**
    *  This method is called by the client. It returns the visual properties of
    *  the terrain tiles.
    *
    *@return                      The object encapsulating the list of tile
    *      views.
    *@throws  FreerailsException  Thrown if there is an error parsing the XML
    *      file.
    */

    public TileRendererList getTileViewList()  {
        return this.tileViewList;
    }

    /**
    *  Creates new TileFactory.
    *
    *@param  xml_url                 The XML file specifying the terrain types.
    *@exception  FreerailsException  Description of Exception
    */

    public TileSetFactoryImpl( URL xml_url )  {
        org.w3c.dom.Element  tiles;
        org.w3c.dom.Document  document = jfreerails.client.common.DOMLoader.get_dom( xml_url );
        tiles = document.getDocumentElement();
        tiles.normalize();
        org.w3c.dom.NodeList  tilesetNodeList = tiles.getElementsByTagName( "Tile_set" );
        this.tileSet = (Element)tilesetNodeList.item( 0 );
        generateTileLists( tileSet.getChildNodes(), new ParsingVariables() );
    }

    /**
    *  This method returns the non-visual properties of the tile-set, so it
    *  would be called by the client and the server.
    *
    *@return    The set of tile models.
    */

    public void  addTerrainTileTypesList(World w) {
		for (int i=0; i < tileTypes.size() ; i++){
			 TerrainType tm = (TerrainType)tileTypes.get(i);
			 w.add(KEY.TERRAIN_TYPES, tm);        	
		 }    
    }

    /**
    *  Returns the tile size in pixels.
    *
    *@return                      tilesize in pixels.
    *@throws  FreerailsException  Thrown when the tile size has not been set.
    */

    public Point getTileSize()  {
        if( tileSize == null ) {
            throw new NullPointerException( "Error: TileFactory.getTileSize called before tilesize had been set" );
        }
        return tileSize;
    }

    /**
    *  Constructor for the TileFactoryImpl object
    *
    *@param  xml_url                 Description of Parameter
    *@param  terrain                 Description of Parameter
    *@exception  FreerailsException  Description of Exception
    */

    public TileSetFactoryImpl( URL xml_url, ImageSplitter terrain ) {
        this( xml_url );
        if( terrain != null ) {
            this.tilesImageSplitter = terrain;
            getTileSize( tilesImageSplitter );
        }
        else {
            throw new NullPointerException( "Null imageSplitter passed to the TileFactory constructor" );
        }
        generateTileLists( tileSet.getChildNodes(), new ParsingVariables() );
    }

    /**
    *  Gets the tileModel attribute of the TileFactoryImpl object
    *
    *@param  tile  A TILE element
    *@return       The tileModel value
    */

    private TerrainType getTileModel( Element tile ) {
        String  terrainType = tile.getAttribute( "Type" );
        String  terrainCategory = tile.getAttribute( "Category" );
        String  temp_number = tile.getAttribute( "rgb" );
        
        int  rgb = string2RGBValue( temp_number );
                
        TerrainType  tileModel = new TileTypeImpl( rgb, terrainCategory, terrainType );
        
        return tileModel;
    }

    /**
    *  Description of the Method
    *
    *@param  tilesToProcess          Description of Parameter
    *@param  parsingVariables        Description of Parameter
    *@exception  FreerailsException  Description of Exception
    */

    private void processList( NodeList tilesToProcess, ParsingVariables parsingVariables )  {
        for( int  i = 0;i < tilesToProcess.getLength();i++ ) {
            org.w3c.dom.Node  tile = tilesToProcess.item( i );
            String  str = tile.getNodeName();
            if( str.equalsIgnoreCase( "Tile" ) ) {
                TerrainType  tileModel = getTileModel( (Element)tile );
                parsingVariables.tileModelList.add( tileModel );
                if( tilesImageSplitter != null ) {
                    parsingVariables.thisTileView = getTileView( (Element)tile, tilesImageSplitter, tileModel, parsingVariables.parentTileView );
                    if( ( parsingVariables.thisTileView == null ) || ( tileModel == null ) ) {
                        throw new NullPointerException( "Error in TileFactory.getTileViewList - (tileView == null)||(tileModel==null) " );
                    }
                    parsingVariables.tileViewHashMap.put( new Integer( tileModel.getRGB() ), parsingVariables.thisTileView );
                }

                /*
                *  We need to check whether this Tile has any specials.
                */
                NodeList  children = tile.getChildNodes();
                for( int  ii = 0;ii < children.getLength();ii++ ) {
                    String  childName = children.item( ii ).getNodeName();
                    if( childName.equalsIgnoreCase( "specials" ) ) {
                        TileRenderer  tempThis = parsingVariables.thisTileView;
                        TileRenderer  tempParent = parsingVariables.parentTileView;
                        parsingVariables.parentTileView = parsingVariables.thisTileView;
                        processList( children.item( ii ).getChildNodes(), parsingVariables );
                        parsingVariables.thisTileView = tempThis;
                        parsingVariables.parentTileView = tempParent;
                    }
                }
            }
        }
    }

    /**
    *  Description of the Method
    *
    *@param  temp_number  Description of Parameter
    *@return              Description of the Returned Value
    */

    private int string2RGBValue( String temp_number ) {
        int  rgb = (int)Integer.parseInt( temp_number, 16 );

        /*
        *  We need to change the format of the rgb value to the same one as used
        *  by the the BufferedImage that stores the map.  See jfreerails.common.Map
        */
        rgb = new java.awt.Color( rgb ).getRGB();
        return rgb;
    }

    /**
    *  Description of the Method
    *
    *@param  tilesToProcess          Description of Parameter
    *@param  parsingVariables        Description of Parameter
    *@exception  FreerailsException  Description of Exception
    */

    private void generateTileLists( NodeList tilesToProcess, ParsingVariables parsingVariables) {
        processList( tilesToProcess, parsingVariables );
        this.tileViewList = new jfreerails.client.renderer.TileRendererListImpl( parsingVariables.tileViewHashMap );
        this.tileTypes = parsingVariables.tileModelList;          
    }

    /**
    *  Gets the tileSize attribute of the TileFactoryImpl object
    *
    *@param  terrain                 Description of Parameter
    *@exception  FreerailsException  Description of Exception
    */

    private void getTileSize( ImageSplitter terrain )  {
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
            throw new IllegalArgumentException( "ParseException while parsing the xml" + "file specifying the tile sizes.  Check that the \"Tile_set\" attributes: \"Height\", \"Width\", \"X\", and \"Y\" are integers" );
        }
    }

    private TileRenderer getTileView( Element tile, ImageSplitter imageSplitter, TerrainType tileModel, TileRenderer parentTileView )  {
        TileRenderer  tileView;
        int  x;
        int  y;

        //the tile icon position in the grid.
        try {
            String  temp_number = tile.getAttribute( "X" );
            x = NumberFormat.getInstance().parse( temp_number ).intValue();
            temp_number = tile.getAttribute( "Y" );
            y = NumberFormat.getInstance().parse( temp_number ).intValue();
        }
        catch( java.text.ParseException pe ) {
            throw new IllegalArgumentException( "ParseException while parsing the xml" + "file specifying the tile sizes.  Check that the \"Tile\" attributes: \"X\", and \"Y\" are integers" + "and that the attribute \"rgb\" is a hex value in the same format as the following: \"fcfc48\"" );
        }
        imageSplitter.setSubGridOffset( x, y );
        int[]  rgbValues = getRGBValuesToCheckFor( tileModel, tile );
        java.awt.Image[]  tileIcons;
        jfreerails.client.renderer.TileIconSelector  tileIconSelector;
        String  tileSelectorName = tile.getAttribute( "tileSelector" );
        tileSelectorName.equalsIgnoreCase( "Standard" );
        if( tileSelectorName.equalsIgnoreCase( "Standard" ) ) {
            tileView = new StandardTileRenderer( imageSplitter, rgbValues, tileModel );
        }
        else {
            if( tileSelectorName.equalsIgnoreCase( "Chequered" ) ) {
                tileView = new jfreerails.client.renderer.ChequeredTileRenderer( imageSplitter, rgbValues, tileModel );
            }
            else {
                if( tileSelectorName.equalsIgnoreCase( "ForestStyle" ) ) {
                    tileView = new jfreerails.client.renderer.ForestStyleTileRenderer( imageSplitter, rgbValues, tileModel );
                }
                else {
                    if( tileSelectorName.equalsIgnoreCase( "RiverStyle" ) ) {
                        tileView = new jfreerails.client.renderer.RiverStyleTileRenderer( imageSplitter, rgbValues, tileModel );
                    }
                    else {
                        if( tileSelectorName.equalsIgnoreCase( "special" ) ) {
                            tileView = new jfreerails.client.renderer.SpecialTileRenderer( imageSplitter, rgbValues, tileModel, parentTileView );
                        }

                        //Insert more TileIconSelector types here..
                        else {
                            System.out.println( "Error: the TileIconSelector's type was either not recognised or not specified" );
                            System.out.println( "Forced to use Standard TileIconSelector" );
                            tileView = new StandardTileRenderer( imageSplitter, rgbValues, tileModel );
                        }
                    }
                }
            }
        }
        return tileView;
    }

    /**
    *  Gets the rGBValuesToCheckFor attribute of the TileFactoryImpl object
    *
    *@param  tileModel  Description of Parameter
    *@param  tile       Description of Parameter
    *@return            The rGBValuesToCheckFor value
    */

    private int[] getRGBValuesToCheckFor( TerrainType tileModel, Element tile ) {
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
}
