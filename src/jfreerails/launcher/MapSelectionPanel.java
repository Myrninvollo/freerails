/*
 * Copyright (C) 2003 Robert Tuck
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

/*
 * JPanel.java
 *
 * Created on 20 December 2003, 15:16
 */

package jfreerails.launcher;

import javax.swing.JFileChooser;
import java.io.File;

import jfreerails.client.common.FileUtils;
import jfreerails.server.GameServer;
import jfreerails.util.Resources;

/**
 *
 * @author rtuck99@users.sourceforge.net
 */
class MapSelectionPanel extends javax.swing.JPanel {
    private String currentDirectory = FileUtils.savesDirectory;

    private Launcher owner;

    static final int LOAD_SAVED_GAME = 0;
    static final int START_NEW_MAP = 1;

    int getMapAction() {
	if (loadMapButton.isSelected()) {
	    return LOAD_SAVED_GAME;
	} else {
	    return START_NEW_MAP;
	}
    }
    
    private void validateSettings() {
	boolean isValid = true;
	String infoText = "";
	if (loadMapButton.isSelected()) {
	    File f = new File(fileNameJTextField.getText());
	    if (! f.isFile()) {
		infoText = Resources.get("You must select a valid "
			+ "file name");
		isValid = false;
	    }
	}
	owner.setInfoText(infoText);
	owner.setNextEnabled(isValid);
    }

    public String getMapName() {
	return (String) jList1.getSelectedValue();
    }

    public File getLoadFilename() {
	return new File(fileNameJTextField.getText());
    }

    /** Creates new form JPanel */
    MapSelectionPanel(Launcher owner) {
	this.owner = owner;

        initComponents();

	/* initialise the map list */
	jList1.setListData(GameServer.getMapNames());
	jList1.setSelectedIndex(0);

	owner.setNextEnabled(true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        loadMapButton = new javax.swing.JRadioButton();
        fileNameJTextField = new javax.swing.JTextField();
        chooseFileNameJButton = new javax.swing.JButton();
        newMapButton = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setLayout(new java.awt.BorderLayout(4, 4));

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 4));

        jPanel3.setAlignmentX(0.0F);
        loadMapButton.setText(jfreerails.util.Resources.get("Load a saved game"));
        buttonGroup1.add(loadMapButton);
        loadMapButton.setMargin(new java.awt.Insets(2, 0, 2, 2));
        loadMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMapButtonActionPerformed(evt);
            }
        });

        jPanel3.add(loadMapButton);

        fileNameJTextField.setColumns(20);
        fileNameJTextField.setEnabled(false);
        fileNameJTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fileNameJTextFieldFocusLost(evt);
            }
        });

        jPanel3.add(fileNameJTextField);

        chooseFileNameJButton.setText("...");
        chooseFileNameJButton.setEnabled(false);
        chooseFileNameJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFileNameJButtonActionPerformed(evt);
            }
        });

        jPanel3.add(chooseFileNameJButton);

        jPanel2.add(jPanel3);

        newMapButton.setSelected(true);
        newMapButton.setText(jfreerails.util.Resources.get("Start a new map"));
        buttonGroup1.add(newMapButton);
        newMapButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newMapButton.setMargin(new java.awt.Insets(2, 4, 2, 2));
        newMapButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                newMapButtonStateChanged(evt);
            }
        });

        jPanel2.add(newMapButton);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.BorderLayout(4, 4));

        jPanel1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), jfreerails.util.Resources.get("Select Map")));
        jScrollPane1.setViewportBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

    private void fileNameJTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fileNameJTextFieldFocusLost
	validateSettings();
    }//GEN-LAST:event_fileNameJTextFieldFocusLost

    private void loadMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMapButtonActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_loadMapButtonActionPerformed

    private void chooseFileNameJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFileNameJButtonActionPerformed
	JFileChooser chooser = new JFileChooser(currentDirectory);
	chooser.setMultiSelectionEnabled(false);
	int option = chooser.showOpenDialog(this);
	if (option == JFileChooser.APPROVE_OPTION) {
	    fileNameJTextField.setText(chooser.getSelectedFile().getPath());
	}
	currentDirectory = chooser.getCurrentDirectory().getPath();
	validateSettings();
    }//GEN-LAST:event_chooseFileNameJButtonActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
	owner.setNextEnabled(true);
    }//GEN-LAST:event_formComponentShown

    private void newMapButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_newMapButtonStateChanged
	jList1.setEnabled(newMapButton.isSelected());
	chooseFileNameJButton.setEnabled(! newMapButton.isSelected());
	fileNameJTextField.setEnabled(! newMapButton.isSelected());
	validateSettings();
    }//GEN-LAST:event_newMapButtonStateChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton chooseFileNameJButton;
    private javax.swing.JTextField fileNameJTextField;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton loadMapButton;
    private javax.swing.JRadioButton newMapButton;
    // End of variables declaration//GEN-END:variables
    
}
