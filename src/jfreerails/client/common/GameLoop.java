package jfreerails.client.common;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.RepaintManager;

import jfreerails.util.GameModel;

/** hopefully this will work better on linux!	*/

final public class GameLoop implements Runnable {

	final static boolean LIMIT_FRAME_RATE = false;

	boolean gameNotDone = true;

	final ScreenHandler screenHandler;

	final static int TARGET_FPS = 30;
	

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

		RepaintManager repaintManager =
			new RepaintManagerForActiveRendering(screenHandler.frame);

		RepaintManager.setCurrentManager(repaintManager);

		Toolkit awtToolkit = Toolkit.getDefaultToolkit();

		EventQueue eventQueue = awtToolkit.getSystemEventQueue();

		Object mutex = new Object();
		EventQueue customEventQueue = new CustomEventQueue2(mutex);

		eventQueue.push(customEventQueue);

		fPScounter = new FPScounter();

		long frameStartTime;

		long nextModelUpdateDue = System.currentTimeMillis();

		while (gameNotDone) {

			frameStartTime = System.currentTimeMillis();

			synchronized (mutex) {
				
				gameModel.update();

				Graphics g = screenHandler.getDrawGraphics();

				try {

					screenHandler.frame.paintComponents(g);

					fPScounter.updateFPSCounter(frameStartTime, g);

				} finally {
					g.dispose();
				}
				screenHandler.swapScreens();
			}
			Toolkit.getDefaultToolkit().sync();

			if (LIMIT_FRAME_RATE) {
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
