package jfreerails.client.view.map;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.event.MouseInputAdapter;
import jfreerails.client.ScrollByDraggingMapMouseInputAdapter;



/** This class is a JPanel that encapsulates a wrapped map
 * component and ensures that the extra restrictions
 * imposed by the wrapped map component are not violated.
 * Where possible, use this class instead of using a wrapped
 * map component directly.
 *
 * @author Luke Lindsay
 */
final public class MapViewJComponentContainer
	extends javax.swing.JPanel
	implements NewMapView {

	public static MapViewJComponentContainer generateMainmapComponent(MapViewJComponent mv) {
		return new MapViewJComponentContainer(mv);
	}

	public static MapViewJComponentContainer generateOverviewMapComponent(
		NewMapView mv,
		MapViewJComponentContainer mainMap) {
		return new MapViewJComponentContainer(mv, mainMap);
	}

	private MapViewJComponentContainer(MapViewJComponent mv) {

		mapView = mv;//new ScrollingDemoMapView(mv);
		initComponents();
		MouseInputAdapter myMouseInputAdapter = new ScrollByDraggingMapMouseInputAdapter();
		mapView.addMouseListener(myMouseInputAdapter);
		mapView.addMouseMotionListener(myMouseInputAdapter);
		//mapView.enableScrollingByDraggingMap();
		if(mapView.isWrappedHorizontally()||mapView.isWrappedVertically()){
			jScrollPane1.setHorizontalScrollBarPolicy(
			javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane1.setVerticalScrollBarPolicy(
			javax.swing.JScrollPane.VERTICAL_SCROLLBAR_NEVER);

		}
		
	}

	private MapViewJComponentContainer(
		NewMapView mv,
		MapViewJComponentContainer theObserved) {

		mapView =
			new ScrollingDemoMapOverview(
				mv,
				theObserved.getScrollingDemoMapView(),
				theObserved.getViewport());

		initComponents();
		
		jScrollPane1.setHorizontalScrollBarPolicy(
			javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane1.setVerticalScrollBarPolicy(
			javax.swing.JScrollPane.VERTICAL_SCROLLBAR_NEVER);

	}

	protected void initComponents() {
		setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

		Dimension mapSize = new Dimension(mapView.getMapSizeInPixels());

		jScrollPane1 = new javax.swing.JScrollPane();

		
//		jScrollPane1.setHorizontalScrollBarPolicy(
//			javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		jScrollPane1.setVerticalScrollBarPolicy(
//			javax.swing.JScrollPane.VERTICAL_SCROLLBAR_NEVER);

		//Seems to be unnecessary.
		//jScrollPane1.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

		jScrollPane1.setMaximumSize(mapSize);
		jScrollPane1.setPreferredSize(mapSize);

		//mapView.setLayout(new java.awt.BorderLayout());

		jScrollPane1.setViewportView(mapView);

		setPreferredSize(mapSize);
		add(Box.createHorizontalGlue());
		add(jScrollPane1);
		add(Box.createHorizontalGlue());

	}
	protected JViewport getViewport() {
		return jScrollPane1.getViewport();
	}
	protected MapViewJComponent getScrollingDemoMapView() {
		return mapView;
	}
	public Dimension getMapSizeInTiles() {
		return mapView.getMapSizeInTiles();
	}

	public Dimension getTileSize() {
		return mapView.getTileSize();

	}

	public float getScale() {
		return mapView.getScale();

	}

	public void setScale(float scale) {
		mapView.setScale(scale);
	}

	public void paintTile(Graphics g, Point tile) {
		mapView.paintTile(g, tile);
	}

	public void paintRectangleOfTiles(Graphics g, Rectangle tilesToPaint) {
		mapView.paintRectangleOfTiles(g, tilesToPaint);
	}

	public void paintRect(Graphics g, Rectangle visibleRect) {
		mapView.paintRect(g, visibleRect);
	}

	public NewMapView getParentMapView() {
		return mapView.getParentMapView();

	}

	public void setParentMapView(NewMapView parent) {
		mapView.setParentMapView(parent);
	}

	public void refreshTile(Point tile) {
		mapView.refreshTile(tile);
	}

	public void refresh() {
		mapView.refresh();
	}

	public Dimension getMapSizeInPixels() {
		return mapView.getMapSizeInPixels();

	}
	public void refreshTileAndNotifyParent(Point tile) {
		mapView.refreshTileAndNotifyParent(tile);

	}
	public void refreshAndNotifyParent() {
		mapView.refreshAndNotifyParent();

	}

	private javax.swing.JScrollPane jScrollPane1;
	private MapViewJComponent mapView;
	//private NewMapView mapView;

}