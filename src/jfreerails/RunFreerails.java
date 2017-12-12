package jfreerails;

import javax.swing.JFrame;
import jfreerails.client.GUIComponentFactory;
import jfreerails.client.GUIComponentFactoryImpl;
import jfreerails.client.GameLoop;
import jfreerails.client.NewClientJFrame;
import jfreerails.client.ScreenHandler;
import jfreerails.client.ViewLists;
import jfreerails.client.ViewListsImpl;
import jfreerails.world.World;
import jfreerails.world.WorldImpl;

public class RunFreerails {

	public static void main(String[] args) {

		boolean fullscreen = false;
		for (int i = 0; i < args.length; i++) {

			if (args[0].equalsIgnoreCase("fullscreen")) {
				System.out.println("Will attempt to run in fullscreen mode...");
				fullscreen = true;
			}
		}

		String map_name;

		map_name = "south_america.png";

		createClient(map_name, fullscreen);

	}

	/**
	 *  Description of the Method
	 *
	 *@param  mapName  The filename of the map to load.
	 */

	private static void createClient(String mapName, boolean fullscreen) {

		World world = new WorldImpl(mapName);

		ViewLists viewLists = new ViewListsImpl();
		if (!viewLists.validate(world)) {
			throw new IllegalArgumentException();
		}

		GUIComponentFactory gUIComponentFactory =
			new GUIComponentFactoryImpl(viewLists, world);

		JFrame client = new NewClientJFrame(gUIComponentFactory);

		ScreenHandler screenHandler = new ScreenHandler(client, fullscreen);

		client.show();

		GameLoop gameLoop = new GameLoop(screenHandler);
		gameLoop.run();

	}

}
