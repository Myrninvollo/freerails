
/*
* ClientJFrame.java
*
* Created on 25 July 2001, 01:52
*/
package jfreerails.client;
import javax.swing.JTable;
import jfreerails.client.BuildMenu;
import jfreerails.common.trackmodel.*;
import jfreerails.lib.TextMessageHandler;

/**
*
* @author  Luke Lindsay
*/


public class ClientJFrame extends javax.swing.JFrame implements jfreerails.client.MessengerBoy {

    private javax.swing.JMenuBar jMenuBar1;

    private javax.swing.JLabel messageBoyJLabel;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JScrollPane jScrollPane1;

    private BuildMenu buildMenu;

    private javax.swing.JTable mainViewJTable;
    
    /** Creates new form ClientJFrame */
    
    public ClientJFrame( JTable jTable1, BuildMenu buildMenu ) {
        TextMessageHandler.setMessengerBoy( this );
        this.buildMenu = buildMenu;
        this.mainViewJTable = jTable1;
        System.out.println( "\nCreating client window" );
        initComponents();
    }
    
    public void displayMessage( java.lang.String message ) {
        messageBoyJLabel.setText( message );
    }
    
    private void initComponents() {
        messageBoyJLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
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
        messageBoyJLabel.setText( "Welcome to freerails.  Use the mouse to lay track.  Moving the cursor by one square lays track" );
        getContentPane().add( messageBoyJLabel, java.awt.BorderLayout.SOUTH );
        jScrollPane1.setPreferredSize( new java.awt.Dimension( 500, 500 ) );
        jPanel1.add( mainViewJTable );
        jScrollPane1.setViewportView( jPanel1 );
        getContentPane().add( jScrollPane1, java.awt.BorderLayout.CENTER );
        pack();
    }
    
    /** Exit the Application */
    
    private void exitForm( java.awt.event.WindowEvent evt ) {
        
    }
}
