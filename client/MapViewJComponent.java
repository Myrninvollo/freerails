
/*
* MapViewJComponent.java
*
* Created on 06 August 2001, 14:12
*/
package jfreerails.client;

/**
*
* @author  Luke Lindsay
* @version
*/


public abstract class MapViewJComponent extends javax.swing.JComponent implements javax.swing.Scrollable {

    protected MapView mapView;

    protected TiledBackgroundPainter tiledBackgroundPainter;
    
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
    
    public abstract void updateTiles( java.awt.Rectangle rect );
    
    /** Creates new MapViewJComponent */
    
    public MapViewJComponent( TiledBackgroundPainter tiledBackgroundPainter, MapView mapView ) {
        this.tiledBackgroundPainter = tiledBackgroundPainter;
        this.mapView = mapView;
        this.setPreferredSize( mapView.getMapSizeInPixels() );
    }
    
    public void paint( java.awt.Graphics g ) {
        java.awt.Graphics2D  g2 = (java.awt.Graphics2D)g;
        java.awt.Rectangle  r = this.getVisibleRect();
        tiledBackgroundPainter.paint( g2, r );
    }
    
    public java.awt.Dimension getPreferredScrollableViewportSize() {
        return this.getPreferredSize();
    }
    
    public int getScrollableUnitIncrement( java.awt.Rectangle rectangle, int orientation, int direction ) {
        if( javax.swing.SwingConstants.VERTICAL == orientation ) {
            return mapView.getTileSize().height;
        }
        else {
            return mapView.getTileSize().width;
        }
    }
    
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }
    
    public int getScrollableBlockIncrement( java.awt.Rectangle rectangle, int orientation, int direction ) {
        if( javax.swing.SwingConstants.VERTICAL == orientation ) {
            int  best = ( ( rectangle.height / mapView.getTileSize().height ) - 2 ) * mapView.getTileSize().height;
            if( best > 0 ) {
                return best;
            }
            else {
                return rectangle.height;
            }
        }
        else {
            int  best = ( ( rectangle.width / mapView.getTileSize().width ) - 2 ) * mapView.getTileSize().width;
            if( best > 0 ) {
                return best;
            }
            else {
                return rectangle.width;
            }
        }
    }
    
    public abstract void updateTile( java.awt.Point tileCoodinate );
}
