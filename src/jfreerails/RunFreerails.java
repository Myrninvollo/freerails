package jfreerails;

import javax.swing.JFrame;
import jfreerails.client.ClientJFrame;
import jfreerails.client.GUIComponentFactoryImpl;
import jfreerails.client.ViewLists;
import jfreerails.client.ViewListsImpl;
import jfreerails.lib.GameLoop;
import jfreerails.lib.ScreenHandler;
import jfreerails.world.World;
import jfreerails.world.WorldImpl;

public class RunFreerails {

	public static void main(String[] args) {

		boolean nogameloop = false;
		boolean fullscreen = false;
		for (int i = 0; i < args.length; i++) {

			if (args[i].equalsIgnoreCase("fullscreen")) {
				System.out.println("Will attempt to run in fullscreen mode...");
				fullscreen = true;
			}
			if (args[i].equalsIgnoreCase("nogameloop")) {
				System.out.println("Will run without the game loop in windowed mode");
				nogameloop = true;
			}
		}

		String map_name;

		map_name = "south_america.png";

		createClient(map_name, fullscreen, nogameloop);

	}

	/**
	 *  Description of the Method
	 *
	 *@param  mapName  The filename of the map to load.
	 */

	public static void createClient(String mapName, boolean fullscreen, boolean nogameloop) {

		World world = WorldImpl.createWorldFromMapFile(mapName);

		ViewLists viewLists = new ViewListsImpl();
		
		GUIComponentFactoryImpl gUIComponentFactory = new GUIComponentFactoryImpl();


       	createClient(fullscreen, nogameloop, world, viewLists, gUIComponentFactory);

	}

	public static void createClient(
		boolean fullscreen,
		boolean nogameloop,
		World world,
		ViewLists viewLists,
		GUIComponentFactoryImpl gUIComponentFactory) {
		 if (!viewLists.validate(world)) {
		        throw new IllegalArgumentException();
		    }
		
			
			gUIComponentFactory.setup(viewLists, world);
			JFrame client = new ClientJFrame(gUIComponentFactory);
		
			if (nogameloop) {
				client.setSize(740, 500);
				client.show();
			} else {
		
				ScreenHandler screenHandler = new ScreenHandler(client, fullscreen);
				GameLoop gameLoop = new GameLoop(screenHandler);
				Thread t = new Thread(gameLoop);
				t.start();
			}
	}

}
