/*
 * TileRenderer.java
 *
 * Created on 24 July 2001, 20:49
 */

package jfreerails.client.tileview;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.net.URL;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author  Luke Lindsay
 * @version 
 */
public class TileRenderer extends javax.swing.JComponent implements javax.swing.table.TableCellRenderer {

    protected boolean isSelected = false; //Whether the cursor is on this tile.

    protected ArrayList images = new ArrayList();

    private BasicStroke stroke = new BasicStroke( 4 ); //The width of the rectangle used to draw the cursor

    private int height, width; //The tile size.
    
    /** Creates new TileRenderer */
    public TileRenderer(int width, int height) {
         super();   //DO we need this??
        
        this.height=height;
        this.width=width;
       
        setOpaque( true ); //DO we need this??
    }

    public java.awt.Component getTableCellRendererComponent(javax.swing.JTable jTable, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
        this.isSelected=hasFocus; 
        images=(ArrayList)value;
        
        return this;
        }
        
        
    
    
    public void paint(java.awt.Graphics g) {
        for  (int i=0;i<images.size();i++){
        if( images.get(i) != null ) {
                g.drawImage( (Image)images.get(i) , 0, 0, this );
            }
        }
         if( isSelected == true ) {            
            //Draw the cursor.
            Graphics2D  g2 = (Graphics2D)g;
            g2.setStroke( stroke );
            g2.setColor( Color.white ); //The colour of the cursor
            g2.drawRect( 0, 0, width, height );
        }
        
    }
    
}
