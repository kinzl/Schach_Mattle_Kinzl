package com.example.schach.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
    private String IPADDRESS;
    private int PORT = 23;
    private boolean running = true;
    public static boolean isConnectedWithTheServer = false;
    private Socket socket;
    private static String serverUsername;
    private static String username;
    private BufferedWriter writer;
    private BufferedReader reader;
    private static boolean isClientWhite = true;
    private static boolean isServerWhite = false;

    public Client(String IPADDRESS) {
        this.IPADDRESS = IPADDRESS;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(IPADDRESS, PORT);
            isConnectedWithTheServer = true;


            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("CLIENT: Connected to Server");

            handleUsername();

            while (running) {

                //TODO: Connection to Server
                System.out.println("Server received from Client: " + serverUsername);

                running = false;


            }
        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

            //ex.printStackTrace();
        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
            //ex.printStackTrace();
        }
    }

    private void handleUsername(){
        try {
            writer.write("handleUsername");
            writer.newLine();
            writer.flush();
            writer.write(username);
            writer.newLine();
            writer.flush();

            String s = reader.readLine();
            if(s.equals("handleUsername")) {
                serverUsername = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUsername(String username) {
        Client.username = username;
    }

    public static String getServerUsername() {
        return serverUsername;
    }

    public static String getUsername() {
        return username;
    }

    public static boolean isIsClientWhite() {
        return isClientWhite;
    }

    public static boolean isIsServerWhite() {
        return isServerWhite;
    }
}
