package com.example.schach.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String IPADDRESS;
    private int PORT = 23;
    private boolean running = true;
    public static boolean isConnectedWithTheServer = false;
    private Socket socket;
    private BufferedWriter bw;
    private BufferedReader br;

    public Client(String IPADDRESS) {
        this.IPADDRESS = IPADDRESS;
    }

    public static void main(String[] args) {
        Client client = new Client("localhost");
        client.connectToServer();
    }

    public void connectToServer() {
        String hostname = "localhost";
        int port = 23;

        try {
            Socket socket = new Socket(hostname, port);
            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);


            System.out.println("HURRA");


        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
