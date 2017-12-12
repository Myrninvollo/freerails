package jfreerails.client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.RepaintManager;

final public class GameLoop {

	final boolean lockFrameRate = false;

	boolean gameNotDone = true;

	final ScreenHandler screenHandler;

	final int targetFPS = 20;

	public GameLoop(ScreenHandler s) {
		screenHandler = s;
	}

	public void run() {

		RepaintManager repaintManager = new ShortCircuitedRepaintManager();

		RepaintManager.setCurrentManager(repaintManager);

		Toolkit awtToolkit = Toolkit.getDefaultToolkit();

		EventQueue eventQueue = awtToolkit.getSystemEventQueue();

		CustomEventQueue customEventQueue = new CustomEventQueue();

		eventQueue.push(customEventQueue);

		FPScounter fPScounter = new FPScounter();

		long frameStartTime;

		while (gameNotDone) {

			frameStartTime = System.currentTimeMillis();

			try {
				customEventQueue.dispatchAllEvents();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Graphics g = screenHandler.getDrawGraphics();

			screenHandler.frame.paintComponents(g);

			fPScounter.updateFPSCounter(frameStartTime, g);

			screenHandler.swapScreens();

			if (lockFrameRate) {
				long deltatime = System.currentTimeMillis() - frameStartTime;

				while (deltatime < (1000 / targetFPS)) {
					try {
						Thread.sleep((1000 / targetFPS) - deltatime);

					} catch (Exception e) {
						e.printStackTrace();
					}
					deltatime = System.currentTimeMillis() - frameStartTime;
				}
			}

		}
	}

}
final class ShortCircuitedRepaintManager extends RepaintManager {

	/**Everything gets painted in the gameloop so there is no 
	 * need to do any painting in response to awt repaint events.
	 * Extending RepaintManager and overriding this method with an 
	 * emtpy one prevents unnecessary painting triggered by 
	 * awt repaint events.
	 * 
	 * (N.B. There may well be a neater way of achiving the same result.)
	 * 
	 * @see javax.swing.RepaintManager#paintDirtyRegions()
	 */
	public void paintDirtyRegions() {

	}

}

final class FPScounter {

	int frameCount = 0;
	int averageFPS = 0;
	long averageFPSStartTime = System.currentTimeMillis();
	String fPSstr = " ";

	void updateFPSCounter(long frameStartTime, Graphics g) {
		frameCount++;
		if (frameCount == 100) {
			frameCount = 0;
			averageFPS = (int) (frameStartTime - averageFPSStartTime);
			averageFPS = 100000 / averageFPS;
			fPSstr = averageFPS + " FPS";
			averageFPSStartTime = frameStartTime;
		}

		g.setColor(Color.WHITE);
		g.fillRect(50, 50, 50, 20);
		g.setColor(Color.BLACK);
		g.drawString(fPSstr, 50, 65);
	}

}