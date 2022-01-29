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

//    public static void main(String[] args) {
//        Client client = new Client("localhost");
//        client.start();
//    }

    public void start() {

        try {
            socket = new Socket(IPADDRESS, PORT);
            isConnectedWithTheServer = true;
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
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
