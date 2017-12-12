package jfreerails.experimental;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;


public class TileSelectionListener implements ListSelectionListener,TableColumnModelListener {

    private JLabel outputJLabel; //Where we display messages.

    private int oldX = 0, oldY = 0; //The coordinates of the last tile that was selected.

    private int newX = 0, newY = 0; //The coordinates of the new tile selected.
    
    public void columnRemoved( TableColumnModelEvent e ) {
        
    }
    
    public void columnMarginChanged( ChangeEvent e ) {
        
    }
    
    public TileSelectionListener( JLabel outputJLabel ) {
        this.outputJLabel = outputJLabel;
    }
    
    public void valueChanged( ListSelectionEvent e ) {
        if( e.getValueIsAdjusting() ) {
            if( oldY == e.getFirstIndex() ) {
                newY = e.getLastIndex();
            }
            else {
                newY = e.getFirstIndex();
            }
        }
        else {
            
        }
        System.out.println( "Change in Y =" + oldX + " -> " + newX );
    }
    
    public void columnAdded( TableColumnModelEvent e ) {
        
    }
    
    public void columnMoved( TableColumnModelEvent e ) {
        
    }
    
    public void columnSelectionChanged( ListSelectionEvent e ) {
        if( e.getValueIsAdjusting() ) {
            if( oldX == e.getFirstIndex() ) {
                newX = e.getLastIndex();
            }
            else {
                newX = e.getFirstIndex();
            }
        }
        else {
            
        }
        System.out.println( "Change in X =" + oldX + " -> " + newX );
    }
    
    public TileSelectionListener() {
        
    }
}
