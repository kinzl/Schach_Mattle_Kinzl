package com.example.schach.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientThread implements Serializable {
    private final String IPADDRESS;
    private final int PORT;
    private static ObjectInputStream reader;
    private static ObjectOutputStream writer;
    public static boolean isConnectedWithTheServer = false;
    private static String serverUsername;
    private static String clientUsername;
    private List<Information> informationList = new ArrayList<>();
    private final ChessboardController chessboardController;

    public ClientThread(String IPADDRESS, int PORT, ChessboardController chessboardController) {
        this.IPADDRESS = IPADDRESS;
        this.PORT = PORT;
        this.chessboardController = chessboardController;
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
            sendMessages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUsername(String username) {
        ClientThread.clientUsername = username;
    }

    private void handleUsername() {
        try {
            writer.writeObject(clientUsername);
            writer.flush();
            serverUsername = reader.readObject().toString();
            System.out.println("Serverusername: " + serverUsername);
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
