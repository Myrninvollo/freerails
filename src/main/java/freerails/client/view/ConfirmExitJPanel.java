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
 * ConfirmExitJPanel.java
 *
 */

package freerails.client.view;

import freerails.client.renderer.RendererRoot;
import freerails.controller.ModelRoot;

import javax.swing.*;

/**
 * JPanel that displays confirmation of exiting, used when the exit menu item is
 * selected or x is pressed.
 */
public class ConfirmExitJPanel extends javax.swing.JPanel implements View {

    private static final long serialVersionUID = 3256728398394110517L;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeJButton;

    /**
     * Creates new form ConfirmExitJPanel.
     */
    public ConfirmExitJPanel() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        JPanel jPanel1 = new JPanel();
        JLabel jLabel1 = new JLabel();
        JPanel jPanel2 = new JPanel();
        JButton confirmExit = new JButton();
        closeJButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        setPreferredSize(new java.awt.Dimension(240, 140));
        jLabel1.setText("Are you sure you want to Exit?");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel1);

        add(jPanel1, new java.awt.GridBagConstraints());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        confirmExit.setText("Exit");
        confirmExit.setContentAreaFilled(false);
        confirmExit.addActionListener(this::confirmExitActionPerformed);

        jPanel2.add(confirmExit, new java.awt.GridBagConstraints());

        closeJButton.setText("Cancel");
        jPanel2.add(closeJButton, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        add(jPanel2, gridBagConstraints);

    }// GEN-END:initComponents

    @SuppressWarnings("unused")
    private void confirmExitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_confirmExitActionPerformed
        System.exit(0);
    }// GEN-LAST:event_confirmExitActionPerformed

    public void setup(ModelRoot mr, RendererRoot vl, Action closeAction) {
        closeJButton.setAction(closeAction);
    }

    // End of variables declaration//GEN-END:variables

}
