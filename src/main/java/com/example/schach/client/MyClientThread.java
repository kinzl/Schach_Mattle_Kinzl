package com.example.schach.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyClientThread implements Serializable {
    private String IPADDRESS;
    private int PORT;
    private static ObjectInputStream reader;
    private static ObjectOutputStream writer;
    public static boolean isConnectedWithTheServer = false;
    private static String serverUsername;
    private static String clientUsername;
    private boolean running = true;
    private String s;
    private List<Information> informationList = new ArrayList<>();
    private ChessboardController chessboardController = new ChessboardController();


    public MyClientThread(String IPADDRESS, int PORT) {
        this.IPADDRESS = IPADDRESS;
        this.PORT = PORT;
    }

    public void run() {
        //ToDO: Methods of Client
        try {
            Socket socket = new Socket(IPADDRESS, PORT);
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
            isConnectedWithTheServer = true;
            handleUsername();
            System.out.println("CLIENT started");

            receiveMessages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUsername(String username) {
        MyClientThread.clientUsername = username;
    }

    private void handleUsername() {
        try {
            writer.writeObject(clientUsername);
            writer.flush();
            serverUsername = reader.readObject().toString();
            System.out.println("Serverusername: " + serverUsername);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getServerUsername() {
        return serverUsername;
    }

    public static String getClientUsername() {
        return clientUsername;
    }

    public static ObjectInputStream getReader() {
        return reader;
    }

    public static ObjectOutputStream getWriter() {
        return writer;
    }

    private void receiveMessages() {
        new Thread(() -> {
            boolean running = true;
            while (running) {
                try {
                    System.out.println("Receive messages BEFORE");
                    String s = reader.readObject().toString();
                    System.out.println("SOMETHING RECEIVED: " + s);
                    if(s.equals("sendInformationList")) {
                        System.err.println("HURRA");
                    }
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void sendMessages() {
        boolean running = true;
        System.out.println("Send messages");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("HURRA");
            }
        }).start();
    }
}
