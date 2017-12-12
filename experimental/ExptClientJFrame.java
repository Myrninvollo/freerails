
/*
* ClientJFrame.java
*
* Created on 25 July 2001, 01:52
*/
package jfreerails.experimental;
import javax.swing.JComponent;

/** The JFrame for the client window.
*
* @author Luke Lindsay
*/


public class ExptClientJFrame extends javax.swing.JFrame implements jfreerails.client.TextMessenger {

    private JComponent mapView;

    private javax.swing.JScrollPane mapViewJScrollPane;
    
    public void displayMessage( String message ) {
        System.out.println( message );
    }
    
    public ExptClientJFrame( JComponent mapView ) {
        this.mapView = mapView;
        initComponents();
    }
    
    /** Exit the Application */
    
    private void exitForm( java.awt.event.WindowEvent evt ) {
        System.exit( 0 );
    }
    
    private void initComponents() {
        mapViewJScrollPane = new javax.swing.JScrollPane();
        addWindowListener( new java.awt.event.WindowAdapter()  {
            
            public void windowClosing( java.awt.event.WindowEvent evt ) {
                exitForm( evt );
            }
        } );
        addWindowListener( new java.awt.event.WindowAdapter()  {
            
            public void windowClosing( java.awt.event.WindowEvent evt ) {
                exitForm( evt );
            }
        } );
        mapViewJScrollPane.setPreferredSize( new java.awt.Dimension( 500, 500 ) );
        mapViewJScrollPane.setViewportView( mapView );
        getContentPane().add( mapViewJScrollPane, java.awt.BorderLayout.CENTER );
        pack();
    }
}
