package connection;

/**
 *
 * @author Willian
 */
public class Server implements Runnable {

    private Client client;

    public Server(Client client) {
        this.client = client;
    }

    public void run() {
        client.initializeConnection();
    }

}
