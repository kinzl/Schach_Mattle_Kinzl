package com.example.schach.client;

import javafx.scene.layout.GridPane;

import javax.security.auth.login.FailedLoginException;
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

    public MyClientThread() {
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
//            sendMessages();

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

    void receiveMessages(GridPane gridPane) {
        new Thread(() -> {
            boolean running = true;
            while (running) {
                try {
                    String s = reader.readObject().toString();
                    if (s.equals("sendInformationListToClient")) {
                        System.out.println("CLIENT RECEIVE");
                        informationList = (List<Information>) reader.readObject();
                        for (int i = 0; i < informationList.size(); i++) {
                            System.out.println(informationList.get(i).getFieldName() + " | " + informationList.get(i).getX() + " | " + informationList.get(i).getY());
                        }
                        ChessboardController.updateChessfield(informationList, gridPane);

                    }
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    void sendMessages() {
        boolean running = true;
        new Thread(() -> {
            while (running) {
                try {
                    writer.writeObject("");
                    writer.flush();
                    if (ChessboardController.informationListAdded) {
                        System.out.println("CLIENT SENDS");
                        ChessboardController.informationListAdded = false;
                        informationList = ChessboardController.informationList;
                        writer.writeObject("sendInformationListToServer");
                        writer.flush();
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
