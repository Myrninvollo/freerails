
 
/*
* test1.java
*
* Created on 23 May 2001, 00:49
*/
package jfreerails.lib;

/**
*  This class provides methods to grap tiles from an image.
* @author  lindsal8
* @version 
*/
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import jfreerails.common.exception.FreerailsException;


public class ImageSplitter extends java.lang.Object {

    private URL image_url;

    private int tile_grid_width, tile_grid_height, grid_start_x, grid_start_y;

    private BufferedImage bi_source;

    private int source_width, source_height;
    
    public ImageIcon get_tile_from_grid( int grid_x, int grid_y ) throws FreerailsException {
        ImageIcon  tile = get_tile( ( ( grid_start_x + ( grid_x * tile_grid_width ) ) ), ( ( grid_start_y + ( grid_y * tile_grid_width ) ) ), tile_grid_width, tile_grid_height );
        if( tile == null ) {
            System.out.print( "Error in ImageSplitter.ImageIcon: tileIcon==null." );
        }
        return tile;
    }
    
    public ImageIcon get_tile( int x, int y, int tile_width, int tile_height ) throws FreerailsException {
        try {
            BufferedImage  bi_tile = bi_source.getSubimage( x, y, tile_width, tile_height );
            ImageIcon  icon_tile = new ImageIcon( bi_tile );
            return icon_tile;
        }
        catch( java.awt.image.RasterFormatException e ) {
            throw new FreerailsException( "Tried to get tile from outside the image.\n" + "Source image: " + image_url );
        }
    }
    
    public ImageIcon[] get_tile_column( int x, int y, int tile_width, int tile_height, int num ) throws FreerailsException {
        ImageIcon[]  tile_column = new ImageIcon[ num ];
        for( int  count = 0;count < num;count++ ) {
            tile_column[ count ] = get_tile( x, y, tile_width, tile_height );
            y += tile_height;
        }
        return tile_column;
    }
    
    /** Creates new test1 */
    
    public ImageSplitter( URL url ) throws FreerailsException {
        
        //Load a picture and draw it on a buffered image.
        image_url = url;
        System.out.print( "\nLoading image: " + image_url );
        try {
            bi_source = ImageIO.read( image_url );
        }
        catch( IOException e ) {
            throw new FreerailsException( "IOException loading " + image_url );
        }
    }
    
    public ImageIcon[] get_tile_row( int x, int y, int tile_width, int tile_height, int num ) throws FreerailsException {
        ImageIcon[]  tile_row = new ImageIcon[ num ];
        for( int  count = 0;count < num;count++ ) {
            tile_row[ count ] = get_tile( x, y, tile_width, tile_height );
            x += tile_width;
        }
        return tile_row;
    }
    
    public void set_tile_grid( int x, int y, int tile_width, int tile_height ) {
        tile_grid_width = tile_width;
        tile_grid_height = tile_height;
        grid_start_x = x;
        grid_start_y = y;
    }
}
