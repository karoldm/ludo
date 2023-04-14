/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import controllers.ControladorJogo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class Server {

    private ServerSocket serverSocket;
    private ControladorJogo controller;
    private String message;

    public Server(int port, ControladorJogo controller) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.controller = controller;
            start();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void start() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            handleClient(clientSocket);
        }
    }

    private void handleClient(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        message = in.readLine();
        clientSocket.close();
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
