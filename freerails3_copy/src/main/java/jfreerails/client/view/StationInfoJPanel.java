/*
 * StationInfoJPanel.java
 *
 * Created on 04 May 2003, 18:56
 */

package jfreerails.client.view;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.NoSuchElementException;

import javax.swing.Action;
import javax.swing.JPanel;

import jfreerails.client.renderer.RenderersRoot;
import jfreerails.controller.ModelRoot;
import jfreerails.world.cargo.CargoType;
import jfreerails.world.cargo.ImmutableCargoBundle;
import jfreerails.world.common.FreerailsSerializable;
import jfreerails.world.common.ImPoint;
import jfreerails.world.player.FreerailsPrincipal;
import jfreerails.world.station.StationModel;
import jfreerails.world.top.KEY;
import jfreerails.world.top.NonNullElements;
import jfreerails.world.top.ReadOnlyWorld;
import jfreerails.world.top.SKEY;
import jfreerails.world.top.WorldIterator;
import jfreerails.world.top.WorldListListener;
import jfreerails.world.track.FreerailsTile;
import jfreerails.world.train.WagonType;

import org.apache.log4j.Logger;

/**
 * This JPanel displays the supply and demand at a station.
 * 
 * @author Luke
 */
public class StationInfoJPanel extends JPanel implements View,
        WorldListListener {

    private static final Logger logger = Logger
            .getLogger(StationInfoJPanel.class.getName());

    private static final long serialVersionUID = 4050759377680150585L;

    private ReadOnlyWorld w;

    private ModelRoot modelRoot;

    private WorldIterator wi;

    /**
     * The index of the cargoBundle associated with this station.
     */
    private int cargoBundleIndex;

    public StationInfoJPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        nextStation = new javax.swing.JButton();
        previousStation = new javax.swing.JButton();
        close = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        setMinimumSize(new java.awt.Dimension(250, 177));
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel1
                .setText("<html>\n<h4 align=\"center\">Supply and Demand at stationName</h4>\n<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"2\">\n  <tr>\n    <td>&nbsp;</td>\n    <td>Will pay<br>for</td>\n    <td>Supplies<br>(cars per year)</td>\n    <td>Waiting for pickup<br>(car loads)</td>\n  </tr>\n   <tr>\n    <td>Mail</td>\n    <td>Yes</td>\n    <td>&nbsp;</td>\n    <td>&nbsp;</td>\n  </tr>\n  <tr>\n    <td>Passengers</td>\n    <td>No</td>\n    <td>3</td>\n    <td>2.5</td>\n  </tr>\n \n</table>\n\n</html>");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 4, 8);
        add(jLabel1, gridBagConstraints);

        nextStation.setText("next ->");
        nextStation.setMargin(new java.awt.Insets(0, 0, 0, 0));
        nextStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextStationActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(nextStation, gridBagConstraints);

        previousStation.setText("<- previous");
        previousStation.setMargin(new java.awt.Insets(0, 0, 0, 0));
        previousStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousStationActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(previousStation, gridBagConstraints);

        close.setText("close");
        close.setMargin(new java.awt.Insets(0, 0, 0, 0));
        close.setMaximumSize(new java.awt.Dimension(65, 22));
        close.setMinimumSize(new java.awt.Dimension(65, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(close, gridBagConstraints);

    }// GEN-END:initComponents

    private void previousStationActionPerformed(java.awt.event.ActionEvent evt) {
        // GEN-FIRST:event_previousStationActionPerformed
        // Add your handling code here:
        if (wi.previous()) {
            ImPoint p = new ImPoint(((StationModel) wi.getElement())
                    .getStationX(), ((StationModel) wi.getElement())
                    .getStationY());
            this.modelRoot.setProperty(ModelRoot.Property.CURSOR_POSITION, p);

            display();
        } else {
            throw new IllegalStateException();
        }
    } // GEN-LAST:event_previousStationActionPerformed

    private void nextStationActionPerformed(java.awt.event.ActionEvent evt) {
        // GEN-FIRST:event_nextStationActionPerformed
        // Add your handling code here:
        if (wi.next()) {
            ImPoint p = new ImPoint(((StationModel) wi.getElement())
                    .getStationX(), ((StationModel) wi.getElement())
                    .getStationY());
            this.modelRoot.setProperty(ModelRoot.Property.CURSOR_POSITION, p);
            display();
        } else {
            throw new IllegalStateException();
        }

    } // GEN-LAST:event_nextStationActionPerformed

    public void setup(ModelRoot mr, RenderersRoot vl, Action al) {
        this.wi = new NonNullElements(KEY.STATIONS, mr.getWorld(), mr
                .getPrincipal());
        addComponentListener(componentListener);
        this.w = mr.getWorld();
        this.modelRoot = mr;
        this.close.addActionListener(al);
    }

    public void setStation(int stationNumber) {
        this.wi.gotoIndex(stationNumber);
        display();
    }

    private void display() {

        if (wi.getRowID() > 0) {
            this.previousStation.setEnabled(true);
        } else {
            this.previousStation.setEnabled(false);
        }

        if (wi.getRowID() < (wi.size() - 1)) {
            this.nextStation.setEnabled(true);
        } else {
            this.nextStation.setEnabled(false);
        }

        int stationNumber = wi.getIndex();
        String label;
        if (stationNumber != WorldIterator.BEFORE_FIRST) {
            StationModel station = (StationModel) w.get(modelRoot
                    .getPrincipal(), KEY.STATIONS, stationNumber);
            FreerailsTile tile = (FreerailsTile) w
                    .getTile(station.x, station.y);
            String stationTypeName = tile.getTrackPiece().getTrackRule()
                    .getTypeName();
            cargoBundleIndex = station.getCargoBundleID();
            ImmutableCargoBundle cargoWaiting = (ImmutableCargoBundle) w.get(
                    modelRoot.getPrincipal(), KEY.CARGO_BUNDLES, station
                            .getCargoBundleID());
            
            StringBuilder table1 = new StringBuilder ();
            
            table1.append("<html>");
            
            table1.append("<h2 align=\"center\">");
            table1.append(station.getStationName());
            table1.append(" (");
            table1.append(stationTypeName);
            table1.append(")</h2>");
            
            table1.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"3\"><tr><td>&nbsp;</td>\n    <td>Demand</td>\n    <td>Supplies<br/>(cars/year)</td><td>Ready<br />(loads)</td>  </tr>");
            
            for (int i = 0; i < w.size(SKEY.CARGO_TYPES); i++) {

                // get the values
                CargoType cargoType = (CargoType) w.get(SKEY.CARGO_TYPES, i);
                String demanded = (station.getDemand().isCargoDemanded(i) ? "Yes"
                        : "No");
                
                int amountSupplied = station.getSupply().getSupply(i);
                boolean isSupplied = (amountSupplied > 0);
                String supply = isSupplied ? String
                        .valueOf(amountSupplied
                                / WagonType.UNITS_OF_CARGO_PER_WAGON)
                        : "&nbsp;";
                        
                int amountWaiting = cargoWaiting.getAmount(i);
                String waiting = (amountWaiting > 0) ? String
                        .valueOf(amountWaiting
                                / WagonType.UNITS_OF_CARGO_PER_WAGON)
                        : "&nbsp;";

                // build the html
                if (station.getDemand().isCargoDemanded(i) || isSupplied) {
                    table1.append("<tr>");
                    table1.append("<td>").append(cargoType.getDisplayName()).append("</td>");
                    table1.append("<td align=center>").append(demanded).append("</td>");
                    table1.append("<td align=center>").append(supply).append("</td>");
                    table1.append("<td align=center>").append(waiting).append("</td>");
                    table1.append("</tr>");
                }
                
            }
            table1.append("</table>");
            table1.append("</html>");
            label = table1.toString();
        } else {
            cargoBundleIndex = WorldIterator.BEFORE_FIRST;
            label = "<html><h2 align=\"center\">No Station "
                    + "Selected</h2></html>";
        }
        jLabel1.setText(label);
        this.repaint();
    }

    private final ComponentAdapter componentListener = new ComponentAdapter() {
        @Override
        public void componentHidden(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {

            int i = wi.getIndex();
            wi.reset();
            if (i != WorldIterator.BEFORE_FIRST) {
                try {
                    wi.gotoIndex(i);
                } catch (NoSuchElementException ex) {
                    logger
                            .info("Exception ignored in StationInfoJPanel (NoSuchElement).");
                    return; // ignore silently
                }
            }
            display();
        }
    };

    private FreerailsSerializable lastCargoBundle = null;

    @Override
    protected void paintComponent(Graphics g) {
        /* We need to update if the cargo bundle has changed. */
        FreerailsPrincipal playerPrincipal = this.modelRoot.getPrincipal();

        /*
         * Avoid a array out of bounds exception when there are no stations and
         * the stations tab is visible.
         */
        if (w.boundsContain(playerPrincipal, KEY.CARGO_BUNDLES,
                cargoBundleIndex)) {
            FreerailsSerializable currentCargoBundle = w.get(playerPrincipal,
                    KEY.CARGO_BUNDLES, this.cargoBundleIndex);
            if (lastCargoBundle != currentCargoBundle) {
                this.display();
                lastCargoBundle = currentCargoBundle;
            }
        }
        super.paintComponent(g);
    }

    private void reactToUpdate(KEY key, int changedIndex, boolean isAddition) {
        if (!isVisible()) {
            return;
        }

        int currentIndex = wi.getIndex();
        if (key == KEY.CARGO_BUNDLES) {
            if (changedIndex == cargoBundleIndex) {
                /* update our cargo bundle */
                display();
                return;
            }
        } else if (key == KEY.STATIONS) {
            wi.reset();
            if (currentIndex != WorldIterator.BEFORE_FIRST) {
                if (currentIndex < wi.size()) {
                    wi.gotoIndex(currentIndex);
                } else {
                    currentIndex = WorldIterator.BEFORE_FIRST;
                }
            }
            if (isAddition && wi.getIndex() == WorldIterator.BEFORE_FIRST) {
                if (wi.next()) {
                    display();
                }
            }

            if (currentIndex == changedIndex
                    || currentIndex == WorldIterator.BEFORE_FIRST) {
                display();
            }
        }
        return;

    }

    public void listUpdated(KEY key, int index, FreerailsPrincipal principal) {
        if (modelRoot.getPrincipal().equals(principal))
            reactToUpdate(key, index, false);
    }

    public void itemAdded(KEY key, int index, FreerailsPrincipal principal) {
        if (modelRoot.getPrincipal().equals(principal))
            reactToUpdate(key, index, true);
    }

    public void itemRemoved(KEY key, int index, FreerailsPrincipal principal) {
        if (modelRoot.getPrincipal().equals(principal))
            reactToUpdate(key, index, false);
    }

    void removeCloseButton() {
        this.remove(close);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JButton nextStation;

    private javax.swing.JButton previousStation;
    // End of variables declaration//GEN-END:variables

}
