
/*
* TileEventListener.java
*
* Created on 24 July 2001, 21:14
*/
package jfreerails.client.tileview;
import javax.swing.JTable;
import javax.swing.JLabel;
import jfreerails.lib.TextMessageHandler;
import jfreerails.common.OneTileMoveVector;
import jfreerails.common.IntPoint;
import jfreerails.common.exception.FreerailsException;
import jfreerails.common.trackmodel.TrackBuilder;
import jfreerails.common.trackmodel.TrackRule;
import javax.swing.table.AbstractTableModel;
import jfreerails.experimental.MainViewTableModel;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class TileEventListener extends java.awt.event.MouseAdapter {

    private TrackBuilder trackBuilder;

    private AbstractTableModel tableModel;

    private int x = 0, y = 0, oldX = 0, oldY = 0;

    private JTable jTable;
    
    /** Creates new TileEventListener */
    
    public TileEventListener( JTable jTable, TrackBuilder trackBuilder, AbstractTableModel tableModel ) {
        this.trackBuilder = trackBuilder;
        this.jTable = jTable;
        this.tableModel = tableModel;
    }
    
    public void mousePressed( java.awt.event.MouseEvent mouseEvent ) {
        x = jTable.getSelectedColumn();
        y = jTable.getSelectedRow();
        if( OneTileMoveVector.checkValidity( x - oldX, y - oldY ) ) {
            try {
                OneTileMoveVector  v = new OneTileMoveVector( x - oldX, y - oldY );
                trackBuilder.buildTrack( new IntPoint( oldX, oldY ), v );
                tableModel.fireTableCellUpdated( oldY, oldX );
                tableModel.fireTableCellUpdated( y, x );
            }
            catch( FreerailsException fe ) {
                
            }
        }
        else {
            TextMessageHandler.sendMessage( "Jumped to: " + x + ", " + y + ".   (Moving the cursor by one square lays track)" );
        }
        oldX = x;
        oldY = y;
    }
}
