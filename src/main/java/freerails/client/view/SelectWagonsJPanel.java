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
 * SelectWagonsJPanel.java
 *
 */

package freerails.client.view;

import freerails.client.renderer.RendererRoot;
import freerails.controller.ModelRoot;
import freerails.world.SKEY;
import freerails.world.train.TrainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This JPanel lets the user add wagons to a train.
 */
@SuppressWarnings("unused")
public class SelectWagonsJPanel extends javax.swing.JPanel implements View {

    private static final long serialVersionUID = 3905239009449095220L;
    private final Image stationView;
    private final List<Integer> wagons = new ArrayList<>();
    private int engineType = 0;

    private RendererRoot rr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton okjButton;
    private javax.swing.JList wagonTypesJList;

    /**
     *
     */
    public SelectWagonsJPanel() {
        initComponents();
        updateMaxWagonsText();

        URL url = SelectWagonsJPanel.class.getResource("/freerails/data/station.gif");
        Image tempImage = (new javax.swing.ImageIcon(url)).getImage();

        GraphicsConfiguration defaultConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        stationView = defaultConfiguration.createCompatibleImage(tempImage.getWidth(null), tempImage.getHeight(null), Transparency.BITMASK);

        Graphics g = stationView.getGraphics();

        g.drawImage(tempImage, 0, 0, null);
    }

    /**
     *
     */
    public void resetSelectedWagons() {
        wagons.clear();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        JPanel jPanel1 = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        wagonTypesJList = new javax.swing.JList();
        okjButton = new javax.swing.JButton();
        JButton clearjButton = new JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        setBackground(new java.awt.Color(0, 255, 51));
        setMinimumSize(new java.awt.Dimension(640, 400));
        setPreferredSize(new java.awt.Dimension(620, 380));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(170, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(170, 300));
        wagonTypesJList.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                wagonTypesJListKeyTyped(e);
            }
        });
        wagonTypesJList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                wagonTypesJListMouseClicked(e);
            }
        });

        jScrollPane1.setViewportView(wagonTypesJList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        okjButton.setText("OK");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(okjButton, gridBagConstraints);

        clearjButton.setText("Clear");
        clearjButton.setActionCommand("clear");
        clearjButton.addActionListener(this::jButton1ActionPerformed);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(clearjButton, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel1.setText("The maximum train length is 6 wagons");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 400, 70, 10);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        add(jPanel1, gridBagConstraints);

    }

    private void wagonTypesJListMouseClicked(java.awt.event.MouseEvent evt) {
        // Add your handling code here:
        addwagon();
    }

    private void wagonTypesJListKeyTyped(java.awt.event.KeyEvent evt) {
        // Add your handling code here:
        if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
            addwagon();
        }

    }

    // Adds the wagon selected in the list to the train consist.
    private void addwagon() {
        if (wagons.size() < TrainModel.MAX_NUMBER_OF_WAGONS) {
            int type = wagonTypesJList.getSelectedIndex();
            wagons.add(type);

            updateMaxWagonsText();
            repaint();
        }

    }

    private void updateMaxWagonsText() {
        if (wagons.size() >= TrainModel.MAX_NUMBER_OF_WAGONS) {
            jLabel1.setText("Max train length is " + TrainModel.MAX_NUMBER_OF_WAGONS + " wagons");
        } else {
            jLabel1.setText("");
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Add your handling code here:
        wagons.clear();
        jLabel1.setText("");
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        // paint the background
        g.drawImage(stationView, 0, 0, null);

        int x = getWidth();

        int y = 330;

        final int SCALED_IMAGE_HEIGHT = 50;
        // paint the wagons
        for (int i = wagons.size() - 1; i >= 0; i--) { // Count down so we
            // paint the wagon
            // at the end of the
            // train first.

            Integer type = wagons.get(i);
            Image image = rr.getWagonImages(type).getSideOnImage();
            int scaledWidth = image.getWidth(null) * SCALED_IMAGE_HEIGHT / image.getHeight(null);
            x -= scaledWidth;
            g.drawImage(image, x, y, scaledWidth, SCALED_IMAGE_HEIGHT, null);

        }

        // paint the engine
        if (-1 != engineType) { // If an engine is selected.
            Image image = rr.getEngineImages(engineType).getSideOnImage();

            int scaledWidth = (image.getWidth(null) * SCALED_IMAGE_HEIGHT) / image.getHeight(null);
            x -= scaledWidth;
            g.drawImage(image, x, y, scaledWidth, SCALED_IMAGE_HEIGHT, null);
        }

        paintChildren(g);
    }

    /**
     * @param modelRoot
     * @param vl
     * @param closeAction
     */
    public void setup(ModelRoot modelRoot, RendererRoot vl, Action closeAction) {
        WorldToListModelAdapter w2lma = new WorldToListModelAdapter(modelRoot.getWorld(), SKEY.CARGO_TYPES);
        wagonTypesJList.setModel(w2lma);
        rr = vl;
        ListCellRenderer wagonCellRenderer = new WagonCellRenderer(w2lma, rr);
        wagonTypesJList.setCellRenderer(wagonCellRenderer);
        okjButton.addActionListener(closeAction);
    }

    /**
     * @return
     */
    public Integer[] getWagons() {
        Integer[] wagonsArray = new Integer[wagons.size()];
        for (int i = 0; i < wagons.size(); i++) {
            Integer type = wagons.get(i);
            wagonsArray[i] = type;
        }
        return wagonsArray;
    }

    /**
     * @param engineType
     */
    public void setEngineType(int engineType) {
        this.engineType = engineType;
    }


}
