package jfreerails;

import java.awt.DisplayMode;
import java.io.IOException;
import java.net.InetAddress;
import jfreerails.client.common.ScreenHandler;
import jfreerails.client.top.GUIClient;
import jfreerails.server.GameServer;
import jfreerails.util.FreerailsProgressMonitor;
import jfreerails.controller.ServerControlInterface;


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

    /**
     * The GameServer we create
     */
    private GameServer localServer;

    /**
     * The game we want to start
     */
    private ServerControlInterface game;

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
    public ServerControlInterface startServer() {
        long startTime = System.currentTimeMillis();
        String map_name;
        map_name = "south_america";
        game = createServer(map_name);
        System.out.println("Time taken to start server: " +
            (System.currentTimeMillis() - startTime) + "ms");

        return game;
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
        System.out.println("Will start " + numberOfClients + " clients.");

        for (int i = 0; i < numberOfClients; i++) {
            String title = "Client " + (i + 1);

            if (remoteServer == null) {
                if (game == null) {
                    throw new IllegalStateException("Must start a " +
                        "server unless connecting remotely!");
                }

                GUIClient gc = new GUIClient(game, game.getLocalConnection(),
                        mode, displayMode, title, monitor);
            } else {
                GUIClient gc = new GUIClient(remoteServer, mode, displayMode,
                        title, monitor);
            }
        }

        long deltaTime = System.currentTimeMillis() - startTime;
        System.out.println("Time taken to start clients: " + deltaTime + "ms");
    }

    private ServerControlInterface createServer(String mapName) {
        localServer = new GameServer();

        return localServer.getNewGame(mapName, monitor);
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