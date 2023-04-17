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
import javax.swing.JOptionPane;

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
            receiveDado();
            turn = true;
        }
    }

    public void connect() {
        try {
            this.turn = false;
            this.socket = new Socket(ip, port);
            JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Falha na conexão!");
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

    public void sendDado(String information) {
        try {
            this.turn = false;
            ObjectOutputStream out = new ObjectOutputStream(this.socket.getOutputStream());
            out.writeObject(information);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void receiveDado() {
        try {
            ObjectInputStream input = new ObjectInputStream(this.socket.getInputStream());
            System.out.println((String) input.readObject());
//            this.controller.setMove(move);
        } catch (IOException ex) {
            if (ex instanceof SocketException) {
//                this.controller.interrupt();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hostServer() {
        Server server = new Server(this);
        this.thread = new Thread(server);
        this.thread.start();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public ControladorJogo getController() {
        return controller;
    }

    public void setController(ControladorJogo controller) {
        this.controller = controller;
    }

}
