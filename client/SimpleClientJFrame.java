/*
 * SimpleClientJFrame.java
 *
 * Created on 29 October 2001, 01:22
 */

package jfreerails.client;

/**
 *
 * @author  Luke Lindsay
 * @version 
 */
public class SimpleClientJFrame extends jfreerails.client.ClientJFrame {

    /** Creates new SimpleClientJFrame */
    public SimpleClientJFrame(javax.swing.JComponent mapView, BuildMenu buildMenu) {
        super(mapView, buildMenu);
        System.out.println("\nCreating client window");
    }

    /**
     * Exit the form.
     *
     * @param  evt  Description of the Parameter
     */
    protected void exitForm(java.awt.event.WindowEvent evt) {
    	System.exit(0);
        
    }
    
}
