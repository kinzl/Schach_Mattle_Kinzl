package com.example.schach.server;


import com.example.schach.client.ChessboardController;
import com.example.schach.client.Information;
import com.example.schach.client.MessangerController;
import javafx.scene.layout.GridPane;

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

    public MyServerThread() {

    }

    public void run() {
        //ToDO: Methods of Server
        try {
            Socket socket = this.socket;
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
            handleUsername();
            System.out.println("SERVER started");
//            sendMessages();
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

    public void receiveMessages(GridPane gridPane) {
        new Thread(() -> {
            boolean running = true;
            while (running) {
                try {
                    String s = reader.readObject().toString();
                    if (s.equals("sendInformationListToServer")) {
                        System.out.println("SERVER RECEIVE");
                        informationList = (List<Information>) reader.readObject();
                        ChessboardController.updateChessfield(informationList, gridPane);
                    }

                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public void sendMessages() {
        boolean running = true;
        new Thread(() -> {
            while (running) {
                try {
                    writer.writeObject("");
                    writer.flush();
                    if (ChessboardController.informationListAdded) {
                        System.out.println("SERVER SENDS");
                        ChessboardController.informationListAdded = false;
                        informationList = ChessboardController.informationList;
                        for (int i = 0; i < informationList.size(); i++) {
                            System.out.println(informationList.get(i).getFieldName() + " | " + informationList.get(i).getX() + " | " + informationList.get(i).getY());
                        }
                        writer.writeObject("sendInformationListToClient");
                        writer.writeObject(informationList);
                        writer.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

