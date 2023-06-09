package connection;

import controllers.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Move;

public class Connection implements Runnable {

    /**
     * Objeto da classe Socket para a troca de mensagens durante o jogo
     */
    private Socket socket;

    /**
     * Thread que aguarda um novo oponente
     */
    private Thread hostThread;

    /**
     * Socket que aguarda um novo oponente
     */
    private ServerSocket socketServidor;

    /**
     * Objeto que guarda o IP
     */
    private InetAddress ip;

    /**
     * Guarda a porta
     */
    private int port;

    /**
     * Objeto da classe ControladorJogo
     */
    public Controller controller;

    /**
     * Construtor da classe
     *
     * @param controller ControladorJogo
     */
    public Connection(Controller controller) {
        this.controller = controller;
    }

    /**
     *
     * @return
     */
    public InetAddress getIp() {
        return ip;
    }

    /**
     *
     * @return
     */
    public int getPort() {
        return port;
    }

    /**
     *
     * @param ip
     */
    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    /**
     *
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     *
     */
    @Override
    public void run() {
        while (true) {
            receiveMove();
        }
    }

    /**
     * Cria a conexão do host
     */
    public void initializeConnection() {
        try {
            this.ip = InetAddress.getLocalHost();
            this.socketServidor = new ServerSocket(5000);
            this.port = this.socketServidor.getLocalPort();
            this.socket = this.socketServidor.accept();
            //this.socketServidor.close();
            this.controller.jogadorConectado();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Envia o movimento realizado pelo jogador
     *
     * @param move
     */
    public void sendMove(Move move) {
        try {
            if (this.socket != null) {
                ObjectOutputStream out = new ObjectOutputStream(this.socket.getOutputStream());
                out.writeObject(move);
                System.out.println("Movimento enviado");
            }
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Recebe o movimento realizado pelo jogador
     */
    private void receiveMove() {
        try {
            ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
            Move move = (Move) in.readObject();
            this.controller.updateMove(move);
            System.out.println("Tabuleiro recebido");
        } catch (IOException ex) {
            if (ex instanceof SocketException) {
                this.controller.interrupt();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Conecta com o host
     */
    public void connect() {
        try {
            this.socket = new Socket(ip, port);
            System.out.println("Conectado");
            this.controller.jogadorConectado();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Falha na conexão!");
        }
    }

    /**
     * Disconecta
     */
    public void disconnect() {
        try {
            this.socket.close();
            this.socket = null;
        } catch (IOException ex) {
            Logger.getLogger(Connection.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cria a thread do host
     */
    public void host() {
        Host h = new Host(this);
        this.hostThread = new Thread(h);
        this.hostThread.start();
    }

    /**
     * Mata a thread do host
     */
    public void cancelHost() {
        if(this.hostThread != null){
            this.hostThread.interrupt();
        }
    }

    public Socket getSocket() {
        return socket;
    }

}
