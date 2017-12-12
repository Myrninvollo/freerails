
/*
* IconMap.java
*
* Created on 25 July 2001, 02:37
*/
package jfreerails.client;
import jfreerails.common.exception.FreerailsException;
import jfreerails.client.tileview.TileView;
import jfreerails.common.TerrainMap;
import java.util.*;
import java.lang.Integer;
import javax.swing.ImageIcon;
import java.awt.Image;
import jfreerails.common.IntPoint;
import jfreerails.common.trackmodel.TrackMap;
import jfreerails.client.trackview.TrackPieceView;
import jfreerails.client.trackview.TrackPieceViewList;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class IconMap extends javax.swing.table.AbstractTableModel {

    private TrackMap trackMap;

    private TrackPieceViewList trackPieceViewList;

    private TerrainMap map;

    private HashMap tiles;
    
    public void addTableModelListener( javax.swing.event.TableModelListener tableModelListener ) {
        
    }
    
    public java.lang.Class getColumnClass( int column ) {
        return ArrayList.class;
    }
    
    public boolean isCellEditable( int param, int param1 ) {
        return false;
    }
    
    /** Creates new IconMap */
    
    public IconMap( TerrainMap map, HashMap tiles, TrackMap trackMap, TrackPieceViewList trackPieceViewList ) throws FreerailsException {
        this.map = map;
        this.tiles = tiles;
        this.trackMap = trackMap;
        this.trackPieceViewList = trackPieceViewList;
    }
    
    public synchronized java.lang.Object getValueAt( int y, int x ) {
        try {
            
            //Terrain
            int  rgb = map.getTerrainTileType( x, y ); //rgb valuesa map to a tile terrain type 
            ArrayList  images = new ArrayList();
            TileView  tileview = (TileView)tiles.get( new Integer( rgb ) );
            ImageIcon  imageIcon = tileview.getIcon( x, y, map );
            images.add( imageIcon.getImage() );
            
            //Track
            int  graphicsNumber = trackMap.getTrackGraphicNumber( new IntPoint( x, y ) );
            int  ruleNumber = trackMap.getTrackTypeNumber( new IntPoint( x, y ) );
            
            // images.add( (Object)trackPieceViewList.getTrackPieceView(0).getTrackPieceIcon( graphicsNumber ) );
            images.add( (Object)trackPieceViewList.getTrackPieceView( ruleNumber ).getTrackPieceIcon( graphicsNumber ) );
            return (Object)images;
        }
        catch( FreerailsException fe ) {
            fe.printStackTrace();
            return null;
        }
    }
    
    public void setValueAt( java.lang.Object obj, int param, int param2 ) {
        
    }
    
    public void removeTableModelListener( javax.swing.event.TableModelListener tableModelListener ) {
        
    }
    
    public synchronized int getColumnCount() {
        return map.getWidth();
    }
    
    public synchronized int getRowCount() {
        return map.getHeight();
    }
    
    public java.lang.String getColumnName( int param ) {
        return null;
    }
}
