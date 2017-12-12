
/*
* TerrainMap.java
*
* Created on 13 July 2001, 09:06
*/
package jfreerails.common;

/**
*  This class encapsulates the terrain map.
*
* @author  Luke Lindsay
* @version 
*/
import java.awt.image.BufferedImage;
import java.awt.Point;
import jfreerails.common.TerrainTileTypesList;
import java.awt.Color;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.net.URL;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import jfreerails.common.exception.FreerailsException;


public class TerrainMap extends java.lang.Object {

    private TerrainTileTypesList terrainTileTypesList;

    private BufferedImage map;
    
    public int getTerrainTileType( int x, int y ) {
        return map.getRGB( x, y );
    }
    
    public String getTerrainTypeName( int x, int y ) {
        return terrainTileTypesList.getTerrainName( map.getRGB( x, y ) );
    }
    
    public int getWidth() {
        return map.getWidth();
    }
    
    /** Creates new TerrainMap */
    
    public TerrainMap( BufferedImage map, TerrainTileTypesList terrainTileTypesList ) {
        this.map = map;
        this.terrainTileTypesList = terrainTileTypesList;
    }
    
    public TerrainMap( URL url, TerrainTileTypesList terrainTileTypesList ) throws FreerailsException {
        try {
            System.out.println( "Loading map " + url );
            BufferedImage  map = ImageIO.read( url );
            this.map = map;
            this.terrainTileTypesList = terrainTileTypesList;
        }
        catch( Exception e ) {
            throw new FreerailsException( "Error loading map: " + url );
        }
    }
    
    public int getHeight() {
        return map.getHeight();
    }
}
