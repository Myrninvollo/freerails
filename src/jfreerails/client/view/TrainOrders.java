/*
 * TrainOrders.java
 *
 * Created on 30 March 2003, 02:00
 */

package jfreerails.client.view;
import java.awt.event.KeyEvent;
import jfreerails.world.top.*;
import jfreerails.world.station.*;
import jfreerails.world.train.*;
import javax.swing.*;
import jfreerails.client.renderer.ViewLists;

/**
 *
 * @author  Luke
 */
public class TrainOrders extends javax.swing.JPanel {
    
    private int[] consist = new int[0];
    
    private java.awt.Color oldBackground;
    
    private int field1;
    
    private World w;
    
    int orderNo=0;
    
    private int trainNumber=0;
    
    private int stationNumber=0;
    
    /** Creates new form TrainOrders */
    public TrainOrders() {
        initComponents();
    }
    
    public void setup(World world, ViewLists vl){
        w=world;
        setupStationsInPopup();
        setupWagonsPopup();
        sideOnTrainViewJPanel1.setup(w, vl, null);
    }
    
    public void display(int trainNumber, int orderNumber){
        this.trainNumber = trainNumber;
        this.orderNo = orderNumber;
        TrainModel train = (TrainModel)w.get(KEY.TRAINS, trainNumber);
        Schedule schedule = train.getSchedule();
        TrainOrdersModel orders = schedule.getOrder(orderNumber);
        stationNumber = orders.getStationNumber();
        
        if(0==orderNumber){
            this.orderNumber.setText("P");  //order 0 is priority orders.
        }else{
            String s = orderNumber+".";
            this.orderNumber.setText(s);
        }
        
        this.setGotoStation(false);
        
        this.sideOnTrainViewJPanel1.removeAllWagons();
        int [] consist = orders.getConsist();
        
        for(int i = 0 ; i<consist.length ; i++){
            this.sideOnTrainViewJPanel1.addWagon(consist[i]);
        }
        StationModel station = (StationModel)w.get(KEY.STATIONS, orders.getStationNumber());
        this.stationName.setText(station.getStationName());
        
        setupStationsInPopup();
        setupWagonsPopup();
    }
    
    public void setOrderID(String s){
        orderNumber.setText(s);
    }
    
