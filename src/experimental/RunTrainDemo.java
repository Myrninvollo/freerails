package experimental;

import jfreerails.lib.GameJFrame;

public class RunTrainDemo {

	public static void main(String[] args) {

		TrainDemo trackView = new TrainDemo();
		GameJFrame gameJFrame = new GameJFrame(trackView);
		gameJFrame.setFullScreen(false);
		gameJFrame.setGameModel(trackView);

		gameJFrame.startGameLoop();

	}
}
