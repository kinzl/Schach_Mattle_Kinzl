package com.example.schach.server;


import com.example.schach.client.ChessboardController;
import com.example.schach.client.Information;
import com.example.schach.client.MessangerController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServerThread extends Thread {

    private Socket socket = null;
    private boolean running;
    private static String username;
    private static String clientUsername;
    private static ObjectInputStream reader;
    private static ObjectOutputStream writer;
    private List<Information> informationList = new ArrayList<>();
    private MessangerController messangerController;
    private ChessboardController chessboardController;

    public static void setUsername(String username) {
        MyServerThread.username = username;
    }

    public MyServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Socket socket = this.socket;
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());

            running = true;


            handleUsername();

            chessboardController = new ChessboardController();

            messangerController = new MessangerController(writer, reader);
            System.out.println("SERVER started");
            while (running) {

                if (ChessboardController.informationListAdded) {
                    informationList = ChessboardController.informationList;
                    writer.writeObject("sendInformationList");
                    writer.writeObject(informationList);
                    System.out.println("SERVER SENT");
                }
Thread.sleep(200);

            }
        } catch (IOException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void stopServerTread() {
        running = true;
    }


    private void handleUsername() {
        try {
            writer.writeObject(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ObjectInputStream getReader() {
        return reader;
    }

    public static ObjectOutputStream getWriter() {
        return writer;
    }


}

