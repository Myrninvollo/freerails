/*
 * SelectWagonsJPanel.java
 *
 * Created on 29 December 2002, 16:54
 */

package jfreerails.client.view;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import jfreerails.client.renderer.TrainImages;
import jfreerails.client.renderer.ViewLists;
import jfreerails.world.cargo.CargoType;
import jfreerails.world.top.KEY;
import jfreerails.world.top.World;
import jfreerails.world.train.TrainModel;
/**
 * This JPanel lets the user add wagons to a train.
 * 
 * @author  lindsal8
 *
 */
public class SelectWagonsJPanel extends javax.swing.JPanel implements View {

	private GraphicsConfiguration defaultConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

	Image stationView;

	private ArrayList wagons = new ArrayList();

	private TrainModel train;
	

	private int engineType = 0;

	private ViewLists viewLists;

	private World w;

	/** Creates new form SelectWagonsJPanel */
	public SelectWagonsJPanel() {
		initComponents();
		URL url = SelectWagonsJPanel.class.getResource("/jfreerails/data/station.gif");
		Image tempImage = (new javax.swing.ImageIcon(url)).getImage();
		
		stationView = defaultConfiguration.createCompatibleImage(tempImage.getWidth(null), tempImage.getHeight(null), Transparency.BITMASK);

		Graphics g = stationView.getGraphics();

		g.drawImage(tempImage, 0, 0, null);			
	}
	
	public void resetSelectedWagons(){
		this.wagons.clear();
	}

	public void setTrain(TrainModel t) {
		train = t;
	}

	public TrainModel getTrain() {
		return train;
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the FormEditor.
	 */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        wagonTypesJList = new javax.swing.JList();
        okjButton = new javax.swing.JButton();
        clearjButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        setBackground(new java.awt.Color(0, 255, 51));
        setMinimumSize(new java.awt.Dimension(640, 400));
        setPreferredSize(new java.awt.Dimension(620, 380));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(100, 100));
        jPanel1.setMinimumSize(new java.awt.Dimension(200, 250));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 1000));
        wagonTypesJList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                wagonTypesJListKeyTyped(evt);
            }
        });

        wagonTypesJList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wagonTypesJListMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(wagonTypesJList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        okjButton.setText("OK");
        okjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonAction(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(okjButton, gridBagConstraints);

        clearjButton.setText("Clear");
        clearjButton.setActionCommand("clear");
        clearjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(clearjButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = -720;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(40, 430, 0, 0);
        add(jPanel1, gridBagConstraints);

    }//GEN-END:initComponents

	private void okButtonAction(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_okButtonAction
		// Add your handling code here:
		

	} //GEN-LAST:event_okButtonAction

	private void wagonTypesJListMouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_wagonTypesJListMouseClicked
		// Add your handling code here:
		addwagon();
	} //GEN-LAST:event_wagonTypesJListMouseClicked

	private void wagonTypesJListKeyTyped(java.awt.event.KeyEvent evt) { //GEN-FIRST:event_wagonTypesJListKeyTyped
		// Add your handling code here:
		if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
			addwagon();
		} else {
			System.out.println(evt.getKeyCode());
		}

	} //GEN-LAST:event_wagonTypesJListKeyTyped

	//Adds the wagon selected in the list to the train consist.
	private void addwagon() {
		int type = wagonTypesJList.getSelectedIndex();	
		wagons.add(new Integer(type));
		this.repaint();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButton1ActionPerformed
		// Add your handling code here:
		wagons.clear();
		this.repaint();
	} //GEN-LAST:event_jButton1ActionPerformed

	public void paint(Graphics g) {
		//paint the background
		g.drawImage(this.stationView, 0, 0, null);

		int x = 0;
		
		int y = 330;

		

		final int SCALED_IMAGE_HEIGHT = 50;
		//paint the wagons
		for (int i = this.wagons.size()-1; i >= 0; i--) {  //Count down so we paint the wagon at the end of the train first. 
			
			Integer type = (Integer)wagons.get(i);
			Image image = viewLists.getTrainImages().getSideOnWagonImage(type.intValue());
			int scaledWidth = image.getWidth(null) * SCALED_IMAGE_HEIGHT / image.getHeight(null);
			
			g.drawImage(image, x, y, scaledWidth, SCALED_IMAGE_HEIGHT, null);
			x += scaledWidth;			
		}

		//paint the engine		
		Image image = viewLists.getTrainImages().getSideOnEngineImage(this.engineType);
		int scaledWidth = (image.getWidth(null) * SCALED_IMAGE_HEIGHT) / image.getHeight(null);			
		g.drawImage(image, x, y, scaledWidth, SCALED_IMAGE_HEIGHT, null);					
		this.paintChildren(g);
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okjButton;
    private javax.swing.JButton clearjButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JList wagonTypesJList;
    // End of variables declaration//GEN-END:variables
	final private class WagonCellRenderer implements ListCellRenderer {

		Image tempImage;
		JLabel label;
		TrainImages trainImages;

		public WagonCellRenderer(TrainImages s) {
			trainImages = s;

			label = new JLabel(); //"text", new ImageIcon( tempImage ), SwingConstants.LEFT );

		}

		public Component getListCellRendererComponent(JList list, Object value, /* value to display*/
		int index, /* cell index*/
		boolean isSelected, /* is the cell selected*/
		boolean cellHasFocus) /* the list and the cell have the focus*/ {
			CargoType cargoType = (CargoType) value;
			label.setFont(new java.awt.Font("Dialog", 0, 12));
			String text = "<html><body>" + (isSelected ? "<strong>" : "") + cargoType.getDisplayName() + (isSelected ? "</strong>" : "&nbsp;&nbsp;&nbsp;&nbsp;"/*padding to stop word wrap due to greater wodth of strong font*/) + "</body></html>";
			label.setText(text);
			Image image = trainImages.getSideOnWagonImage(index);
			int height = image.getHeight(null);
			int width = image.getWidth(null);
			int scale = height/10;
			ImageIcon icon = new ImageIcon(image.getScaledInstance(width/scale, height/scale, Image.SCALE_FAST));			
			label.setIcon(icon);
			return label;
		}
	}

	public void setup(World world, ViewLists vl, ActionListener submitButtonCallBack) {
		this.viewLists = vl;
		this.w = world;
		this.wagonTypesJList.setModel(new World2ListModelAdapter(w, KEY.CARGO_TYPES) );
		this.wagonTypesJList.setCellRenderer(new WagonCellRenderer(viewLists.getTrainImages()));
		this.okjButton.addActionListener(submitButtonCallBack);
	}
	public int getEngineType() {
		return engineType;
	}

	public int[] getWagons(){
		int [] wagonsArray = new int[wagons.size()];
		for(int i=0; i<wagons.size(); i++){
			Integer type = (Integer)wagons.get(i);
			wagonsArray[i]=type.intValue();
		}
		return wagonsArray;		
	}

	public void setEngineType(int engineType) {
		this.engineType = engineType;
	}

}
