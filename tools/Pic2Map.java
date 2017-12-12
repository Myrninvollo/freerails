
/*
* Pic2Map.java
*
* Created on 04 June 2001, 04:25
*/
package jfreerails.tools;

/**
*
* @author  lindsal8
* @version 
*/
import java.awt.Color;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.net.URL;
import java.io.IOException;
import jfreerails.common.TileFactory;
import jfreerails.common.TileModel;
import jfreerails.common.TerrainMapIO;
import jfreerails.lib.TerminalIO;


public class Pic2Map extends Object {
    
    //Generate map from an image file and return it as an array of shorts.
    
    public static short[][] Pic2Map( String filename, String terrain_filename ) {
        BufferedImage  bi_source;
        int  source_width, source_height;
        short[][]  map;
        
        //Load the image file and draw on a buffered so we can read the rgb values of 
        
        //individual pixels.
        URL  image_url = Pic2Map.class.getResource( filename );
        ImageIcon  icon_source = new ImageIcon( image_url );
        source_width = icon_source.getIconWidth();
        source_height = icon_source.getIconHeight();
        Image  img_source = icon_source.getImage();
        bi_source = new BufferedImage( source_width, source_height, BufferedImage.TYPE_INT_RGB );
        Graphics2D  biContext_source = bi_source.createGraphics();
        biContext_source.drawImage( img_source, 0, 0, null );
        
        //Load the xml file. It defines which rgb value is assigned to each of the terrain types.
        URL  xml_url = Pic2Map.class.getResource( terrain_filename );
        TileFactory  xtt = new TileFactory( xml_url );
        TileModel[]  tileModelList = xtt.getTileModelList();
        
        //This array maps terrain types to rgb values.
        int[]  rgb2type_terrain_type = new int[ tileModelList.length ];
        
        //Convert the rgb values to the same format as getRGB() uses.
        for( int  i = 0;i < tileModelList.length;i++ ) {
            rgb2type_terrain_type[ i ] = new Color( tileModelList[ i ].getRGB() ).getRGB();
        }
        int  rgb;
        
        //The rgb value of a pixel form the image file.
        map = new short[ source_width ][ source_height ];
        
        //stores values representing terrain types.
        int  i;
        for( int  x = 0;x < source_width;x++ ) {
            for( int  y = 0;y < source_height;y++ ) {
                rgb = bi_source.getRGB( x, y );
                i = 0;
                while( rgb2type_terrain_type[ i ] != rgb ) {
                    if( i == rgb2type_terrain_type.length ) {
                        System.out.println( "Warning: There is no terrain type assigned to colour: " + rgb );
                        rgb = rgb2type_terrain_type[ i ];
                    
                    //We've come acroos a colour we were not expecting, 
                    
                    //we fudge it.
                    }
                    else {
                        i++;
                    }
                }
                map[ x ][ y ] = (short)i;
            }
        }
        return map;
    }
    
    public static void main( String args[] ) {
        String  map_name, xmlName;
        TerminalIO  my_io = new TerminalIO();
        map_name = my_io.my_read_line( "Enter map filename (e.g. test)" );
        
        //xmlName=my_io.my_read_line("Enter terrain types xml filename (e.g. tile.xml)");
        short[][]  map = Pic2Map.Pic2Map( "/jfreerails/data/" + map_name + ".png", "/jfreerails/data/Tiles.xml" );
        URL  savePath = Pic2Map.class.getResource( "/jfreerails/data/" );
        String  mapSaveFilename = savePath.getPath() + map_name + ".map";
        TerrainMapIO.save_map( map, mapSaveFilename );
        System.out.println( "\nGoodBye..\n" );
        System.exit( 0 );
    }
}
