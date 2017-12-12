
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

    private int subGridYoffset = 0;

    private int tileGridWidth, tileGridHeight, gridXAxisOrigin, gridYAxisOrigin;

    private BufferedImage sourceBufferedImage;

    private URL imageURL;

    private int subGridXOffset = 0;

    private int source_width, source_height;
    
    public ImageIcon getTileFromGrid( int gridX, int gridY ) throws FreerailsException {
        ImageIcon  tile = getTile( ( ( gridXAxisOrigin + ( gridX * tileGridWidth ) ) ), ( ( gridYAxisOrigin + ( gridY * tileGridWidth ) ) ), tileGridWidth, tileGridHeight );
        if( tile == null ) {
            System.out.println( "Error in ImageSplitter.ImageIcon: tileIcon==null." );
        }
        return tile;
    }
    
    public ImageIcon getTile( int x, int y, int tileWidth, int tileHeight ) throws FreerailsException {
        try {
            BufferedImage  tileBufferedImage = sourceBufferedImage.getSubimage( x, y, tileWidth, tileHeight );
            ImageIcon  tileIcon = new ImageIcon( tileBufferedImage );
            return tileIcon;
        }
        catch( java.awt.image.RasterFormatException e ) {
            throw new FreerailsException( "Tried to get tile from outside the image.\n" + "Source image: " + imageURL );
        }
    }
    
    public ImageSplitter( URL url ) throws FreerailsException {
        
        //Load a picture and draw it on a buffered image.
        imageURL = url;
        System.out.println( "\nLoading image: " + imageURL );
        try {
            sourceBufferedImage = ImageIO.read( imageURL );
        }
        catch( IOException e ) {
            throw new FreerailsException( "IOException loading " + imageURL );
        }
    }
    
    public void setTileGrid( int x, int y, int tileWidth, int tileHeight ) {
        tileGridWidth = tileWidth;
        tileGridHeight = tileHeight;
        gridXAxisOrigin = x;
        gridYAxisOrigin = y;
    }
    
    public void setSubGridOffset( int x, int y ) {
        subGridXOffset = x;
        subGridYoffset = y;
    }
    
    public ImageIcon getTileFromSubGrid( int gridX, int gridY ) throws FreerailsException {
        ImageIcon  tile = getTile( ( ( gridXAxisOrigin + ( ( subGridXOffset + gridX ) * tileGridWidth ) ) ), ( ( gridYAxisOrigin + ( ( subGridYoffset + gridY ) * tileGridWidth ) ) ), tileGridWidth, tileGridHeight );
        if( tile == null ) {
            System.out.println( "Error in ImageSplitter.ImageIcon: tileIcon==null." );
        }
        return tile;
    }
}
