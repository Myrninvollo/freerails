
/*
 *  TiledBackgroundPainter.java
 *
 *  Created on 31 July 2001, 16:22
 */
package jfreerails.client.view;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *  This abstract class stores a buffer of the backgound of the current visible
 *  rectangle of the map. Code that is independent of how tiles are represented,
 *  e.g. whether they are square or isometric, should go here.
 *
 *@author     Luke Lindsay
 *     06 October 2001
 *@version    1.0
 *
 */

public abstract class BufferedTiledBackgroundPainter implements MapViewLayer {

	/**
	 *  This is used to create images that are compatible with the default
	 *  graphics configuration. Such images can be drawn to the screen quickly
	 *  since no conversion is needed.
	 */
	protected java.awt.GraphicsConfiguration defaultConfiguration =
		java
			.awt
			.GraphicsEnvironment
			.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice()
			.getDefaultConfiguration();

	/**
	 *  Used to draw on the backbuffer
	 */
	protected java.awt.Graphics bg;

	/**
	 * Used to draw on the backbuffer. It is translated so that to its users,
	 * it appears they are drawing on the actual map, not a buffered region
	 * of the map.
	 *
	 *  translatedBg equals bg.translate(-bufferRect.x , -bufferRect.y);
	 */
	protected java.awt.Graphics translatedBg;

	/**
	 *  The bounds and location of the map region that is stored in the
	 *  offscreen Image backgraoundBuffer
	 */
	protected Rectangle bufferRect = new Rectangle();

	/**
	 *  An offscreen image storing the background of a region of the map
	 */
	protected java.awt.Image backgroundBuffer;

	/**
	 *  Updates the backbuffer as necessay, then draws it on to the Graphics
	 *  object passed.
	 *
	 *@param  outputGraphics          Once it has been updated, the backbuffer
	 *      is drawn onto this Graphics object.
	 *@param  newVisibleRectectangle  The region of the map that the backbuffer
	 *      must be updated to display.
	 */

	public void paintRect(
		java.awt.Graphics outputGraphics,
		Rectangle newVisibleRectectangle) {

		/*
		 *  If this is the first call to the paint method or the component has just been resized,
		 *  we need to create a new backgroundBuffer.
		 */
		if ((backgroundBuffer == null)
			|| (newVisibleRectectangle.height != bufferRect.height)
			|| (newVisibleRectectangle.width != bufferRect.width)) {
			setbackgroundBuffer(
				newVisibleRectectangle.width,
				newVisibleRectectangle.height);
		}

		/*
		 *  Has the VisibleRectangle moved since the last paint?
		 */
		if ((bufferRect.x != newVisibleRectectangle.x)
			|| (bufferRect.y != newVisibleRectectangle.y)) {
			int dx = bufferRect.x - newVisibleRectectangle.x;
			int dy = bufferRect.y - newVisibleRectectangle.y;
			scrollbackgroundBuffer(dx, dy);
			bufferRect.setBounds(newVisibleRectectangle);
		}
		if ((bufferRect.width != newVisibleRectectangle.width)
			&& (bufferRect.height != newVisibleRectectangle.height)) {
			paintBufferRectangle(
				newVisibleRectectangle.x - bufferRect.x,
				newVisibleRectectangle.y - bufferRect.y,
				newVisibleRectectangle.width,
				newVisibleRectectangle.height);
		}
		outputGraphics.drawImage(
			backgroundBuffer,
			newVisibleRectectangle.x,
			newVisibleRectectangle.y,
			null);
		bufferRect.setBounds(newVisibleRectectangle);
	}

	protected void refreshBackground() {
		paintBufferRectangle(0, 0, bufferRect.width, bufferRect.height);
	}

	protected void setbackgroundBuffer(int w, int h) {
		backgroundBuffer = defaultConfiguration.createCompatibleImage(w, h);
		bufferRect.height = backgroundBuffer.getHeight(null);
		bufferRect.width = backgroundBuffer.getWidth(null);
		bg = backgroundBuffer.getGraphics();
		translatedBg = bg.create();
		translatedBg.translate(-bufferRect.x, -bufferRect.y);
		bg.clearRect(0, 0, w, h);
		refreshBackground();
	}

	protected abstract void paintBufferRectangle(
		int x,
		int y,
		int width,
		int height);

	public void refreshRectangleOfTiles(Rectangle r) {
		Point tile = new Point();
		for (tile.x = r.x; tile.x < (r.x + r.width); tile.x++) {
			for (tile.y = r.y; tile.y < (r.y + r.width); tile.y++) {
				refreshTile(tile.x, tile.y);
			}
		}
	}

	/**
	 *  Description of the Method
	 *
	 *@param  dx  Description of Parameter
	 *@param  dy  Description of Parameter
	 */

	protected void scrollbackgroundBuffer(int dx, int dy) {
		int copyWidth = bufferRect.width;
		int copyHeight = bufferRect.height;
		int copySourceX = 0;
		int copySourceY = 0;
		if (dx > 0) {
			copyWidth -= dx;
		} else {
			copyWidth += dx;
			copySourceX = -dx;
		}
		if (dy > 0) {
			copyHeight -= dy;
		} else {
			copyHeight += dy;
			copySourceY = -dy;
		}
		bg.copyArea(copySourceX, copySourceY, copyWidth, copyHeight, dx, dy);
		bufferRect.x -= dx;
		bufferRect.y -= dy;

		// paint exposed areas
		if (dx > 0) {
			bg.setClip(0, 0, dx, bufferRect.height);
			bg.clearRect(0, 0, dx, bufferRect.height);
			paintBufferRectangle(0, 0, dx, bufferRect.height);
		} else {
			if (dx < 0) {
				bg.setClip(bufferRect.width + dx, 0, -dx, bufferRect.height);
				bg.clearRect(bufferRect.width + dx, 0, -dx, bufferRect.height);
				paintBufferRectangle(
					bufferRect.width + dx,
					0,
					-dx,
					bufferRect.height);
			}
		}
		if (dy > 0) {
			bg.setClip(0, 0, bufferRect.width, dy);
			bg.clearRect(0, 0, bufferRect.width, dy);
			paintBufferRectangle(0, 0, bufferRect.width, dy);
		} else {
			if (dy < 0) {
				bg.setClip(0, bufferRect.height + dy, bufferRect.width, -dy);
				bg.clearRect(0, bufferRect.height + dy, bufferRect.width, -dy);
				paintBufferRectangle(
					0,
					bufferRect.height + dy,
					bufferRect.width,
					-dy);
			}
		}
		bg.setClip(0, 0, bufferRect.width, bufferRect.height);
	}


}
