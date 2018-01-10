/*
 * FreeRails
 * Copyright (C) 2000-2018 The FreeRails Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

/*
 * LauncherPanel1.java
 *
 */

package freerails.client.launcher;

import javax.swing.*;

/**
 * The first launcher panel, lets you choose 'single player', 'start network
 * game' etc.
 */
final class LauncherPanel1 extends javax.swing.JPanel {
    static final int MODE_SINGLE_PLAYER = 0;
    static final int MODE_START_NETWORK_GAME = 1;
    static final int MODE_JOIN_NETWORK_GAME = 2;
    static final int MODE_SERVER_ONLY = 3;
    private static final long serialVersionUID = 3257850965422913590L;
    private final ButtonModel[] buttonModels = new ButtonModel[4];
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.ButtonGroup buttonGroup1;

    /*
     * private void validateSettings() { boolean isValid = false; String
     * infoText = null;
     *
     * switch (getMode()) {
     *
     * case MODE_SINGLE_PLAYER: isValid = true; break; case
     * MODE_START_NETWORK_GAME: case MODE_SERVER_ONLY: isValid = true; break;
     * case MODE_JOIN_NETWORK_GAME: isValid = true; break; }
     * owner.setInfoText(infoText, LauncherInterface.WARNING);
     * owner.setNextEnabled(isValid); }
     */
    javax.swing.JRadioButton joinNetworkButton;
    javax.swing.JPanel paddingJPanel;
    javax.swing.JRadioButton serverOnlyButton;
    javax.swing.JRadioButton singlePlayerButton;
    javax.swing.JRadioButton startNetworkButton;

    public LauncherPanel1() {
        initComponents();
        buttonModels[MODE_SINGLE_PLAYER] = singlePlayerButton.getModel();
        buttonModels[MODE_START_NETWORK_GAME] = startNetworkButton.getModel();
        buttonModels[MODE_JOIN_NETWORK_GAME] = joinNetworkButton.getModel();
        buttonModels[MODE_SERVER_ONLY] = serverOnlyButton.getModel();
    }

    int getMode() {
        for (int i = 0; i < buttonModels.length; i++) {
            if (buttonGroup1.getSelection() == buttonModels[i]) {
                return i;
            }
        }
        assert false;
        return 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        singlePlayerButton = new javax.swing.JRadioButton();
        startNetworkButton = new javax.swing.JRadioButton();
        joinNetworkButton = new javax.swing.JRadioButton();
        serverOnlyButton = new javax.swing.JRadioButton();
        paddingJPanel = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        setBorder(new javax.swing.border.TitledBorder(
                new javax.swing.border.EtchedBorder(), "Select Game Type"));
        buttonGroup1.add(singlePlayerButton);
        singlePlayerButton.setSelected(true);
        singlePlayerButton.setText("Single-Player");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(singlePlayerButton, gridBagConstraints);

        buttonGroup1.add(startNetworkButton);
        startNetworkButton.setText("Start a network game");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(startNetworkButton, gridBagConstraints);

        buttonGroup1.add(joinNetworkButton);
        joinNetworkButton.setText("Join a network game");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(joinNetworkButton, gridBagConstraints);

        buttonGroup1.add(serverOnlyButton);
        serverOnlyButton.setText("Server only");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(serverOnlyButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(paddingJPanel, gridBagConstraints);

    }
    // End of variables declaration//GEN-END:variables

}
