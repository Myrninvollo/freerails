/*
 * TrainOrderJPanel.java
 *
 * Created on 23 August 2003, 17:25
 */

package jfreerails.client.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import jfreerails.client.common.ModelRoot;
import jfreerails.client.renderer.ViewLists;
import jfreerails.world.player.FreerailsPrincipal;
import jfreerails.world.station.StationModel;
import jfreerails.world.top.KEY;
/**
 * ListCellRenderer that displays a train order.
 * @author  Luke Lindsay
 */
public class TrainOrderJPanel extends javax.swing.JPanel implements View, ListCellRenderer{
    
    private jfreerails.world.top.ReadOnlyWorld w;
    private FreerailsPrincipal principal;
    
    private final ImageIcon gotoNow = new ImageIcon(TrainOrderJPanel.class.getResource("/jfreerails/client/graphics/selected_arrow.png"));
    private final ImageIcon gotoAfterPriorityOrders = new ImageIcon(TrainOrderJPanel.class.getResource("/jfreerails/client/graphics/deselected_arrow.png"));
    private final ImageIcon dontGoto = null;
    
    private final Color backgoundColor = (java.awt.Color) javax.swing.UIManager.getDefaults().get("List.background");
    
    private final Color selectedColor = (java.awt.Color) javax.swing.UIManager.getDefaults().get("List.selectionBackground");
    
    private final Color selectedColorNotFocused = Color.LIGHT_GRAY;

    
    
    public TrainOrderJPanel() {
        initComponents();
        this.setBackground(backgoundColor);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        gotoIcon = new javax.swing.JLabel();
        consistChangeJPanel = new TrainListCellRenderer();
        noChangeJLabel = new javax.swing.JLabel();
        stationNameJLabel = new javax.swing.JLabel();
        ordersJLabel = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        gotoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jfreerails/client/graphics/selected_arrow.png")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(gotoIcon, gridBagConstraints);

        consistChangeJPanel.setLayout(new java.awt.GridBagLayout());

        noChangeJLabel.setText("No Change");
        consistChangeJPanel.add(noChangeJLabel, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        add(consistChangeJPanel, gridBagConstraints);

        stationNameJLabel.setText("Some Station");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(stationNameJLabel, gridBagConstraints);

        ordersJLabel.setText("wait until full / don't wait");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 5);
        add(ordersJLabel, gridBagConstraints);

    }//GEN-END:initComponents
    
    public void setup(ModelRoot mr,  ViewLists vl, ActionListener submitButtonCallBack) {
        this.w = mr.getWorld();
        TrainListCellRenderer trainViewJPanel = (TrainListCellRenderer)consistChangeJPanel;
        trainViewJPanel.setHeight(15);
        trainViewJPanel.setup(mr, vl, null);
        this.principal = mr.getPrincipal();
        
    }
    
    public java.awt.Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        TrainOrdersListModel.TrainOrdersListElement trainOrders = (TrainOrdersListModel.TrainOrdersListElement)value;
        
        //Set station name
        int stationNumber = trainOrders.order.m_station;
        StationModel station = (StationModel)w.get(KEY.STATIONS, stationNumber, principal);
        String stationName = station.getStationName();
        this.stationNameJLabel.setText(stationName);
        
        //Set wait until full
        String waitUntilFull  = trainOrders.order.waitUntilFull ? "Wait until full" : "";
        this.ordersJLabel.setText(waitUntilFull);
        
        //Set selected        
        if (isSelected) {
    		if(list.isFocusOwner()){
    			setBackground(selectedColor);
    		}else{
    			setBackground(selectedColorNotFocused);
    		}
    	} else {
    		setBackground(backgoundColor);
    	}
        
        
        //Set goto status.
        switch (trainOrders.gotoStatus){
            case TrainOrdersListModel.DONT_GOTO:
                this.gotoIcon.setIcon(this.dontGoto);
                break;
            case TrainOrdersListModel.GOTO_AFTER_PRIORITY_ORDERS:
                this.gotoIcon.setIcon(this.gotoAfterPriorityOrders);
                break;
            case TrainOrdersListModel.GOTO_NOW:
                this.gotoIcon.setIcon(this.gotoNow);
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(trainOrders.gotoStatus));
        }
        this.gotoIcon.setPreferredSize(new Dimension(20, 20));
        
        //Set consist
        TrainListCellRenderer trainViewJPanel = (TrainListCellRenderer)consistChangeJPanel;
        trainViewJPanel.display(trainOrders.trainNumber, index);
        
        //Show priority orders.
        if(trainOrders.isPriorityOrder){
            //Write the station name in upper case
            String s = this.stationNameJLabel.getText();
            this.stationNameJLabel.setText(s + " (Priority Orders)");
        }
        
        //Check for 'No change'
        if(null == trainOrders.order.consist){
            if(trainOrders.order.autoConsist){
                 this.noChangeJLabel.setText("Select wagons automatically");
            }else{
                this.noChangeJLabel.setText("No Change");
            }
        }else{
            this.noChangeJLabel.setText(null);
        }
        
        //Set the section title
        //this.sectionTitleJLabel.setText("trainOrders.sectionTitle");
        
        return this;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JPanel consistChangeJPanel;
    javax.swing.JLabel gotoIcon;
    javax.swing.JLabel noChangeJLabel;
    javax.swing.JLabel ordersJLabel;
    javax.swing.JLabel stationNameJLabel;
    // End of variables declaration//GEN-END:variables
    
}