    /** This sets the list of stations in the select station popup.
     *It needs to be called before each time the popup is shown since
     *stations may have been added or removed.
     */
    private void setupStationsInPopup(){
        
        changeStation.removeAll();  //Remove existing menu items.
        NonNullElements stations = new NonNullElements(KEY.STATIONS, w);
        while(stations.next()){
            final StationModel stationModel = (StationModel)stations.getElement();
            final int stationIndex = stations.getIndex();
            JMenuItem stationMenuItem = new JMenuItem();
            
            stationMenuItem.setText(stationModel.getStationName());
            stationMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    System.out.println("Station set to "+stationModel.getStationName());
                    setStationNameText(stationModel.getStationName());
                    setStationNumber(stationIndex);
                }
            });
            changeStation.add(stationMenuItem);
            
        }
        
        JSeparator jSeparator1 = new javax.swing.JSeparator();
        changeStation.add(jSeparator1);
        
        JMenuItem removeStation = new JMenuItem();
        removeStation.setText("Remove Station");
        changeStation.add(removeStation);
        
    }
    
    private void setupWagonsPopup(){
        changeConsist.removeAll();  //Remove existing menu items.
        
        NonNullElements wagonTypes = new NonNullElements(KEY.WAGON_TYPES, w);
        while(wagonTypes.next()){
            final WagonType wagonType = (WagonType)wagonTypes.getElement();
            JMenuItem wagonMenuItem = new JMenuItem();
            final int wagonTypeNumber = wagonTypes.getIndex();
            wagonMenuItem.setText(wagonType.getName());
            wagonMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    System.out.println("Add "+wagonType.getName());
                    addWagon(wagonTypeNumber);
                }
            });
            changeConsist.add(wagonMenuItem);
        }
        JSeparator jSeparator1 = new javax.swing.JSeparator();
        changeConsist.add(jSeparator1);
        JMenuItem removeAll = new JMenuItem();
        removeAll.setText("Remove All");
        removeAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Remove all wagons");
                removeAllWagons();
            }
        });
        changeConsist.add(removeAll);
    }
    
    private void setStationNameText(String s){
        this.stationName.setText(s);
    }
    
    private void addWagon(int i){
        this.sideOnTrainViewJPanel1.addWagon( i);
        this.repaint();
    }
    
    private void removeAllWagons(){
        this.sideOnTrainViewJPanel1.removeAllWagons();
        this.repaint();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        changeWaitUntilFullOrders = new javax.swing.JPopupMenu();
        wait = new javax.swing.JMenuItem();
        dontWait = new javax.swing.JMenuItem();
        changeStation = new javax.swing.JPopupMenu();
        changeConsist = new javax.swing.JPopupMenu();
        orderNumber = new javax.swing.JLabel();
        waitUntilFullOrders = new javax.swing.JLabel();
        stationName = new javax.swing.JLabel();
        sideOnTrainViewJPanel1 = new jfreerails.client.view.SideOnTrainViewJPanel();

        wait.setText("Wait Unitl Full");
        wait.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waitActionPerformed(evt);
            }
        });

        changeWaitUntilFullOrders.add(wait);
        dontWait.setText("Don't Wait");
        dontWait.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dontWaitActionPerformed(evt);
            }
        });

        changeWaitUntilFullOrders.add(dontWait);

        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        setPreferredSize(new java.awt.Dimension(500, 26));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        orderNumber.setText("1.");
        orderNumber.setPreferredSize(new java.awt.Dimension(16, 16));
        add(orderNumber);

        waitUntilFullOrders.setText("W");
        waitUntilFullOrders.setToolTipText("Wait Until Full");
        waitUntilFullOrders.setPreferredSize(new java.awt.Dimension(16, 16));
        waitUntilFullOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                waitUntilFullOrdersMouseClicked(evt);
            }
        });

        add(waitUntilFullOrders);

        stationName.setForeground(new java.awt.Color(102, 102, 102));
        stationName.setText("Bristol Temple Meades");
        stationName.setPreferredSize(new java.awt.Dimension(200, 16));
        stationName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stationNameMouseClicked(evt);
            }
        });

        add(stationName);

        sideOnTrainViewJPanel1.setPreferredSize(new java.awt.Dimension(240, 25));
        sideOnTrainViewJPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sideOnTrainViewJPanel1MouseClicked(evt);
            }
        });

        add(sideOnTrainViewJPanel1);

    }//GEN-END:initComponents
    
    private void sideOnTrainViewJPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideOnTrainViewJPanel1MouseClicked
        // Add your handling code here:
        setupStationsInPopup();
        this.changeConsist.show(evt.getComponent(), evt.getX(), evt.getY());
        
    }//GEN-LAST:event_sideOnTrainViewJPanel1MouseClicked
    
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // Add your handling code here:
        switch (evt.getKeyCode()){
            case KeyEvent.VK_O:
                this.changeWaitUntilFullOrders.show(this.waitUntilFullOrders, this.waitUntilFullOrders.getX(), this.waitUntilFullOrders.getY());
                break;
            case KeyEvent.VK_C:
                this.changeConsist.show(this.sideOnTrainViewJPanel1, 0, this.sideOnTrainViewJPanel1.getY());
                break;
            case KeyEvent.VK_S:
                this.changeStation.show(this.stationName, this.stationName.getX(), this.stationName.getY());
                break;
            case KeyEvent.VK_UP:
                System.out.println("Up pressed");
                this.getParent().getParent().dispatchEvent(evt);
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Down pressed");
                this.getParent().getParent().dispatchEvent(evt);
                break;
        }
        
        
    }//GEN-LAST:event_formKeyPressed
    
    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // Add your handling code here:
        this.requestFocus();
    }//GEN-LAST:event_formMouseEntered
    
    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        // Add your handling code here:
        this.setBackground( this.oldBackground );
    }//GEN-LAST:event_formFocusLost
    
    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // Add your handling code here:
        this.oldBackground = this.getBackground();
        this.setBackground(java.awt.Color.yellow);
    }//GEN-LAST:event_formFocusGained
    
    private void dontWaitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dontWaitActionPerformed
        // Add your handling code here:
        this.waitUntilFullOrders.setText("-");
    }//GEN-LAST:event_dontWaitActionPerformed
    
    private void waitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waitActionPerformed
        // Add your handling code here:
        this.waitUntilFullOrders.setText("W");
    }//GEN-LAST:event_waitActionPerformed
    
    private void stationNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stationNameMouseClicked
        // Add your handling code here:
        System.out.println("changeStation");
        this.changeStation.show(evt.getComponent(), evt.getX(), evt.getY());
    }//GEN-LAST:event_stationNameMouseClicked
    
    private void waitUntilFullOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waitUntilFullOrdersMouseClicked
        // Add your handling code here:
        this.changeWaitUntilFullOrders.show(evt.getComponent(), evt.getX(), evt.getY());
    }//GEN-LAST:event_waitUntilFullOrdersMouseClicked
    
    public TrainOrdersModel getNewOrders(){
        TrainOrdersModel newOrders = new TrainOrdersModel(this.stationNumber, this.sideOnTrainViewJPanel1.getWagons(), false);
        return newOrders;
    }
    
    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }
    
    /** Set whether to indicate that the train is going to this station. */
    public void setGotoStation(boolean b){
        java.awt.Color c;
        if(b){
            c= new java.awt.Color(0, 0, 0);
        }else{
            c = new java.awt.Color(102, 102, 102);
        }
        this.stationName.setForeground(c);
        this.waitUntilFullOrders.setForeground(c);
        this.orderNumber.setForeground(c);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu changeConsist;
    private javax.swing.JPopupMenu changeStation;
    private javax.swing.JLabel stationName;
    private javax.swing.JLabel waitUntilFullOrders;
    private javax.swing.JMenuItem dontWait;
    private jfreerails.client.view.SideOnTrainViewJPanel sideOnTrainViewJPanel1;
    private javax.swing.JPopupMenu changeWaitUntilFullOrders;
    private javax.swing.JLabel orderNumber;
    private javax.swing.JMenuItem wait;
    // End of variables declaration//GEN-END:variables
    
    
    
}
