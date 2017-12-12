
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
import jfreerails.common.exception.FreerailsException;
import java.awt.Transparency;
import java.awt.Image;


public class ImageSplitter extends java.lang.Object {

    private int subGridYoffset = 0;

    private int tileGridWidth, tileGridHeight, gridXAxisOrigin, gridYAxisOrigin;

    private java.awt.image.BufferedImage sourceBufferedImage;

    private java.net.URL imageURL;

    private int subGridXOffset = 0;

    private int source_width, source_height;

    private int transparency = Transparency.OPAQUE;

    private java.awt.GraphicsConfiguration defaultConfiguration = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    
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
    
    public Image getTileFromSubGrid( int gridX, int gridY ) throws FreerailsException {
        Image  tile = getTile( ( ( gridXAxisOrigin + ( ( subGridXOffset + gridX ) * tileGridWidth ) ) ), ( ( gridYAxisOrigin + ( ( subGridYoffset + gridY ) * tileGridWidth ) ) ), tileGridWidth, tileGridHeight );
        if( tile == null ) {
            System.out.println( "Error in ImageSplitter.Image: tileIcon==null." );
        }
        return tile;
    }
    
    public void setTransparencyToOPAQUE() {
        this.transparency = Transparency.OPAQUE;
    }
    
    public void setTransparencyToBITMASK() {
        this.transparency = Transparency.BITMASK;
    }
    
    public void setTransparencyToTRANSLUCENT() {
        this.transparency = Transparency.TRANSLUCENT;
    }
    
    public Image getTileFromGrid( int gridX, int gridY ) throws FreerailsException {
        Image  tile = getTile( ( ( gridXAxisOrigin + ( gridX * tileGridWidth ) ) ), ( ( gridYAxisOrigin + ( gridY * tileGridWidth ) ) ), tileGridWidth, tileGridHeight );
        if( tile == null ) {
            System.out.println( "Error in ImageSplitter.Image: tileIcon==null." );
        }
        return tile;
    }
    
    public Image getTile( int x, int y, int tileWidth, int tileHeight ) throws FreerailsException {
        try {
            java.awt.image.BufferedImage  tileBufferedImage = defaultConfiguration.createCompatibleImage( tileWidth, tileHeight, transparency );
            java.awt.Graphics  g = tileBufferedImage.getGraphics();
            g.drawImage( sourceBufferedImage, -x, -y, null );
            return tileBufferedImage;
        }
        catch( Exception e ) {
            throw new FreerailsException( "Tried to get tile from outside the image.\n" + "Source image: " + imageURL );
        }
    }
    
    public ImageSplitter( java.net.URL url ) throws FreerailsException {
        
        //Load a picture and draw it on a buffered image.
        imageURL = url;
        System.out.println( "\nLoading image: " + imageURL );
        try {
            sourceBufferedImage = javax.imageio.ImageIO.read( imageURL );
        }
        catch( java.io.IOException e ) {
            throw new FreerailsException( "IOException loading " + imageURL );
        }
    }
}
