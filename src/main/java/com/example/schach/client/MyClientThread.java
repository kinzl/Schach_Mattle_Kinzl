package com.example.schach.client;

import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class MyClientThread implements Runnable, Serializable {
    private String IPADDRESS;
    private int PORT;
    private static ObjectInputStream reader;
    private static ObjectOutputStream writer;
    public static boolean isConnectedWithTheServer = false;
    private static String serverUsername;
    private static String clientUsername;
    private boolean running = true;
    private String s;

    public MyClientThread(String IPADDRESS, int PORT) {
        this.IPADDRESS = IPADDRESS;
        this.PORT = PORT;
    }

    @Override
    public void run() {
        //ToDO: Methods of Client

        try {
                Socket socket = new Socket("localhost", 23);
                reader = new ObjectInputStream(socket.getInputStream());
                writer = new ObjectOutputStream(socket.getOutputStream());

            isConnectedWithTheServer = true;

            handleUsername(writer, reader);

            while (running) {
                s = reader.readObject().toString();

                if(s.equals("updateChessfield")) {

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void setUsername(String username) {
        MyClientThread.clientUsername = username;
    }

    private void handleUsername(ObjectOutputStream writer, ObjectInputStream reader){
        try {
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

    public static ObjectInputStream getReader() {
        return reader;
    }

    public static ObjectOutputStream getWriter() {
        return writer;
    }
}
