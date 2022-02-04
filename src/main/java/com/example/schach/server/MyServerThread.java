package com.example.schach.server;


import java.io.*;
import java.net.Socket;

public class MyServerThread extends Thread {

    private Socket socket = null;
    private boolean running;
    private static String username;
    private static String clientUsername;
    private static ObjectInputStream reader;
    private static ObjectOutputStream writer;

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



            while (running) {
                //s = reader.readLine();
                //TODO: Connection to Server
                //System.out.println("Server received from Client: " + clientUsername);
//                running = false;
                Object o = reader.readObject();
                System.out.println(o.toString());

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
}

