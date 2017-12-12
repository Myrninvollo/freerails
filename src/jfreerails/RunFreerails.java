package jfreerails;

import java.io.IOException;

import javax.swing.JFrame;

import jfreerails.client.common.GameLoop;
import jfreerails.client.common.ScreenHandler;
import jfreerails.client.renderer.ViewLists;
import jfreerails.controller.ServerGameEngine;
import jfreerails.world.top.World;

public class RunFreerails {

	/** @param args 
	 */
	public static void main(String[] args) throws IOException {

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
	 * Description of the Method
	 * 
	 * @param mapName The filename of the map to load.
	 * @param fullscreen 
	 * @param nogameloop 
	 */

	public static void createClient(String mapName, boolean fullscreen, boolean nogameloop) throws IOException {

		World world = OldWorldImpl.createWorldFromMapFile(mapName);

		ViewLists viewLists = new ViewListsImpl();
		
		GUIComponentFactoryImpl gUIComponentFactory = new GUIComponentFactoryImpl();


       	createClient(fullscreen, nogameloop, world, viewLists, gUIComponentFactory);

	}

	/** @param fullscreen 
	 * @param nogameloop 
	 * @param world 
	 * @param viewLists 
	 * @param gUIComponentFactory 
	 */
	public static void createClient(
		boolean fullscreen,
		boolean nogameloop,
		World world,
		ViewLists viewLists,
		GUIComponentFactoryImpl gUIComponentFactory) {
		 if (!viewLists.validate(world)) {
		        throw new IllegalArgumentException();
		    }
		
			ServerGameEngine gameEngine = new ServerGameEngine(world); 
			gUIComponentFactory.setup(viewLists, world, gameEngine);
			JFrame client = gUIComponentFactory.createClientJFrame();
		
			if (nogameloop) {
				client.setSize(740, 500);
				client.show();
			} else {
				
				ScreenHandler screenHandler = new ScreenHandler(client, fullscreen);
				GameLoop gameLoop = new GameLoop(screenHandler, gameEngine);
				Thread t = new Thread(gameLoop);
				t.start();
			}
	}

}
