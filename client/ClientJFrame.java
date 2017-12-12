
/*
* ClientJFrame.java
*
* Created on 25 July 2001, 01:52
*/
package jfreerails.client;

/** The JFrame for the client window.
*
* @author Luke Lindsay
*/


public class ClientJFrame extends javax.swing.JFrame implements jfreerails.client.TextMessenger {

    private javax.swing.JScrollPane mapViewJScrollPane;

    private BuildMenu buildMenu;

    private javax.swing.JMenuBar jMenuBar1;

    private javax.swing.JLabel textMessageOutputJLabel;

    private javax.swing.JComponent mapView;
    
    /** Displays a messages to the player.
    * @param message The message to be displayed.
    */
    
    public void displayMessage( java.lang.String message ) {
        textMessageOutputJLabel.setText( message );
    }
    
    /** Creates new form ClientJFrame
    * @param mapView The component that displays the map.
    * @param buildMenu The menu for choosing different track types etc.
    */
    
    public ClientJFrame( javax.swing.JComponent mapView, BuildMenu buildMenu ) {
        
        //this.setUndecorated(true);
        jfreerails.lib.TextMessageHandler.setMessengerBoy( this );
        this.buildMenu = buildMenu;
        this.mapView = mapView;
        System.out.println( "\nCreating client window" );
        initComponents();
    }
    
    private void exitForm( java.awt.event.WindowEvent evt ) {
        
    
    //Exit application could go here.        
    }
    
    private void initComponents() {
        textMessageOutputJLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mapViewJScrollPane = new javax.swing.JScrollPane();
        addWindowListener( new java.awt.event.WindowAdapter()  {
            
            public void windowClosing( java.awt.event.WindowEvent evt ) {
                exitForm( evt );
            }
        } );
        jMenuBar1.add( buildMenu );
        addWindowListener( new java.awt.event.WindowAdapter()  {
            
            public void windowClosing( java.awt.event.WindowEvent evt ) {
                exitForm( evt );
            }
        } );
        setJMenuBar( jMenuBar1 );
        textMessageOutputJLabel.setText( "Welcome to freerails.  Use the mouse to lay track.  Moving the cursor by one square lays track" );
        getContentPane().add( textMessageOutputJLabel, java.awt.BorderLayout.SOUTH );
        mapViewJScrollPane.setPreferredSize( new java.awt.Dimension( 500, 500 ) );
        mapViewJScrollPane.setViewportView( mapView );
        getContentPane().add( mapViewJScrollPane, java.awt.BorderLayout.CENTER );
        pack();
    }
}
