/*
 * MainViewTableModel.java
 *
 * Created on 24 July 2001, 00:05
 */

package jfreerails.experimental;
import jfreerails.common.IntPoint;
import jfreerails.common.trackmodel.TrackMap;
import java.awt.Image;
import java.util.*;
import jfreerails.client.trackview.TrackPieceView;
import jfreerails.common.exception.FreerailsException;

/**
 *
 * @author  Luke Lindsay
 * @version 
 */
public class MainViewTableModel extends javax.swing.table.AbstractTableModel {
    
    private TrackMap trackMap;
   
    private TrackPieceView trackPieceView;
    
    /** Creates new MainViewTableModel */
    public MainViewTableModel(TrackMap trackMap, TrackPieceView trackPieceView) {
        this.trackMap=trackMap;
        this.trackPieceView=trackPieceView;
    }

   // public void addTableModelListener(javax.swing.event.TableModelListener tableModelListener) {
   // }
    
    public java.lang.Class getColumnClass(int param) {      
        return java.util.ArrayList.class;
    }
    
    public synchronized int  getColumnCount() {
        
         return trackMap.getWidth();
    }
    
    public java.lang.String getColumnName(int param) {
        return null;
    }
    
    public synchronized int  getRowCount() {
         return trackMap.getHeight();
    }
    
    public synchronized java.lang.Object  getValueAt(int row, int column) {
        try{
        int graphicsNumber=trackMap.getTrackGraphicNumber(new IntPoint(column,row));
        ArrayList imagesArrayList=new ArrayList();
        imagesArrayList.add((Object)trackPieceView.getTrackPieceIcon(graphicsNumber));
        
        return (Object)imagesArrayList;
        }
        catch(FreerailsException fe){
            //This should never happen;
            fe.printStackTrace();
            return null;
        }
    }
    
    public boolean isCellEditable(int param, int param1) {
        return false;
    }
    
    //public void removeTableModelListener(javax.swing.event.TableModelListener tableModelListener) {
    //}
    
    public synchronized void  setValueAt(java.lang.Object obj, int row, int column) {
       
    }
    
}
