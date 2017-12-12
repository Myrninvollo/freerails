package jfreerails.client.view.map;

import java.awt.Dimension;
import java.awt.Rectangle;

public interface MapView extends MapViewLayer {
	
	float getScale();
		
	Dimension getMapSizeInPixels();

}
