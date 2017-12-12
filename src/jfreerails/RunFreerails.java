package jfreerails;

import java.awt.DisplayMode;
import java.io.IOException;

import jfreerails.client.common.ScreenHandler;
import jfreerails.client.renderer.ViewLists;
import jfreerails.client.top.GUIClient;
import jfreerails.client.top.ViewListsImpl;
import jfreerails.controller.LocalConnection;
import jfreerails.server.GameServer;

/**
 * This class starts a server and a GUI client which connects to it.  It implements
 * Runnable so that it can be started in the AWT event thread without blocking other items in
 * the event queue.
 */
public class RunFreerails implements Runnable {

	int numberOfClients = 1;

	int mode = ScreenHandler.WINDOWED_MODE;

	DisplayMode displayMode = null;

	public static void main(String[] args) throws IOException {
		new RunFreerailsJFrame().show();
	}

	public void createClient(String mapName, int mode) throws IOException {
		long startTime = System.currentTimeMillis();
		GameServer gs = new GameServer(mapName);
		

		System.out.println("Will start " + numberOfClients + " clients.");
		ViewLists viewLists = null;
		for (int i = 0; i < numberOfClients; i++) {	
			
			LocalConnection connection = gs.getLocalConnection();		
			GUIClient gc = new GUIClient(connection);
			
			//We want to setup the screen handler before creating the view lists since  
			//ViewListsImpl creates images that are compatible with the current display settings 
			//and the screen handler may change the display settings.
			ScreenHandler screenHandler = new ScreenHandler(gc.getClientJFrame(), mode, displayMode);
			
			//Only create the view lists on the first iteration.
			if(null == viewLists){			
			 	viewLists = new ViewListsImpl(gs.getWorld());
			}
			
			//Add a title to the client's window so that we can distinguish between clients.
			String title = "Client "+(i+1);			
			gc.getClientJFrame().setTitle(title);
						
			gc.setViewLists(viewLists);
			gc.setServerControls(gs.getServerControls());
			gc.start(screenHandler);
		}
		long deltaTime = System.currentTimeMillis() - startTime;
		System.out.println("Time taken for startup: " + deltaTime + "ms");
	}

	public DisplayMode getDisplayMode() {
		return displayMode;
	}

	public int getMode() {
		return mode;
	}

	public int getNumberOfClients() {
		return numberOfClients;
	}

	public void setDisplayMode(DisplayMode mode) {
		displayMode = mode;
	}

	public void setMode(int i) {
		mode = i;
	}

	public void setNumberOfClients(int i) {
		numberOfClients = i;
	}

	public void run() {
		String map_name;

		map_name = "south_america";

		try {
			createClient(map_name, mode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
