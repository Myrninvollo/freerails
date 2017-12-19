package freerails.network;

import freerails.controller.MessageToServer;
import freerails.controller.MessageStatus;
import freerails.controller.ServerControlInterface;

/**
 * A request to save the game.
 *
 */
public class SaveGameMessageToServer implements MessageToServer {
    private static final long serialVersionUID = 3257281452725777209L;

    private final int id;
    private final String filename;

    /**
     *
     * @param id
     * @param s
     */
    public SaveGameMessageToServer(int id, String s) {
        this.id = id;
        this.filename = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SaveGameMessageToServer))
            return false;

        final SaveGameMessageToServer saveGameMessageToServer = (SaveGameMessageToServer) o;

        if (id != saveGameMessageToServer.id)
            return false;
        return filename.equals(saveGameMessageToServer.filename);
    }

    @Override
    public int hashCode() {
        int result;
        result = id;
        result = 29 * result + filename.hashCode();
        return result;
    }

    /**
     *
     * @return
     */
    public int getID() {
        return id;
    }

    /**
     *
     * @param server
     * @return
     */
    public MessageStatus execute(ServerControlInterface server) {
        try {
            server.savegame(filename);

            return new MessageStatus(id, true);
        } catch (Exception e) {
            return new MessageStatus(id, false, e.getMessage());
        }
    }
}