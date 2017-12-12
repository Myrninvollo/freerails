package jfreerails.client.view;

import java.awt.Dimension;

public interface MapView extends MapViewLayer {
	
	float getScale();
		
	Dimension getMapSizeInPixels();

}
