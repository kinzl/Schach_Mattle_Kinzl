package com.example.schach.server;


import java.io.*;
import java.net.Socket;

public class MyServerThread extends Thread {

    private Socket socket = null;
    private boolean running;
    private static String username;
    private BufferedReader reader;
    private BufferedWriter writer;
    private static String clientUsername;


    public MyServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            running = true;


            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

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

            writer.write("handleUsername");
            writer.newLine();
            writer.flush();
            writer.write(username);
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getClientUsername() {
        return clientUsername;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        MyServerThread.username = username;
    }

}

