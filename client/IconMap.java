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



/**
 *
 * @author  Luke Lindsay
 * @version 
 */
public class IconMap extends javax.swing.table.AbstractTableModel {

    
    private TerrainMap map;
    private HashMap tiles; 
    private TrackMap trackMap;
    private TrackPieceView trackPieceView;
    
    /** Creates new IconMap */
    public IconMap(TerrainMap map, HashMap tiles, TrackMap trackMap, TrackPieceView trackPieceView ) throws FreerailsException  {
        this.map=map;
        this.tiles=tiles;
        this.trackMap=trackMap;
        this.trackPieceView=trackPieceView;
    }

    public void addTableModelListener(javax.swing.event.TableModelListener tableModelListener) {
    }
    
    public java.lang.Class getColumnClass(int column) {
        return ArrayList.class;
    }
    
    public synchronized int getColumnCount() {
        return map.getWidth();
    }
    
    public java.lang.String getColumnName(int param) {
        return null;
    }
    
    public synchronized int getRowCount() {
        return map.getHeight();
    }
    
    public synchronized java.lang.Object getValueAt(int y, int x) {
        try{
            //Terrain
            int  rgb = map.getTerrainTileType( x, y ); //rgb valuesa map to a tile terrain type 
            ArrayList images=new ArrayList();
            TileView  tileview = (TileView)tiles.get( new Integer( rgb ) );
            ImageIcon  imageIcon = tileview.getIcon( x, y, map );
            images.add(imageIcon.getImage());
            
            
            //Track
            int graphicsNumber=trackMap.getTrackGraphicNumber(new IntPoint(x,y));
            images.add((Object)trackPieceView.getTrackPieceIcon(graphicsNumber));
           
            
            
            return (Object)images;
        }
        catch (FreerailsException fe){
            fe.printStackTrace();
            return  null;
        }
    }
    
    public boolean isCellEditable(int param, int param1) {
        return false;
    }
    
    public void removeTableModelListener(javax.swing.event.TableModelListener tableModelListener) {
    }
    
    public void setValueAt(java.lang.Object obj, int param, int param2) {
    }
    
}
