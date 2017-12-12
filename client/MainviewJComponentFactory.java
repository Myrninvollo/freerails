
/*
* MainviewJTable.java
*
* Created on 19 May 2001, 18:46
*/
package jfreerails.client;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.table.AbstractTableModel;

import java.util.*;
import jfreerails.client.tileview.TileRenderer;
import jfreerails.client.IconMap;
import jfreerails.common.trackmodel.*;
import jfreerails.client.tileview.TileEventListener;
/**
* This class creates a JTable with a custom cell renderer to display the map in
*the main window.  The data model it uses is a 2D array of ImageIcons.
* @author  Luke Lindsay
* @version 
*/


public class MainviewJComponentFactory extends java.lang.Object {

    javax.swing.border.LineBorder not_selected = null;

    javax.swing.border.LineBorder selected = new javax.swing.border.LineBorder( java.awt.Color.white, 2 );

    private JTable tbl;

    private Point tileSize;
   
    public javax.swing.JTable newMainviewJTable( IconMap iconMap, TrackBuilder trackBuilder) {
        //String  cols[] = new String[ lblData[ 0 ].length ];
        
        //The column headings.  When col[i]==null, no heading is shown for column i.
        //TableModel  tblmodel = new DefaultTableModel( lblData, cols )  {
            
        //    public boolean isCellEditable( int row, int col ) {
         //       return false;
         //   }
        //};
    
        tbl = new JTable( iconMap );
        tbl.setPreferredSize( new java.awt.Dimension( ( (int)tileSize.getX() * iconMap.getColumnCount()), ( (int)tileSize.getY() * iconMap.getRowCount() ) ) );
        tbl.setIntercellSpacing( new java.awt.Dimension( 0, 0 ) );
        tbl.setRowHeight( (int)tileSize.getY() );
       // Class  bb = tbl.getColumnClass( 1 );
       // System.out.println(bb);
        tbl.setDefaultRenderer( Object.class, new TileRenderer((int)tileSize.getX(),(int)tileSize.getY()) );
        tbl.addMouseListener(new TileEventListener(tbl, trackBuilder, (AbstractTableModel) iconMap));
        
        return tbl;
    }
    
    public MainviewJComponentFactory( Point tileSize ) {
        this.tileSize = tileSize;
    }
}
