package com.example.schach.server;


import com.example.schach.client.ChessboardController;
import com.example.schach.client.Information;
import com.example.schach.client.MessangerController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServerThread implements Serializable {

    private Socket socket;
    private boolean running = true;
    private static String serverUsername;
    private static String clientUsername;
    private static ObjectInputStream reader;
    private static ObjectOutputStream writer;
    private List<Information> informationList = new ArrayList<>();
    private MessangerController messangerController;
    private ChessboardController chessboardController = new ChessboardController();

    public static void setServerUsername(String serverUsername) {
        MyServerThread.serverUsername = serverUsername;
    }

    public MyServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        //ToDO: Methods of Server
        try {
            Socket socket = this.socket;
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
            handleUsername();
            System.out.println("SERVER started");
            sendMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void stopServerTread() {
        running = true;
    }

    private void handleUsername() {
        try {
            writer.writeObject(serverUsername);
            writer.flush();
            clientUsername = reader.readObject().toString();
            System.out.println("Clientusername: " + clientUsername);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ObjectInputStream getReader() {
        return reader;
    }

    public static ObjectOutputStream getWriter() {
        return writer;
    }

    public static String getServerUsername() {
        return serverUsername;
    }

    public static String getClientUsername() {
        return clientUsername;
    }

    private void receiveMessages() {
        new Thread(() -> {
            boolean running = true;
            while (running) {
                try {
                    System.out.println("Receive messages BEFORE");
                    String s = reader.readObject().toString();
                    System.out.println("SOMETHING RECEIVED: " + s);
                    if (s.equals("sendInformationList")) {
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
        new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(50);
                    writer.writeObject("");
                    writer.flush();
                    if (ChessboardController.informationListAdded) {
                        ChessboardController.informationListAdded = false;
                        informationList = ChessboardController.informationList;
//                        writer.reset();
                        writer.writeObject("sendInformationList");
                        writer.flush();
                        writer.writeObject(informationList);
                        writer.flush();
//                    System.out.println("SERVER SENT");
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

