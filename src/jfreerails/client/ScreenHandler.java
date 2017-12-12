package jfreerails.client;

import java.awt.Component;
import java.awt.Container;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;

import javax.swing.JComponent;
import javax.swing.JFrame;

/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
/**
 * @version 	1.0
 * @author
 */
final public class ScreenHandler {

	GraphicsDevice device;

	final JFrame frame;

	BufferStrategy bufferStrategy;

	public ScreenHandler(JFrame f, boolean fullscreen) {

		frame = f;

		GraphicsEnvironment env =
			GraphicsEnvironment.getLocalGraphicsEnvironment();

		device = env.getDefaultScreenDevice();
		setRepaintOffAndDisableDoubleBuffering(frame);
		GraphicsConfiguration gc = device.getDefaultConfiguration();

		if (fullscreen) {
			frame.setUndecorated(true);
			device.setFullScreenWindow(frame);
			if (device.isDisplayChangeSupported()) {
				chooseBestDisplayMode(device);
			}
		} else {
			frame.getContentPane().setSize(640, 400);

			frame.pack();
			frame.setSize(640, 400);

		}
		
		frame.createBufferStrategy(2);
		bufferStrategy = frame.getBufferStrategy();
		
		setRepaintOffAndDisableDoubleBuffering(frame);

	}

	public Graphics getDrawGraphics() {
		return bufferStrategy.getDrawGraphics();
	}
	
	
	public boolean swapScreens() {
		boolean done = false;
		if (!bufferStrategy.contentsLost()) {
			bufferStrategy.show();
			done = true;
		}		
		return done;
	}

	public static void setRepaintOffAndDisableDoubleBuffering(Component c) {

		c.setIgnoreRepaint(true);
		
		//Since we are using a buffer strategy we don't want Swing
		//to double buffer any JComponents.
		if (c instanceof JComponent) {
			JComponent jComponent = (JComponent) c;
			boolean doubleDuffered = c.isDoubleBuffered();			
			jComponent.setDoubleBuffered(false);
		}

		if (c instanceof java.awt.Container) {
			Component[] children = ((Container) c).getComponents();
			for (int i = 0; i < children.length; i++) {
				setRepaintOffAndDisableDoubleBuffering(children[i]);
			}
		}
	}

	private static DisplayMode getBestDisplayMode(GraphicsDevice device) {
		for (int x = 0; x < BEST_DISPLAY_MODES.length; x++) {
			DisplayMode[] modes = device.getDisplayModes();
			for (int i = 0; i < modes.length; i++) {
				if (modes[i].getWidth() == BEST_DISPLAY_MODES[x].getWidth()
					&& modes[i].getHeight() == BEST_DISPLAY_MODES[x].getHeight()
					&& modes[i].getBitDepth()
						== BEST_DISPLAY_MODES[x].getBitDepth()) {
					return BEST_DISPLAY_MODES[x];

				}
			}
		}
		return null;
	}

	private static void chooseBestDisplayMode(GraphicsDevice device) {
		DisplayMode best = getBestDisplayMode(device);
		if (best != null) {
			device.setDisplayMode(best);
		}

	}

	private static final DisplayMode[] BEST_DISPLAY_MODES =
		new DisplayMode[] {
			new DisplayMode(640, 400, 16, 0),
			new DisplayMode(800, 600, 16, 0),
			new DisplayMode(1024, 768, 16, 0),
			};

}