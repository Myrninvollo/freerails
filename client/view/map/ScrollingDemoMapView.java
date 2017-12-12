package jfreerails.client.view.map;

/*
 * ScrollingDemoMapView.java
 *
 * Created on 12 December 2001, 16:13
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/** This class is a JComponent that displays a map that
 * can be wrapped vertically and/or horizontally.  To work
 * correctly, it must only placed a container with a
 * layout manager that respects JComponent.getMaximumSize().
 * (Currently, only BoxLayout does this.)
 *
 * @author Luke Lindsay
 */
public class ScrollingDemoMapView
	extends MapViewJComponent
	implements NewMapView {

	protected Rectangle visibleTiles;

	/** Pixels per tile.
	 */
	//protected float scale;

	//protected Image map;

	//protected Image scaledImage;

	protected NewMapView mapView;

	protected Dimension mapSizeInTiles = new Dimension();

	//protected Dimension mapSizeInPixels = new Dimension();

	//	protected GraphicsConfiguration defaultConfiguration =
	//		GraphicsEnvironment
	//			.getLocalGraphicsEnvironment()
	//			.getDefaultScreenDevice()
	//			.getDefaultConfiguration();

	public static final int WRAP = 2;

	public static final int NO_WRAPPING = 1;

	protected final int VERTICAL_WRAPPING = NO_WRAPPING;

	protected final int HORIZONTAL_WRAPPING = NO_WRAPPING;

	public ScrollingDemoMapView(NewMapView mv) {
		super(mv);

		//this.mapView = new ScrollingDemoNewMapView(s);
		this.mapView = mv;
		mapView.setParentMapView(this);

		//		java.net.URL imageURL =
		//			ScrollingDemoMapView.class.getResource("/scrollingdemo/simplemap.gif");
		//		map = new javax.swing.ImageIcon(imageURL).getImage();

		//this.mapSizeInTiles.setSize(map.getWidth(null), map.getHeight(null));

		//this.mapSizeInTiles.setSize()

		setScale(mapView.getScale());

	}

	public Dimension getMapSizeInPixels() {
		return mapView.getMapSizeInPixels();
	}

	//	public void centerOnTile(Point tile) {
	//		
	//		float scale=mapView.getScale();
	//		Rectangle visRect = new Rectangle(this.getVisibleRect());
	//		visRect.x = (int) (tile.x * scale - (visRect.width / 2));
	//		visRect.y = (int) (tile.y * scale - (visRect.height / 2));
	//		this.scrollRectToVisible(visRect);
	//	}

	/** Returns the rectangle that contains the specified rectangle,
	 * and is nearest to and has the same dimensions as the
	 * component's current visible rectangle.  If the specified
	 * rectangle is bigger than the visible rectangle, a
	 * rectangle with the visible rectangle's dimensions and
	 * the specified rectangle's center is returned.  If the
	 * visible rectangle already contains the specified
	 * rectangle, a rectangle equal to the visible rectangle
	 * is returned.  If and only if the map is wrapped on a
	 * given axis, the returned rectangle is always the
	 * nearest in either direction on that axis.
	 *
	 * @param r The target rectangle.
	 * @return The nearest rectangle containing the target.
	 */
	protected Rectangle getScrollTargetRect(Rectangle r) {

		Rectangle visRect = this.getVisibleRect();
		Rectangle wrappedRect = new Rectangle(r);
		Dimension mapSizeInPixels = this.mapView.getMapSizeInPixels();

		//I had to resort to a pencil and paper and junit to write this.
		//It probably should be broken into several smaller steps.

		if (2 == HORIZONTAL_WRAPPING) {

			wrappedRect.x = wrapWidth(wrappedRect.x);

			wrappedRect.width = visRect.width;

			if ((visRect.x <= wrappedRect.x)
				&& ((visRect.x + visRect.width) >= (wrappedRect.x + r.width))) {

				wrappedRect.x = visRect.x;
			} else if (
				(visRect.x <= (wrappedRect.x + mapSizeInPixels.width))
					&& ((visRect.x + visRect.width)
						>= (wrappedRect.x + r.width + mapSizeInPixels.width))) {

				wrappedRect.x = visRect.x;
			} else {

				if (r.width <= visRect.width) {

					if ((wrappedRect.x + r.width) > (visRect.x + visRect.width)) {
						if ((wrappedRect.x + r.width - visRect.x)
							< (visRect.x + visRect.width + mapSizeInPixels.width - wrappedRect.x)) {
							wrappedRect.x += r.width - visRect.width;
						} else {
							//do nothing
						}
					}
					if (visRect.x > wrappedRect.x) {
						if ((visRect.x + visRect.width - wrappedRect.x)
							< (wrappedRect.x + r.width + mapSizeInPixels.width - visRect.x)) {
							//do nothing
						} else {
							wrappedRect.x += r.width - visRect.width;

						}
					}
				} else {
					//When the target is bigger than the visible rect.
					wrappedRect.x = r.x + (r.width / 2) - (visRect.width / 2);
					wrappedRect.width = visRect.width;
				}
			}

			wrappedRect.x = wrapWidth(wrappedRect.x);

		} else {
			if ((r.x >= visRect.x) && ((r.x + r.width) <= (visRect.x + visRect.width))) {
				wrappedRect.x = visRect.x;
				wrappedRect.width = visRect.width;
			} else {
				//do nothing
			}
		}

		//Do exactly the same on the vertical axis.
		if (2 == VERTICAL_WRAPPING) {
			wrappedRect.y = wrapHeight(wrappedRect.y);

			wrappedRect.height = visRect.height;

			if ((visRect.y <= wrappedRect.y)
				&& ((visRect.y + visRect.height) >= (wrappedRect.y + r.height))) {

				wrappedRect.y = visRect.y;
			} else if (
				(visRect.y <= (wrappedRect.y + mapSizeInPixels.height))
					&& ((visRect.y + visRect.height)
						>= (wrappedRect.y + r.height + mapSizeInPixels.height))) {

				wrappedRect.y = visRect.y;
			} else {

				if (r.height <= visRect.height) {

					if ((wrappedRect.y + r.height) > (visRect.y + visRect.height)) {
						if ((wrappedRect.y + r.height - visRect.y)
							< (visRect.y + visRect.height + mapSizeInPixels.height - wrappedRect.y)) {
							wrappedRect.y += r.height - visRect.height;
						} else {
							//do nothing
						}
					}
					if (visRect.y > wrappedRect.y) {
						if ((visRect.y + visRect.height - wrappedRect.y)
							< (wrappedRect.y + r.height + mapSizeInPixels.height - visRect.y)) {
							//do nothing
						} else {
							wrappedRect.y += r.height - visRect.height;

						}
					}
				} else {
					wrappedRect.y = r.y + (r.height / 2) - (visRect.height / 2);
					wrappedRect.height = visRect.height;
				}
			}

			wrappedRect.y = wrapHeight(wrappedRect.y);

		} else {
			if ((r.y >= visRect.y) && ((r.y + r.height) <= (visRect.y + visRect.height))) {
				wrappedRect.y = visRect.y;
				wrappedRect.height = visRect.height;
			} else {
				//do nothing
			}
		}

		return wrappedRect;
	}

	public void scrollRectToVisible(Rectangle r) {
		if (VERTICAL_WRAPPING != NO_WRAPPING || HORIZONTAL_WRAPPING != NO_WRAPPING) {
			r = this.getScrollTargetRect(r);
		}
		super.scrollRectToVisible(r);

	}
	protected int wrapWidth(int w) {
		Dimension mapSizeInPixels = this.mapView.getMapSizeInPixels();

		if (w == 0) {
			return 0;
		} else {
			int i =
				(mapSizeInPixels.width + (w % mapSizeInPixels.width)) % mapSizeInPixels.width;
			return i;
		}

	}
	protected int wrapHeight(int h) {
		Dimension mapSizeInPixels = this.mapView.getMapSizeInPixels();

		int i =
			(mapSizeInPixels.height + (h % mapSizeInPixels.height))
				% mapSizeInPixels.height;
		return i;

	}
	public boolean isRectVisible(Rectangle r) {
		Rectangle visRect = this.getVisibleRect();
		Rectangle wrappedR = this.getScrollTargetRect(r);
		return visRect.equals(wrappedR);
	}

	public void setScale(float s) {
		//this.scale = s;
		if (mapView.getScale() != s) {
			mapView.setScale(s);
		}

		//this.mapSizeInPixels.height = (int) (map.getHeight(null) * scale);

		//		this.mapSizeInPixels.width = (int) (map.getWidth(null) * scale);

		this.setPreferredSize(
			new Dimension(
				mapView.getMapSizeInPixels().width * HORIZONTAL_WRAPPING,
				mapView.getMapSizeInPixels().height * VERTICAL_WRAPPING));
		//		this.scaledImage =
		//			defaultConfiguration.createCompatibleImage(
		//				mapSizeInPixels.width,
		//				mapSizeInPixels.height);
		//		Graphics g = scaledImage.getGraphics();
		//		g.drawImage(map, 0, 0, mapSizeInPixels.width, mapSizeInPixels.height, null);
		//		g.dispose();
	}
	public void paint(Graphics g) {

		super.paint(g);
		mapView.paintRect(g, this.getVisibleRect());

		//		for (int x = 0; x < HORIZONTAL_WRAPPING; x++) {
		//			for (int y = 0; y < VERTICAL_WRAPPING; y++) {
		//				g.drawImage(
		//					scaledImage,
		//					x * mapSizeInPixels.width,
		//					y * mapSizeInPixels.height,
		//					null);
		//			}
		//		}
	}
	public float getScale() {
		return mapView.getScale();
	}
	/** Adds a mouselistener to the this component, which scrolls
	 * the map when the user drags it and enters the map when
	 * the user clicks it.
	 */
	//	public void enableScrollingByDraggingMap() {
	//		MouseInputAdapter myMouseInputAdapter = new ScrollByDraggingMapMouseInputAdapter();
	//		MouseInputAdapter myMouseInputAdapter = new MouseInputAdapter() {
	//			Point oldScreenLocation = new Point(); //Screen location in pixels
	//
	//			Point newScreenLocation = new Point(); //Screen location in pixels
	//
	//			Point mapLocation = new Point(); //Location in tiles
	//
	//			boolean noNeedToCenter = false; //Stop us centering twice.
	//
	//			public void mouseDragged(MouseEvent evt) {
	//				noNeedToCenter = true;
	//								Rectangle thisVisibleRect = ScrollingDemoMapView.this.getVisibleRect();
	//
	//				oldScreenLocation.setLocation(newScreenLocation);
	//				newScreenLocation.x = evt.getX() - thisVisibleRect.x;
	//				newScreenLocation.y = evt.getY() - thisVisibleRect.y;
	//
	//				Rectangle r = getVisibleRect();
	//
	//				r.x -= newScreenLocation.x - oldScreenLocation.x;
	//				r.y -= newScreenLocation.y - oldScreenLocation.y;
	//
	//				scrollRectToVisible(getScrollTargetRect(r));
	//			}
	//
	//			public void mousePressed(MouseEvent evt) {
	//				Rectangle thisVisibleRect = ScrollingDemoMapView.this.getVisibleRect();
	//				oldScreenLocation.setLocation(oldScreenLocation);
	//				newScreenLocation.x = evt.getX() - thisVisibleRect.x;
	//				newScreenLocation.y = evt.getY() - thisVisibleRect.y;
	//			}
	//			public void mouseReleased(MouseEvent evt) {
	//				float scale=mapView.getScale();
	//				if (!noNeedToCenter) {
	//					Point p = new Point((int) (evt.getX() / scale), (int) (evt.getY() / scale));
	//					centerOnTile(p);
	//
	//				}
	//				noNeedToCenter = false;
	//			}
	//		};
	//		this.addMouseListener(myMouseInputAdapter);
	//		this.addMouseMotionListener(myMouseInputAdapter);
	//	}

	private void initComponents() {

		setLayout(new java.awt.BorderLayout());

	}
	public Dimension getMapSizeInTiles() {
		return new Dimension(mapSizeInTiles);
	}

	public Dimension getTileSize() {
		return mapView.getTileSize();

	}

	public void paintTile(Graphics g, Point tile) {
		paint(g);
	}

	public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint) {
		paint(g);
	}

	public void paintRect(Graphics g, Rectangle visibleRect) {
		paint(g);
	}

	public NewMapView getParentMapView() {
		throw new UnsupportedOperationException("Method not yet implemented.");

	}

	public void setParentMapView(NewMapView parent) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	public void refreshTile(Point tile) {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	public void refresh() {
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	public void refreshTileAndNotifyParent(Point tile) {
		throw new UnsupportedOperationException("Method not yet implemented.");

	}
	public void refreshAndNotifyParent() {
		this.repaint();
	}
	public boolean isWrappedVertically() {
		if (VERTICAL_WRAPPING == NO_WRAPPING) {

			return false;
		} else {
			return true;
		}
	}
	public boolean isWrappedHorizontally() {
		if (HORIZONTAL_WRAPPING == NO_WRAPPING) {

			return false;
		} else {
			return true;
		}
	}

}