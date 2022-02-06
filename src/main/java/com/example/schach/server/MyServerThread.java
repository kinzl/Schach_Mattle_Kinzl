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


            handleUsername(writer, reader);

            ChessboardController chessboardController = new ChessboardController();
            chessboardController.setStreams(writer, reader);
            messangerController = new MessangerController(writer, reader);

            while (running) {

                Object o = reader.readObject();

                if (o instanceof String) {
                    String s = o.toString();
                    System.out.println(s);

                    if(chessboardController.isInformationListAdded()) {
                        informationList = chessboardController.getInformationList();
                        writer.writeObject("sendInformationList");
                        writer.writeObject(informationList);
                    }


                }

            }
        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void stopServerTread() {
        running = true;
    }


    private void handleUsername(ObjectOutputStream writer, ObjectInputStream reader) {
        try {
            writer.writeObject(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}

