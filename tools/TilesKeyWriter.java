
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
import java.net.URL;


public class TilesKeyWriter extends java.lang.Object {
    
    public TilesKeyWriter() {
        
    }
    
    /**
    * @param args the command line arguments
    */
    
    public static void main( String args[] ) {
        try {
            
            //Load the picture containing the tile graphics.
            URL  tiles_url = TilesKeyWriter.class.getResource( "/jfreerails/data/freerails_tiles.PNG" );
            jfreerails.lib.ImageSplitter  terrain = new jfreerails.lib.ImageSplitter( tiles_url );
            URL  tiles_xml_url = TilesKeyWriter.class.getResource( "/jfreerails/data/Tiles.xml" );
            draw_key( tiles_xml_url, terrain );
            System.exit( 0 );
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
    
    public static void draw_key( URL tiles_xml_url, jfreerails.lib.ImageSplitter terrain ) {
        try {
            int  x = 0, y = 0; //Position on the buffered image on which we draw the key.
            jfreerails.common.TileFactory  tileFactory = new jfreerails.common.TileFactory( tiles_xml_url, terrain );
            jfreerails.client.tileview.TileViewList  tiles = tileFactory.getTileViewList();
            java.awt.Point  tileSize = tileFactory.getTileSize();
            int  tile_height = tileSize.x;
            int  number_of_tiles = tiles.getLength();
            java.awt.image.BufferedImage  tilesKey;
            tilesKey = new java.awt.image.BufferedImage( 300, ( tile_height + 2 ) * number_of_tiles + 50, java.awt.image.BufferedImage.TYPE_INT_RGB );
            java.awt.Graphics2D  g2 = tilesKey.createGraphics();
            y += 16;
            g2.setColor( java.awt.Color.white );
            String  str = tiles_xml_url.getFile();
            g2.drawString( str, 0, y );
            y += 20;
            java.util.Iterator  iterator = tiles.getIterator();
            System.out.println( "Drawing tiles" );
            while( iterator.hasNext() ) {
                jfreerails.client.tileview.TileView  tileView = (jfreerails.client.tileview.TileView)iterator.next();
                java.awt.Image  img = tileView.getIcon();
                x = 10;
                y += 2;
                g2.setPaint( new java.awt.Color( tileView.getRGB() ) );
                java.awt.Rectangle  rect = new java.awt.Rectangle( x, y, tile_height, tile_height );
                g2.fill( rect );
                x += 10 + tile_height;
                g2.drawImage( img, x, y, null );
                x += 10 + tile_height;
                g2.setColor( java.awt.Color.white );
                y += tile_height;
                g2.drawString( tileView.getTerrainType(), x, y - 2 );
            }
            System.out.println( "Finished drawing tiles" );
            URL  saveURL = TilesKeyWriter.class.getResource( "/jfreerails/data/" );
            String  saveFilename = saveURL.getPath() + "key.png";
            if( javax.imageio.ImageIO.write( (java.awt.image.RenderedImage)tilesKey, "png", new java.io.File( saveFilename ) ) ) {
                System.out.println( "Key saved as: " + saveFilename );
            }
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
