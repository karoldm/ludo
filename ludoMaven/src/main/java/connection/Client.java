package connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import controllers.ControladorJogo;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

/**
 *
 * @author Willian
 */
public class Client implements Runnable {

    private Socket socket;
    private Thread thread;
    private ServerSocket serverSocket;
    private InetAddress ip;
    private int port;
    private boolean turn;
    private ControladorJogo controller;

    /**
     *
     * @param controller
     */
    public Client(ControladorJogo controller) {
        this.controller = controller;
    }

//Sempre está escutando o recebimento de informações
    /**
     *
     */
    public void run() {
        while (true) {
            receiveMove();
            turn = true;
        }
    }

    /**
     *
     */
    public void initializeConnection() {
        try {
            this.turn = true;
            this.ip = InetAddress.getLocalHost();
            this.serverSocket = new ServerSocket(5000);
            this.port = this.serverSocket.getLocalPort();
            this.socket = this.serverSocket.accept();
            this.serverSocket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void sendBord() {
        try {
            this.myTurn = false;
            ObjectOutputStream out = new ObjectOutputStream(this.soc.getOutputStream());
            out.writeObject(move);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void receiveMove() {
        try {
            ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
//            Move move = (Move) in.readObject();
//            this.controller.setMove(move);
        } catch (IOException ex) {
            if (ex instanceof SocketException) {
//                this.controller.interrupt();
            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
        }
    }

    public void server() {
        Server server = new Server(this);
        this.thread = new Thread(server);
        this.thread.start();
    }
}
