
/*
* BuildMenu.java
*
* Created on 30 July 2001, 06:49
*/
package jfreerails.client;
import jfreerails.common.trackmodel.TrackBuilder;

/**
*
* @author  Luke Lindsay
* @version

jyuhg182*/


public class BuildMenu extends javax.swing.JMenu {

    private final TrackBuilder trackBuilder;
    
    /** Creates new BuildMenu */
    
    public BuildMenu( jfreerails.common.trackmodel.TrackRuleList trackRuleList, TrackBuilder tb ) {
        super();
        this.trackBuilder = tb;
        this.setText( "Build" );
        javax.swing.ButtonGroup  trackTypesGroup = new javax.swing.ButtonGroup();
        javax.swing.ButtonGroup  buildRemoveOrUpgrade = new javax.swing.ButtonGroup();
        javax.swing.JRadioButtonMenuItem  buildTrackMenuItem = new javax.swing.JRadioButtonMenuItem( "Build Track" );
        buildTrackMenuItem.addActionListener( new java.awt.event.ActionListener()  {
            
            public void actionPerformed( java.awt.event.ActionEvent actionEvent ) {
                System.out.println( "build track" );
                trackBuilder.setTrackBuilderToBUILD_TRACK();
            }
        } );
        
        /*Set build track as the default*/
        buildTrackMenuItem.setSelected( true );
        trackBuilder.setTrackBuilderToBUILD_TRACK();
        buildRemoveOrUpgrade.add( buildTrackMenuItem );
        this.add( buildTrackMenuItem );
        javax.swing.JRadioButtonMenuItem  RemoveTrackMenuItem = new javax.swing.JRadioButtonMenuItem( "Remove Track" );
        RemoveTrackMenuItem.addActionListener( new java.awt.event.ActionListener()  {
            
            public void actionPerformed( java.awt.event.ActionEvent actionEvent ) {
                System.out.println( "remove track" );
                trackBuilder.setTrackBuilderToREMOVE_TRACK();
            }
        } );
        buildRemoveOrUpgrade.add( RemoveTrackMenuItem );
        this.add( RemoveTrackMenuItem );
        javax.swing.JRadioButtonMenuItem  upgradeTrackMenuItem = new javax.swing.JRadioButtonMenuItem( "Upgrade Track" );
        upgradeTrackMenuItem.addActionListener( new java.awt.event.ActionListener()  {
            
            public void actionPerformed( java.awt.event.ActionEvent actionEvent ) {
                System.out.println( "upgrade track" );
                trackBuilder.setTrackBuilderToUPGRADE_TRACK();
            }
        } );
        buildRemoveOrUpgrade.add( upgradeTrackMenuItem );
        this.add( upgradeTrackMenuItem );
        this.addSeparator();
        for( int  i = 0;i < trackRuleList.getLength();i++ ) {
            final jfreerails.common.trackmodel.TrackRule  trackRule = trackRuleList.getTrackRule( i );
            String  trackType = trackRule.getTypeName();
            javax.swing.JRadioButtonMenuItem  rbMenuItem = new javax.swing.JRadioButtonMenuItem( "Build " + trackType );
            rbMenuItem.addActionListener( new java.awt.event.ActionListener()  {
                
                public void actionPerformed( java.awt.event.ActionEvent actionEvent ) {
                    trackBuilder.setTrackRule( trackRule );
                }
            } );
            if( 0 == i ) {
                rbMenuItem.setSelected( true );
            }
            trackTypesGroup.add( rbMenuItem );
            this.add( rbMenuItem );
        }
    }
}
