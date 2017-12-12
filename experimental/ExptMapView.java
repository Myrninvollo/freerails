
/*
* ExptMapView.java
*
* Created on 21 August 2001, 21:11
*/
package jfreerails.experimental;
import java.awt.*;

/**
*
* @author  Luke Lindsay
* @version
*/


public class ExptMapView implements jfreerails.client.MapView {

    Dimension tileSize;

    Image testImage;

    private java.awt.GraphicsConfiguration defaultConfiguration = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    
    /** Creates new ExptMapView */
    
    public ExptMapView() {
        java.net.URL  testImageURL = ExptMapView.class.getResource( "/jfreerails/experimental/single_terrain_tile_big.png" );
        Image  temp = new javax.swing.ImageIcon( testImageURL ).getImage();
        tileSize = new Dimension( temp.getWidth( null ), temp.getHeight( null ) );
        System.out.println( tileSize.width );
        testImage = defaultConfiguration.createCompatibleImage( tileSize.width, tileSize.height );
        Graphics  g = testImage.getGraphics();
        g.drawImage( temp, 0, 0, null );
        g.dispose();
    }
    
    public Dimension getMapSizeInPixels() {
        return new Dimension( tileSize.width * 100, tileSize.height * 100 );
    }
    
    public Dimension getMapSizeInTiles() {
        return new Dimension( 100, 100 );
    }
    
    public Dimension getTileSize() {
        return tileSize;
    }
    
    public void paintTile( Graphics g, Point tileMapCoordinate, Point screenPosition ) {
        Graphics2D  g2 = (Graphics2D)g;
        String  str = tileMapCoordinate.x + ", " + tileMapCoordinate.y;
        g2.setColor( java.awt.Color.white );
        g2.drawImage( testImage, screenPosition.x, screenPosition.y, null );
    
    //  g2.drawString( str, screenPosition.x, screenPosition.y + 30 );
    
    //g2.drawImage( testImage, 0, 0, null );
    
    //g2.drawString( str, screenPosition.x, screenPosition.y + 30 );
    }
    
    public void updateTile( Point tileMapCoordinate ) {
        
    }
    
    public void paintTiles( Graphics g, Point tileMapCoordinate, Rectangle tilesToPaint ) {
        
    }
    
    public void updateTiles( Rectangle tileMapRectangle ) {
        
    }
}
