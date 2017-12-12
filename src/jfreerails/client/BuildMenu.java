
/*
* BuildMenu.java
*
* Created on 30 July 2001, 06:49
*/
package jfreerails.client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import jfreerails.list.TrackRuleList;
import jfreerails.move.source.TrackMoveProducer;


/**
*
* @author  Luke Lindsay
* @version
*/


final public class BuildMenu extends javax.swing.JMenu {

    private final TrackMoveProducer trackBuilder;
    
    /** Creates new BuildMenu */
    
    public BuildMenu( jfreerails.list.TrackRuleList trackRuleList, TrackMoveProducer tb ) {
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
            final int  trackRuleNumber = i;
            String  trackType = trackRuleList.getTrackRule( i ).getTypeName();
            javax.swing.JRadioButtonMenuItem  rbMenuItem = new javax.swing.JRadioButtonMenuItem( "Build " + trackType );
            rbMenuItem.addActionListener( new java.awt.event.ActionListener()  {
                
                public void actionPerformed( java.awt.event.ActionEvent actionEvent ) {
                    trackBuilder.setTrackRule( trackRuleNumber );
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
