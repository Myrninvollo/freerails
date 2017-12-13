/*
 * TrainDialogueJPanel.java
 *
 * Created on 24 August 2003, 17:13
 */

package jfreerails.client.view;

import java.awt.event.ActionListener;

import jfreerails.world.player.FreerailsPrincipal;
import jfreerails.world.top.KEY;
import jfreerails.world.top.NonNullElements;
import jfreerails.world.top.ReadOnlyWorld;
import jfreerails.world.top.WorldIterator;
import jfreerails.world.top.WorldListListener;

/**
 *
 * @author  Luke Lindsay
 */
public class TrainDialogueJPanel extends javax.swing.JPanel implements View, WorldListListener {
    
    private WorldIterator wi;
    private ReadOnlyWorld w;
    private FreerailsPrincipal principal;
    
    /** Creates new form TrainDialogueJPanel */
    public TrainDialogueJPanel() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        newTrainScheduleJPanel1 = new jfreerails.client.view.TrainScheduleJPanel();
        trainDetailsJPanel1 = new jfreerails.client.view.TrainDetailsJPanel();
        previousJButton = new javax.swing.JButton();
        nextJButton = new javax.swing.JButton();
        trainListJButton = new javax.swing.JButton();
        closeJButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(newTrainScheduleJPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(trainDetailsJPanel1, gridBagConstraints);

        previousJButton.setText("last");
        previousJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousJButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(previousJButton, gridBagConstraints);

        nextJButton.setText("next");
        nextJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextJButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(nextJButton, gridBagConstraints);

        trainListJButton.setText("Train list");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(trainListJButton, gridBagConstraints);

        closeJButton.setText("Close");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(closeJButton, gridBagConstraints);

    }//GEN-END:initComponents
    
    private void previousJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousJButtonActionPerformed
        // Add your handling code here:
        if(wi.previous()){
            display(wi.getIndex());
        }else{
            System.err.println("Couldn't get previous");
        }
    }//GEN-LAST:event_previousJButtonActionPerformed
    
    private void nextJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextJButtonActionPerformed
        // Add your handling code here:
        if(wi.next()){
            display(wi.getIndex());
        }else{
            System.err.println("Couldn't get next");
        }
    }//GEN-LAST:event_nextJButtonActionPerformed
    
    public void setup(ModelRoot mr, ActionListener al) {
        newTrainScheduleJPanel1.setup(mr, al);
        trainDetailsJPanel1.setup(mr, al);
        this.setCancelButtonActionListener(al);
        this.principal = mr.getPlayerPrincipal();
        this.w = mr.getWorld();
    }
    
    public void display(int trainNumber){
        wi = new NonNullElements(KEY.TRAINS, w, principal);
        wi.gotoIndex(trainNumber);
        if (wi.getRowNumber() > 0) {
            this.previousJButton.setEnabled(true);
        } else {
            this.previousJButton.setEnabled(false);
        }
        
        if (wi.getRowNumber() < (wi.size() - 1)) {
            this.nextJButton.setEnabled(true);
        } else {
            this.nextJButton.setEnabled(false);
        }
        
        newTrainScheduleJPanel1.display(trainNumber);
        trainDetailsJPanel1.displayTrain(trainNumber);
    }
    
    public void listUpdated(KEY key, int index, FreerailsPrincipal p) {
        newTrainScheduleJPanel1.listUpdated(key, index,p);
        trainDetailsJPanel1.listUpdated(key, index, p);
    }
    
    public void itemAdded(KEY key, int index, FreerailsPrincipal p) {
    }

    public void itemRemoved(KEY key, int index, FreerailsPrincipal p) {
    }
    
     
    void setTrainDetailsButtonActionListener(ActionListener l){
        ActionListener[] oldListeners = trainListJButton.getActionListeners();
        for(int i = 0; i < oldListeners.length; i++){
            trainListJButton.removeActionListener(oldListeners[i]);
        }
        this.trainListJButton.addActionListener(l);
    }
    
     /** Removes any existing ActionListener listeners from the cancel button, then 
     *adds the specifed one.
     */ 
    void setCancelButtonActionListener(ActionListener l){
        ActionListener[] oldListeners = closeJButton.getActionListeners();
        for(int i = 0; i < oldListeners.length; i++){
            closeJButton.removeActionListener(oldListeners[i]);
        }
        this.closeJButton.addActionListener(l);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeJButton;
    private jfreerails.client.view.TrainScheduleJPanel newTrainScheduleJPanel1;
    private javax.swing.JButton nextJButton;
    private javax.swing.JButton previousJButton;
    private jfreerails.client.view.TrainDetailsJPanel trainDetailsJPanel1;
    private javax.swing.JButton trainListJButton;
    // End of variables declaration//GEN-END:variables
    
}
