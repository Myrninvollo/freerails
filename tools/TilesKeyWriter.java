
/*
* TilesKeyWriter.java
*This class loads the xml file defining the terrain tile types and draws a
*key, as a png file, mapping rgb values to the icons of each of the terrain
*types.  This key can then be used to aid map creation in a graphics program, 
*e.g. The Gimp.
*
* Created on 03 June 2001, 19:59
*/
package jfreerails.tools;

/**
*
* @author  lindsal8
* @version 
*/
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.*;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.imageio.*;
import jfreerails.client.tileview.TileView;
import jfreerails.common.exception.FreerailsException;
import jfreerails.common.TileFactory;
import jfreerails.lib.ImageSplitter;


public class TilesKeyWriter extends java.lang.Object {
    
    public TilesKeyWriter() {
        
    }
    
    public static void draw_key( URL tiles_xml_url, ImageSplitter terrain ) {
        try {
            int  x = 0, y = 0; //Position on the buffered image on which we draw the key.
            TileFactory  tileFactory = new TileFactory( tiles_xml_url );
            HashMap  tileViewHashMap = tileFactory.getTileViewHashMap( terrain );
            Point  tileSize = tileFactory.getTileSize();
            int  tile_height = (int)tileSize.getX();
            int  number_of_tiles = tileViewHashMap.size();
            BufferedImage  tilesKey;
            tilesKey = new BufferedImage( 300, ( tile_height + 2 ) * number_of_tiles + 50, BufferedImage.TYPE_INT_RGB );
            Graphics2D  g2 = tilesKey.createGraphics();
            y += 16;
            g2.setColor( Color.white );
            String  str = tiles_xml_url.getFile();
            g2.drawString( str, 0, y );
            y += 20;
            Iterator  iterator = tileViewHashMap.values().iterator();
            System.out.println( "Drawing tiles" );
            while( iterator.hasNext() ) {
                TileView  tileView = (TileView)iterator.next();
                Image  img = tileView.getIcon().getImage();
                x = 10;
                y += 2;
                g2.setPaint( new Color( tileView.getRGB() ) );
                Rectangle2D.Double  rect = new Rectangle2D.Double( (double)x, (double)y, (double)tile_height, (double)tile_height );
                g2.fill( rect );
                x += 10 + tile_height;
                g2.drawImage( img, x, y, null );
                x += 10 + tile_height;
                g2.setColor( Color.white );
                y += tile_height;
                g2.drawString( tileView.getTerrainType(), x, y - 2 );
            }
            System.out.println( "Finished drawing tiles" );
            URL  saveURL = TilesKeyWriter.class.getResource( "/jfreerails/data/" );
            String  saveFilename = saveURL.getPath() + "key.png";
            if( ImageIO.write( (RenderedImage)tilesKey, "png", new File( saveFilename ) ) ) {
                System.out.println( "Key saved as: " + saveFilename );
            }
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
    
    /**
    * @param args the command line arguments
    */
    
    public static void main( String args[] ) {
        try {
            
            //Load the picture containing the tile graphics.
            URL  tiles_url = TilesKeyWriter.class.getResource( "/jfreerails/data/freerails_tiles.PNG" );
            ImageSplitter  terrain = new ImageSplitter( tiles_url );
            URL  tiles_xml_url = TilesKeyWriter.class.getResource( "/jfreerails/data/Tiles.xml" );
            draw_key( tiles_xml_url, terrain );
            System.exit( 0 );
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
