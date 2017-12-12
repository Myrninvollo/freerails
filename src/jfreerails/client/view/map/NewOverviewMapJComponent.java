package jfreerails.client.view.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class NewOverviewMapJComponent extends JComponent {

	protected MapView mapView=new BlankMapView(0.4F);

	protected Rectangle mainMapVisRect;
	
	public NewOverviewMapJComponent(Rectangle r){		
		this.setPreferredSize(mapView.getMapSizeInPixels());
		mainMapVisRect=r;	
	}	
	public void setup(MapView mv){
		mapView=mv;					
		this.setPreferredSize(mapView.getMapSizeInPixels());
		this.setMinimumSize(this.getPreferredSize());
		this.setSize(this.getPreferredSize());
		if(null!=this.getParent()){									
			this.getParent().validate();
		}			
	}

	protected void paintComponent(java.awt.Graphics g) {
		java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
		java.awt.Rectangle r = this.getVisibleRect();
		mapView.paintRect(g2, r);
		g2.setColor(Color.WHITE);
		g2.drawRect(mainMapVisRect.x,mainMapVisRect.y,mainMapVisRect.width,mainMapVisRect.height);
	}
	
	public Dimension getPreferredSize(){
		return mapView.getMapSizeInPixels();
	}
	
}
