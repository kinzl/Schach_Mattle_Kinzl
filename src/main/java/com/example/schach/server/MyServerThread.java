package com.example.schach.server;


import com.example.schach.client.ChessboardController;
import com.example.schach.client.Information;
import com.example.schach.client.MessangerController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServerThread implements Runnable {

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

    @Override
    public void run() {
        //ToDO: Methods of Server
        try {
            Socket socket = this.socket;
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
            handleUsername();
            System.out.println("SERVER started");
            while (running) {

                if (ChessboardController.informationListAdded) {
                    informationList = ChessboardController.informationList;
//                    for (int i = 0; i < informationList.size(); i++) {
//                        System.out.println(informationList.get(i));
//                    }
                    System.out.println("\n\n");
                    writer.writeObject("sendInformationList");
                    writer.flush();
                    writer.writeObject(informationList);
                    writer.flush();
//                    System.out.println("SERVER SENT");
                }

            }
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
}

