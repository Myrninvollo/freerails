package jfreerails.client.common;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.RepaintManager;

import jfreerails.controller.GameModel;

final public class GameLoop implements Runnable {
	
	Runnable r;

	final boolean LOCK_FRAME_RATE = false;
	final boolean USE_CUSTOM_EVENT_QUEUE = false;

	boolean gameNotDone = true;

	final ScreenHandler screenHandler;

	final int TARGET_FPS = 30;

	final GameModel gameModel;

	FPScounter fPScounter;

	public GameLoop(ScreenHandler s) {
		gameModel = GameModel.NULL_MODEL;
		screenHandler = s;
	}

	public GameLoop(ScreenHandler s, GameModel gm) {
		gameModel = gm;
		screenHandler = s;
	}

	public void run() {

		RepaintManager repaintManager = new ShortCircuitedRepaintManager();

		RepaintManager.setCurrentManager(repaintManager);

		Toolkit awtToolkit = Toolkit.getDefaultToolkit();

		
		
		EventQueue eventQueue = awtToolkit.getSystemEventQueue();

		if (USE_CUSTOM_EVENT_QUEUE) {
			CustomEventQueue customEventQueue = new CustomEventQueue();

			eventQueue.push(customEventQueue);
		}

		fPScounter = new FPScounter();

		long frameStartTime;
		
		r = new Runnable(){
			public void run() {				
				gameLoopIteration();
				EventQueue.invokeLater(r);
			}
		};
		EventQueue.invokeLater(r);
//		while (gameNotDone) {
//			Graphics g;
//			gameLoopIteration();
//		}
	}

	private void gameLoopIteration() {
		long frameStartTime;

		frameStartTime = System.currentTimeMillis();

		gameModel.update();

		Toolkit.getDefaultToolkit().sync();
		//			if (USE_CUSTOM_EVENT_QUEUE) {
		//				try {
		//					customEventQueue.dispatchAllEvents();
		//				} catch (Exception e) {
		//					e.printStackTrace();
		//				}
		//			}

		Graphics g = screenHandler.getDrawGraphics();
		;
		try {

			screenHandler.frame.paintComponents(g);

			fPScounter.updateFPSCounter(frameStartTime, g);

		} finally {
			g.dispose();
		}
		screenHandler.swapScreens();

		if (LOCK_FRAME_RATE) {
			long deltatime = System.currentTimeMillis() - frameStartTime;

			while (deltatime < (1000 / TARGET_FPS)) {
				try {
					long sleeptime = (1000 / TARGET_FPS) - deltatime;
					Thread.sleep(sleeptime);

				} catch (Exception e) {
					e.printStackTrace();
				}
				deltatime = System.currentTimeMillis() - frameStartTime;
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

	final long TIME_INTERVAL = 5000;

	int frameCount = 0;
	int averageFPS = 0;
	long averageFPSStartTime = System.currentTimeMillis();
	String fPSstr = "starting..";

	boolean dot = true;

	//Display the average number of FPS.
	void updateFPSCounter(long frameStartTime, Graphics g) {
		if (frameCount == 0) {
			averageFPSStartTime = frameStartTime;
		}
		frameCount++;
		if (averageFPSStartTime + TIME_INTERVAL < frameStartTime) {

			int time = (int) (frameStartTime - averageFPSStartTime);
			if (0 != time) {
				averageFPS = frameCount * 1000 / time;
			}
			if (dot) {
				fPSstr = averageFPS + " FPS";
			} else {
				fPSstr = averageFPS + ":FPS";
			}
			frameCount = 0;
			dot = !dot;
		}

		g.setColor(Color.WHITE);
		g.fillRect(50, 50, 50, 20);
		g.setColor(Color.BLACK);
		g.drawString(fPSstr, 50, 65);
	}

}
