
//file: TestRenderer.java
package jfreerails.experimental;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.net.URL;
import java.io.IOException;
import java.util.*;


public class TestRenderer extends JComponent {

    protected boolean isSelected = false; //Whether the cursor is on this tile.

    protected ArrayList images = new ArrayList();

    private BasicStroke stroke = new BasicStroke( 4 ); //The width of the rectangle used to draw the cursor

    private int height = 30, width = 30; //The tile size.
    
    public void paint( Graphics g ) {
        //for( int  i = 0;i < images.size();i++ ) {
            if( images.get(0) != null ) {
                g.drawImage( (Image)images.get(0) , 0, 0, this );
            }
            else {
                //Graphics2D  g2 = (Graphics2D)g;
               // g2.fillRect(0,0,30,30);
              
                
                //System.out.println( "Non fatal error: tile icon==null" );
            }
       // }
        if( isSelected == true ) {
            
            //Draw the cursor.
            Graphics2D  g2 = (Graphics2D)g;
            g2.setStroke( stroke );
            g2.setColor( Color.darkGray ); //The colour of the cursor
            g2.drawRect( 0, 0, width, height );
        }
    }
    
    public TestRenderer() {
        //URL  tile_url = TestRenderer.class.getResource( "/jfreerails/experimental/single_terrain_tile.PNG" );
        //images.add(Toolkit.getDefaultToolkit().getImage( tile_url ));
       // URL  track_url = TestRenderer.class.getResource( "/jfreerails/experimental/single_track_tile.PNG" );
        //images.add(Toolkit.getDefaultToolkit().getImage( track_url ));
    }
    
    public void setTileValues( ArrayList images, boolean selected ) {
        this.images = images;
        this.isSelected = selected;
    }
    
    public static void main( String[] args ) {
        JFrame  f = new JFrame( "TestRenderer" );
        Container  c = f.getContentPane();
        c.setLayout( new BorderLayout() );
        c.add( new TestRenderer(), BorderLayout.CENTER );
        f.setSize( 300, 300 );
        f.setLocation( 100, 100 );
        f.addWindowListener( new WindowAdapter()  {
            
            public void windowClosing( WindowEvent e ) {
                System.exit( 0 );
            }
        } );
        f.setVisible( true );
    }
}
