package jfreerails.client.view.map;

/*
 * ScrollingDemoMapOverview.java
 *
 * Created on 12 December 2001, 16:13
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.MouseInputAdapter;
/**
 *
 * @author  Luke Lindsay
 */

public class ScrollingDemoMapOverview extends ScrollingDemoMapView {

	private MapViewJComponent theObserved;

	private Rectangle theObservedScaledVisibleRect;

	private boolean isObervingAnotherQDMapView = false;

	/** Creates new form ScrollingDemoMapView */
	public ScrollingDemoMapOverview(
		NewMapView mv,
		MapViewJComponent qdmv,
		JViewport viewport) {
		super(mv);

		theObserved = qdmv;

		MouseInputAdapter myMouseInputAdapter = new MouseInputAdapter() {
			Point oldScreenLocation = new Point(); //Screen location in pixels

			Point newScreenLocation = new Point(); //Screen location in pixels

			Point mapLocation = new Point(); //Location in tiles

			boolean noNeedToCenter = false; //Stop us centering twice.

			public void mouseDragged(MouseEvent evt) {
				float scale = mapView.getScale();
				noNeedToCenter = true;
				Rectangle thisVisibleRect = ScrollingDemoMapOverview.this.getVisibleRect();

				oldScreenLocation.setLocation(newScreenLocation);
				newScreenLocation.x = evt.getX() - thisVisibleRect.x;
				newScreenLocation.y = evt.getY() - thisVisibleRect.y;

				Rectangle r = new Rectangle(theObserved.getVisibleRect());

				r.x
					+= (int) ((newScreenLocation.x - oldScreenLocation.x)
						* theObserved.getScale()
						/ scale);
				r.y
					+= (int) ((newScreenLocation.y - oldScreenLocation.y)
						* theObserved.getScale()
						/ scale);

				theObserved.scrollRectToVisible(r);
			}

			public void mousePressed(MouseEvent evt) {
				float scale = mapView.getScale();
				Rectangle thisVisibleRect = ScrollingDemoMapOverview.this.getVisibleRect();
				oldScreenLocation.setLocation(oldScreenLocation);
				newScreenLocation.x = evt.getX() - thisVisibleRect.x;
				newScreenLocation.y = evt.getY() - thisVisibleRect.y;

				Point p = new Point((int) (evt.getX() / scale), (int) (evt.getY() / scale));

				//Decide if we need to center the view.  Since the map may be wrapped in
				//either axis, we need to test 4 combinations.

				Rectangle visRect = new Rectangle(theObserved.getVisibleRect());

				Point pScaledToObserved =
					new Point(
						(int) (p.x * theObserved.getScale()),
						(int) (p.y * theObserved.getScale()));

				//				Rectangle rrr =
				//					theObserved.getScrollTargetRect(new Rectangle(pScaledToObserved));
				//
				//				if (visRect.x != rrr.x || visRect.y != rrr.y) {
				//					theObserved.centerOnTile(p);
				//					noNeedToCenter = true;
				//				}

				if (!theObserved.isRectVisible(new Rectangle(pScaledToObserved))) {
					theObserved.centerOnTile(p);
					noNeedToCenter = true;

				}

			}
			public void mouseReleased(MouseEvent evt) {
				float scale = mapView.getScale();
				if (!noNeedToCenter) {

					Point p = new Point((int) (evt.getX() / scale), (int) (evt.getY() / scale));
					Rectangle r = theObserved.getVisibleRect();

					theObserved.centerOnTile(p);

				}

				noNeedToCenter = false;
			}
		};
		this.addMouseListener(myMouseInputAdapter);
		this.addMouseMotionListener(myMouseInputAdapter);

		viewport.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				updateObservedRect();
			}
		});

	}
	public ScrollingDemoMapOverview(NewMapView mv) {
		super(mv);
		throw new java.lang.UnsupportedOperationException(
			"Don't use this constructor, use  ScrollingDemoMapOverview(float s, ScrollingDemoMapView qdmv, JViewport viewport) or ScrollingDemoMapView(float s) instead.");

	}
	private void updateObservedRect() {
		float scale = mapView.getScale();
		Rectangle r = theObserved.getVisibleRect();

		float theObservedScale = theObserved.getScale();
		theObservedScaledVisibleRect =
			new Rectangle(
				(int) (r.x * scale / theObservedScale),
				(int) (r.y * scale / theObservedScale),
				(int) (r.width * scale / theObservedScale),
				(int) (r.height * scale / theObservedScale));

		Rectangle rectToView = new Rectangle(theObservedScaledVisibleRect);

		isObervingAnotherQDMapView = true;

		Rectangle visRect = this.getVisibleRect();

		Rectangle target = this.getScrollTargetRect(rectToView);
		if (visRect.x != target.x || visRect.y != target.y) {
			this.scrollRectToVisible(target);

		} else {
			this.repaint();

		}

	}

	public void paint(Graphics g) {
		super.paint(g);

		if (isObervingAnotherQDMapView) {
			Graphics2D g2 = (Graphics2D) g;
			//Color c = new Color(255, 255, 255, 128);
			Color c =Color.white;
			g2.setColor(c);

			//We do it 9 times to avoid wrapping related problems.
			//Is there a better way? 
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {
					int drawX =
						theObservedScaledVisibleRect.x + (x * this.getMapSizeInPixels().width);
					int drawY =
						theObservedScaledVisibleRect.y + (y * this.getMapSizeInPixels().height);
					int drawWidth = theObservedScaledVisibleRect.width;
					int drawHeight = theObservedScaledVisibleRect.height;
					//g2.fillRect(drawX, drawY, drawWidth, drawHeight);
					g2.drawRect(drawX, drawY, drawWidth, drawHeight);
				

				}
			}
		}
	}

	private void initComponents() {

		setLayout(new java.awt.BorderLayout());

	}
}