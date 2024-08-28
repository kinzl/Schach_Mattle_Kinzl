package com.example.schach.server;


import com.example.schach.client.ChessboardController;
import com.example.schach.client.Information;

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
    ChessboardController chessboardController;

    public static void setServerUsername(String serverUsername) {
        MyServerThread.serverUsername = serverUsername;
    }

    public MyServerThread(Socket socket, ChessboardController chessboardController) {
        this.socket = socket;
        this.chessboardController = chessboardController;
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
            receiveMessages();
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

    public static String getServerUsername() {
        return serverUsername;
    }

    public static String getClientUsername() {
        return clientUsername;
    }

    private void receiveMessages() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                    String s = reader.readObject().toString();
                    if (s.equals("sendInformationList")) {
                        informationList = (List<Information>) reader.readObject();
                        for (Information information : informationList) {
                            System.out.println(information.getFieldName() + " " + information.getX() + " " + information.getY());
                        }
                        chessboardController.updateChessfield();
                    }

                } catch (ClassNotFoundException | IOException | InterruptedException e) {
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

