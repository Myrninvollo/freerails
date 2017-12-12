/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package experimental;

import java.awt.Graphics;

import jfreerails.world.track.PositionOnTrack;
import jfreerails.world.track.TrackSection;

/**
 * @version 	1.0
 * @author
 */


public class TrackSectionView {
	
	public TrackSectionView(){
	}
	
	public void paint(Graphics g, TrackSection ts){
		int length=ts.getLength();
		int x1,x2,y1,y2;
		
		for (int i=0;i<length;i++){					
			PositionOnTrack positionOnTrack	= ts.getLocation(null, i);
			x2=positionOnTrack.getX();
			y2=positionOnTrack.getY();
			g.drawLine(x2,y2,x2,y2);			
		}
	}	

}
