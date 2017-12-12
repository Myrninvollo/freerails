
/*
* ImageSplitter.java
*
* Created on 23 May 2001, 00:49
*/
package jfreerails.lib;

/**
*  This class provides methods to grap tiles from an image.
* @author  lindsal8
* @version 
*/
import java.awt.Image;
import java.awt.Transparency;

import javax.swing.ImageIcon;

final public class ImageSplitter extends java.lang.Object {

	private java.awt.Image sourceImage;

	private java.net.URL imageURL;

	private int subGridXOffset = 0;

	private int transparency = Transparency.OPAQUE;

	private java.awt.GraphicsConfiguration defaultConfiguration =
		java
			.awt
			.GraphicsEnvironment
			.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice()
			.getDefaultConfiguration();

	private int subGridYoffset = 0;

	private int tileGridWidth, tileGridHeight, gridXAxisOrigin, gridYAxisOrigin;

	public Image getTileFromSubGrid(int gridX, int gridY) {
		Image tile =
			getTile(
				((gridXAxisOrigin + ((subGridXOffset + gridX) * tileGridWidth))),
				((gridYAxisOrigin + ((subGridYoffset + gridY) * tileGridWidth))),
				tileGridWidth,
				tileGridHeight);
		if (tile == null) {
			System.out.println("Error in ImageSplitter.Image: tileIcon==null.");
		}
		return tile;
	}

	public void setTransparencyToOPAQUE() {
		this.transparency = Transparency.OPAQUE;
	}

	public void setTransparencyToBITMASK() {
		this.transparency = Transparency.BITMASK;
	}

	public void setTransparencyToTRANSLUCENT() {
		this.transparency = Transparency.TRANSLUCENT;
	}

	public Image getTileFromGrid(int gridX, int gridY) {
		Image tile =
			getTile(
				((gridXAxisOrigin + (gridX * tileGridWidth))),
				((gridYAxisOrigin + (gridY * tileGridWidth))),
				tileGridWidth,
				tileGridHeight);
		if (tile == null) {
			throw new java.lang.NullPointerException(
				"Error in ImageSplitter.Image: tileIcon==null.");
		}
		return tile;
	}

	public Image getTile(int x, int y, int tileWidth, int tileHeight) {
		try {
			java.awt.image.BufferedImage tileImage =
				defaultConfiguration.createCompatibleImage(tileWidth, tileHeight, transparency);
			java.awt.Graphics g = tileImage.getGraphics();
			g.drawImage(sourceImage, -x, -y, null);
			return tileImage;
		} catch (Exception e) {
			throw new java.lang.IllegalArgumentException(
				"Tried to get tile from outside the image.\n" + "Source image: " + imageURL);
		}
	}

	public ImageSplitter(java.net.URL url) {
		imageURL = url;
		System.out.println("\nLoading image " + imageURL);
		sourceImage = (new ImageIcon(imageURL)).getImage();
	}

	public void setTileGrid(int x, int y, int tileWidth, int tileHeight) {
		tileGridWidth = tileWidth;
		tileGridHeight = tileHeight;
		gridXAxisOrigin = x;
		gridYAxisOrigin = y;
	}

	public void setSubGridOffset(int x, int y) {
		subGridXOffset = x;
		subGridYoffset = y;
	}
}