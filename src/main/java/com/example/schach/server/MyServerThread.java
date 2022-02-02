package com.example.schach.server;


import java.io.*;
import java.net.Socket;

public class MyServerThread extends Thread {

    private Socket socket = null;
    private boolean running;
    private static String username;
    private ObjectInputStream reader;
    private ObjectOutputStream  writer;
    private static String clientUsername;

    public static void setUsername(String username) {
        MyServerThread.username = username;
    }

    public MyServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            running = true;

            System.out.println("SERVER: before");
            reader = new ObjectInputStream(socket.getInputStream());
            System.out.println("SERVER: middle");
            writer = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("SERVER: after");

            handleUsername();
            String s;

            while (running) {
                //s = reader.readLine();
                //TODO: Connection to Server
                System.out.println("Server received from Client: " + clientUsername);
                running = false;


            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    void stopServerTread() {
        running = true;
    }


    private void handleUsername(){
        try {
            String s = reader.readLine();
            if(s.equals("handleUsername")) {
                clientUsername = reader.readLine();
            }

//            writer.write("handleUsername");
//            writer.newLine();
//            writer.flush();
//            writer.write(username);
//            writer.newLine();
//            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getClientUsername() {
        return clientUsername;
    }
}

