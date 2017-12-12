package experimental;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import jfreerails.world.track.TrackSection;

/**
 * @version 	1.0
 * @author
 */
public class ExptTrackJFrame extends JFrame {

	TrackSection[] track = new TrackSection[4];
	TrackSectionView view = new TrackSectionView();

	public ExptTrackJFrame() {
		super();
		track[0] = new TrackSectionImpl1(50, 50, Bearings.EAST,150, 50, Bearings.WEST);
		track[1] = new TrackSectionImpl1(150, 50, Bearings.EAST, 150, 150, Bearings.WEST);
		track[2] = new TrackSectionImpl1(150, 150, Bearings.EAST, 50, 150, Bearings.WEST);
		track[3] = new TrackSectionImpl1(50, 150, Bearings.EAST, 50, 50, Bearings.WEST);
	}

	public static void main(String[] args) {
		(new ExptTrackJFrame()).show();
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < track.length; i++) {
			view.paint(g, track[i]);
		}
	}
}