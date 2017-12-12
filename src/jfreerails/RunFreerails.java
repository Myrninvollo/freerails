package jfreerails;

import java.awt.DisplayMode;
import java.io.IOException;
import java.net.InetAddress;

import jfreerails.client.common.ScreenHandler;
import jfreerails.client.top.GUIClient;
import jfreerails.server.GameServer;
import jfreerails.util.FreerailsProgressMonitor;

/**
 * This class allows a server and/or client to be configured and started.
 */
public class RunFreerails {

	boolean done = false;

	FreerailsProgressMonitor monitor = FreerailsProgressMonitor.NULL_INSTANCE;

	private InetAddress remoteServer;

	int numberOfClients = 1;

	int mode = ScreenHandler.WINDOWED_MODE;

	DisplayMode displayMode = null;
	
	private GameServer localServer;

	public RunFreerails() {
	}

	public RunFreerails(FreerailsProgressMonitor monitor) {
		this.monitor = monitor;
	}

	public static void main(String[] args) throws IOException {
		new RunFreerailsJFrame().show();
	}

	/**
	 * Starts the server in a new thread.
	 * @return a reference to the server
	 */
	public GameServer startServer() {
		long startTime = System.currentTimeMillis();
		String map_name;
		GameServer gs = null;
		map_name = "south_america";
		gs = createServer(map_name);
		localServer = gs;
		System.out.println("Time taken to start server: " +
			(System.currentTimeMillis() - startTime) + "ms");
		return gs;
	}

	/**
	 * Starts one or more clients in separate threads.
	 */
	public void startClients() {
		try {
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void start() throws IOException {
		long startTime = System.currentTimeMillis();
		System.out.println("Will start "+numberOfClients+ " clients.");
		for(int i = 0 ; i < numberOfClients; i ++){		
		    String title = "Client " + (i + 1);
		    if (remoteServer == null) {
			if (localServer == null) {
			    throw new IllegalStateException("Must start a "
			    + "server unless connecting remotely!");
			}
			GUIClient gc = new
			    GUIClient(localServer.getLocalConnection(),
				mode, displayMode, title, monitor);
			gc.setServerControls(localServer.getServerControls());
		    } else {
			GUIClient gc = new GUIClient(remoteServer, mode,
				displayMode, title, monitor);
		    }
		}
		long deltaTime = System.currentTimeMillis() - startTime;
		System.out.println("Time taken to start clients: " +
			deltaTime + "ms");
	}
	
	private GameServer createServer(String mapName)
	{
		return new GameServer(mapName, monitor);
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
	}

	public void setRemoteServer(InetAddress address) {
	    remoteServer = address;
	}
}
