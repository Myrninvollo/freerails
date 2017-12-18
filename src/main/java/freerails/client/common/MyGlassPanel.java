/*
 * MyGlassPanel.java
 *
 * Created on 21 December 2002, 22:19
 */
package freerails.client.common;

/**
 * A transparent JPanel that catches key presses and mouse clicks.
 *
 * @author lindsal8
 */
public class MyGlassPanel extends javax.swing.JPanel {
    private static final long serialVersionUID = 3976735856986239795L;

    /**
     *
     */
    public MyGlassPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    private void initComponents() { // GEN-BEGIN:initComponents
        javax.swing.JComponent contentPanel = new javax.swing.JPanel();
        setLayout(new java.awt.GridBagLayout());

        java.awt.GridBagConstraints gridBagConstraints1;
        setOpaque(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        contentPanel.setPreferredSize(new java.awt.Dimension(60, 40));
        contentPanel.setMinimumSize(new java.awt.Dimension(60, 40));
        contentPanel.setBackground(java.awt.Color.red);
        contentPanel.setMaximumSize(new java.awt.Dimension(60, 40));

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 1;
        add(contentPanel, gridBagConstraints1);
    }

    // GEN-END:initComponents
    private void formMouseMoved(java.awt.event.MouseEvent evt) { // GEN-FIRST:event_formMouseMoved
        // Add your handling code here:
    }

    // GEN-LAST:event_formMouseMoved
    private void formMousePressed(java.awt.event.MouseEvent evt) { // GEN-FIRST:event_formMousePressed
        // Add your handling code here:
    }

    // GEN-LAST:event_formMousePressed
    private void formKeyPressed(java.awt.event.KeyEvent evt) { // GEN-FIRST:event_formKeyPressed
        // Add your handling code here:
    }

    // End of variables declaration//GEN-END:variables
}