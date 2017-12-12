
/*
* BuildMenu.java
*
* Created on 30 July 2001, 06:49
*/
package jfreerails.client;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import jfreerails.common.trackmodel.*;
import java.awt.event.ActionListener;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class BuildMenu extends javax.swing.JMenu {

    private final TrackBuilder trackBuilder;
    
    /** Creates new BuildMenu */
    
    public BuildMenu( TrackRuleList trackRuleList, TrackBuilder tb ) {
        super();
        this.trackBuilder = tb;
        this.setText( "Build" );
        ButtonGroup  group = new ButtonGroup();
        for( int  i = 0;i < trackRuleList.getLength();i++ ) {
            final TrackRule  trackRule = trackRuleList.getTrackRule( i );
            String  trackType = trackRule.getTypeName();
            JRadioButtonMenuItem  rbMenuItem = new JRadioButtonMenuItem( "Build " + trackType );
            rbMenuItem.addActionListener( new ActionListener()  {
                
                public void actionPerformed( java.awt.event.ActionEvent actionEvent ) {
                    trackBuilder.setTrackRule( trackRule );
                }
            } );
            if( 0 == i ) {
                rbMenuItem.setSelected( true );
            }
            group.add( rbMenuItem );
            this.add( rbMenuItem );
        }
    }
}
