/*
 * SideOnTrainViewJPanel.java
 *
 * Created on 18 April 2003, 16:59
 */

package jfreerails.client.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import jfreerails.client.renderer.SideOnTrainTrainViewImages;
import jfreerails.client.renderer.ViewLists;
import jfreerails.world.top.World;
/**
 *
 * @author  Luke
 */
public class SideOnTrainViewJPanel extends javax.swing.JPanel {
    
    int engineType=0;
    
    boolean showEngine = false;
    
    private ArrayList wagons = new ArrayList();
    
    private ViewLists viewLists;
    
    private World w;
    
    public int[] getWagons(){
    	int[] wagonsArray = new int[wagons.size()];
    	for(int i=0; i<wagons.size() ; i++){
    		Integer integer = (Integer)wagons.get(i);
			wagonsArray[i]=integer.intValue();
    	}    	
    	return wagonsArray;
    }
    
    public void addWagon(int i){
        Integer wagon = new Integer(i);
        wagons.add(wagon);
    }
    
    public void removeAllWagons(){
        this.wagons.clear();
    }
    
    /** Creates new form SideOnTrainViewJPanel */
    public SideOnTrainViewJPanel() {
        initComponents();
    }
    
    public void setup(World world, ViewLists vl, ActionListener submitButtonCallBack) {
        this.viewLists = vl;
        this.w = world;        
    }
    
    public void paint(Graphics g) {
        
        int x = 0;
        
        final int wagonHeight = SideOnTrainTrainViewImages.HEIGHT_25_PIXELS;
        
        int y = 0;
        
        Image image;
        
        //paint the wagons
        for (int i = 0; i < this.wagons.size(); i++) {
            Integer type = (Integer)wagons.get(i);
            image = viewLists.getSideOnTrainTrainViewImages().getWagonImage(type.intValue(), wagonHeight);
            g.drawImage(image, x, y, null);
            x += image.getWidth(null);
        }
        
        if(showEngine){
            //paint the engine
            image = this.viewLists.getSideOnTrainTrainViewImages().getEngineImage(this.engineType, wagonHeight);
            g.drawImage(image, x, y, null);
            x += image.getWidth(null);
        }
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents

        setLayout(new java.awt.BorderLayout());

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3));
        setOpaque(false);
    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
