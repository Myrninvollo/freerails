

package jfreerails.client.view.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.Random;

import junit.framework.TestCase;


public class TestNewMapView extends TestCase {
	
	NewMapView mapView;
	 GraphicsConfiguration defaultConfiguration =
		GraphicsEnvironment
			.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice()
			.getDefaultConfiguration();
			
	ParentMapview parentMapview;
			
			
	BufferedImage image=defaultConfiguration.createCompatibleImage(100,100, Transparency.TRANSLUCENT);

	Graphics g=image.getGraphics();
	
	int[][]map;
	/**
	 * constructor.
	 * @param    aName     a test name
	 */
	public TestNewMapView(String str) {
		super(str);
		
	} /** tests the method jfreerails.client.view.map.NewMapView.getMapSizeInTiles() */
	public void testGetMapSizeInTiles() {
		Dimension mapSizeInTiles=mapView.getMapSizeInTiles();
		assertEquals(mapSizeInTiles, new Dimension(10,10));
		
	} /** tests the method jfreerails.client.view.map.NewMapView.getTileSize() */
	public void testGetTileSize() {
		Dimension tileSize=mapView.getTileSize();
		assertEquals(tileSize, new Dimension(10,10));
	} /** tests the method jfreerails.client.view.map.NewMapView.getScale() */
	public void testGetScale() {
		assertTrue(mapView.getScale()==10);
	} /** tests the method jfreerails.client.view.map.NewMapView.setScale() */
	public void testSetScale() {
		setUp();
		assertTrue(mapView.getScale()==10);
		Dimension tileSize=mapView.getTileSize();
		assertEquals(tileSize, new Dimension(10,10));
		mapView.setScale(6);
		assertTrue(mapView.getScale()==6);
		tileSize=mapView.getTileSize();
		assertEquals(tileSize, new Dimension(6,6));
		
		
	} /** tests the method jfreerails.client.view.map.NewMapView.paintTile() */
	public void testPaintTile() {
		setUp();
		int rgb=image.getRGB(52,52);
		assertTrue(rgb!=map[5][5]);
		mapView.paintTile(g,new Point(5,5));
		rgb=image.getRGB(52,52);
		assertTrue(rgb==map[5][5]);
		
	} /** tests the method jfreerails.client.view.map.NewMapView.paintRectangleOfTiles() */
	public void testPaintRectangleOfTiles() {
		setUp();
		int rgb=image.getRGB(52,52);
		assertTrue(rgb!=map[5][5]);
		Rectangle r=new Rectangle(5,5,2,2);
		mapView.paintRectangleOfTiles(g,r);
		rgb=image.getRGB(52,52);
		assertTrue(rgb==map[5][5]);
		rgb=image.getRGB(62,62);
		assertTrue(rgb==map[6][6]);
		
		
	} /** tests the method jfreerails.client.view.map.NewMapView.paintRect() */
	public void testPaintRect() {
		setUp();
		int rgb=image.getRGB(52,52);
		assertTrue(rgb!=map[5][5]);
		Rectangle r=new Rectangle(5,5,2,2);
		mapView.paintRectangleOfTiles(g,r);
		rgb=image.getRGB(52,52);
		assertTrue(rgb==map[5][5]);
		rgb=image.getRGB(62,62);
		assertTrue(rgb==map[6][6]);
		
	} /** tests the method jfreerails.client.view.map.NewMapView.getParentMapView() */
	public void testGetParentMapView() {
		//do nothing fo now
	} /** tests the method jfreerails.client.view.map.NewMapView.setParentMapView() */
	public void testSetParentMapView() {
		setUp();
		NewMapView m=mapView.getParentMapView();
		assertEquals(m, parentMapview);
		mapView.setParentMapView(null);
		 m=mapView.getParentMapView();
		 assertNull(m);
		
	} /** tests the method jfreerails.client.view.map.NewMapView.refresh() */
	public void testRefresh() {
		setUp();
		int rgb=image.getRGB(52,52);
		assertTrue(rgb!=map[5][5]);
		mapView.paintTile(g,new Point(5,5));
		rgb=image.getRGB(52,52);
		assertTrue(rgb==map[5][5]);
		this.regenerateMapArray(map);
		
		mapView.refresh();
		assertTrue(rgb!=map[5][5]);
		mapView.paintTile(g,new Point(5,5));
		rgb=image.getRGB(52,52);
		assertTrue(rgb==map[5][5]);
		
	} /** tests the method jfreerails.client.view.map.NewMapView.refreshTile() */
	public void testRefreshTile() {
		setUp();
		int rgb=image.getRGB(52,52);
		assertTrue(rgb!=map[5][5]);
		mapView.paintTile(g,new Point(5,5));
		rgb=image.getRGB(52,52);
		assertTrue(rgb==map[5][5]);
		this.regenerateMapArray(map);
		
		mapView.refreshTile(new Point(5,5));
		assertTrue(rgb!=map[5][5]);
		mapView.paintTile(g,new Point(5,5));
		rgb=image.getRGB(52,52);
		assertTrue(rgb==map[5][5]);
	
	} /** tests the method jfreerails.client.view.map.NewMapView.refreshTileAndNotifyParent() */
	public void testRefreshTileAndNotifyParent() {
		setUp();
		int rgb=image.getRGB(52,52);
		assertTrue(rgb!=map[5][5]);
		mapView.paintTile(g,new Point(5,5));
		rgb=image.getRGB(52,52);
		assertTrue(rgb==map[5][5]);
		this.regenerateMapArray(map);
		
		mapView.refreshTileAndNotifyParent(new Point(5,5));
		assertTrue(rgb!=map[5][5]);
		//mapView.paintTile(g,new Point(5,5));
		//the parent should do this.
		rgb=image.getRGB(52,52);
		assertTrue(rgb==map[5][5]);
		
	} /** tests the method jfreerails.client.view.map.NewMapView.refreshAndNotifyParent() */
	public void testRefreshAndNotifyParent() {
		setUp();
		int rgb=image.getRGB(52,52);
		assertTrue(rgb!=map[5][5]);
		mapView.paintTile(g,new Point(5,5));
		rgb=image.getRGB(52,52);
		assertTrue(rgb==map[5][5]);
		this.regenerateMapArray(map);
		assertTrue(rgb!=map[5][5]);
		mapView.refreshAndNotifyParent();
		
	
		
		
		rgb=image.getRGB(52,52);
		assertTrue(rgb==map[5][5]);
	} /** tests the method jfreerails.client.view.map.NewMapView.getMapSizeInPixels() */
	public void testGetMapSizeInPixels() {
		setUp();
		Dimension mapSizeInPixels=mapView.getMapSizeInPixels();
		assertEquals(mapSizeInPixels, new Dimension(100,100));
		mapView.setScale(7);
		mapSizeInPixels=mapView.getMapSizeInPixels();
		assertEquals(mapSizeInPixels, new Dimension(70,70));
		
	}
	protected void setUp(){
		map=generateMapArray(10,10);
		mapView=new BoringMapView(map);
		this.parentMapview=new ParentMapview(mapView, g); 
		g.clearRect(0,0,100,100);
		
		
	}
	public static int[][] generateMapArray(int w, int h){
		int[][] map=new int[w][h];
		Random rand=new Random(345235235);
		for(int x=0;x<w;x++){
			for(int y=0;y<w;y++){
				int rgb=rand.nextInt(5000);
				rgb=new Color(rgb).getRGB();
				map[x][y]=rgb;
			}
		}
		return map;
	}
	public static void regenerateMapArray(int[][] map){
		Random rand=new Random(64968403);
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[0].length;y++){
				int rgb=rand.nextInt(5000);
				rgb=new Color(rgb).getRGB();
				map[x][y]=rgb;
			}
		}
	}
		
}