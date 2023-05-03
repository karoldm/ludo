package connection;

public class Host implements Runnable {

    /**
     * Objeto da classe Connection
     */
    private Connection connection;

    /**
     * Construtor da classe
     *
     * @param connection Connection
     */
    public Host(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     */
    @Override
    public void run() {
        connection.initializeConnection();
    }

}
