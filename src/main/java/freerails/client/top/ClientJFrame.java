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
 * ClientJFrame.java
 *
 */

package freerails.client.top;

/**
 * The JFrame that you see while you are playing the game.
 */
public class ClientJFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 3834868100742265142L;

    private GUIComponentFactory gUIComponentFactory;

    /**
     * Creates new form ClientJFrame.
     *
     * @param gcf
     */
    public ClientJFrame(GUIComponentFactory gcf) {
        setup(gcf);
    }

    /**
     * @param args
     */
    public static void main(String args[]) {
        new ClientJFrame(new GUIComponentFactoryTestImpl()).setVisible(true);
    }

    private void setup(GUIComponentFactory gcf) {
        gUIComponentFactory = gcf;
        initComponents();
        gUIComponentFactory.createDateJLabel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        javax.swing.JPanel rhsjPanel = new javax.swing.JPanel();
        javax.swing.JPanel mapOverview = gUIComponentFactory.createOverviewMap();
        javax.swing.JTabbedPane trainsJTabPane1 = gUIComponentFactory.createTrainsJTabPane();

        javax.swing.JPanel lhsjPanel = new javax.swing.JPanel();
        javax.swing.JScrollPane mainMapView = gUIComponentFactory.createMainMap();
        javax.swing.JPanel statusjPanel = new javax.swing.JPanel();
        javax.swing.JLabel datejLabel = gUIComponentFactory.createDateJLabel();
        javax.swing.JLabel cashjLabel = gUIComponentFactory.createCashJLabel();
        javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
        javax.swing.JMenu gameMenu = gUIComponentFactory.createGameMenu();
        javax.swing.JMenu buildMenu = gUIComponentFactory.createBuildMenu();
        javax.swing.JMenu brokerMenu1 = gUIComponentFactory.createBrokerMenu();
        javax.swing.JMenu displayMenu = gUIComponentFactory.createDisplayMenu();
        javax.swing.JMenu reportsMenu = gUIComponentFactory.createReportsMenu();
        javax.swing.JMenu helpMenu = gUIComponentFactory.createHelpMenu();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        rhsjPanel.setLayout(new java.awt.GridBagLayout());

        rhsjPanel.add(mapOverview, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        rhsjPanel.add(trainsJTabPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(rhsjPanel, gridBagConstraints);

        lhsjPanel.setLayout(new java.awt.GridBagLayout());

        mainMapView.setAlignmentX(0.0F);
        mainMapView.setAlignmentY(0.0F);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        lhsjPanel.add(mainMapView, gridBagConstraints);

        statusjPanel.add(datejLabel);

        statusjPanel.add(cashjLabel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        lhsjPanel.add(statusjPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(lhsjPanel, gridBagConstraints);

        gameMenu.setText("Game");
        jMenuBar1.add(gameMenu);

        buildMenu.setText("Build");
        jMenuBar1.add(buildMenu);

        brokerMenu1.setText("Broker");
        jMenuBar1.add(brokerMenu1);

        displayMenu.setText("Display");
        jMenuBar1.add(displayMenu);

        reportsMenu.setText("Reports");
        jMenuBar1.add(reportsMenu);

        helpMenu.setText("Help");
        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }

    /**
     * Exit the Application.
     */
    @SuppressWarnings("unused")
    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }

    // End of variables declaration                   

}
