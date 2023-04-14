/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import controllers.ControladorJogo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Willian
 */
public class Client {

    private String hostname;
    private int port;
    private ControladorJogo controler;

    public Client(String hostname, int port, ControladorJogo controler) {
        this.hostname = hostname;
        this.port = port;
        this.controler = controler;
    }

    public void sendMessage(String message) {
        try {
            Socket socket = new Socket(hostname, port);
            OutputStream out = socket.getOutputStream();
            out.write(message.getBytes());
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }

    }
}
