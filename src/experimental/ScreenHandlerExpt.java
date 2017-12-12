package experimental;

import javax.swing.JFrame;

import jfreerails.client.common.GameLoop;
import jfreerails.client.common.ScreenHandler;

public class ScreenHandlerExpt extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new ScreenHandlerExpt();
		frame.show();
		ScreenHandler sh = new ScreenHandler(frame, false);
		GameLoop gl=new GameLoop(sh);
		gl.run();
	}

}
