package com.example.schach.server;


import java.io.*;
import java.net.Socket;

public class MyServerThread extends Thread {

    private Socket socket = null;
    private boolean running;
    private static String username;
    private static String clientUsername;

    public static void setUsername(String username) {
        MyServerThread.username = username;
    }

    public MyServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(Socket socket = this.socket;
        ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
        ) {
            running = true;

            writer.writeObject("Hello");
            String s = reader.readObject().toString();
            System.out.println(s);
            handleUsername();


            while (running) {
                //s = reader.readLine();
                //TODO: Connection to Server
                System.out.println("Server received from Client: " + clientUsername);
                running = false;


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


    private void handleUsername(){
//        try {
//            String s = reader.readLine();
//            if(s.equals("handleUsername")) {
//                clientUsername = reader.readLine();
//            }
//
//            writer.write("handleUsername");
//            writer.newLine();
//            writer.flush();
//            writer.write(username);
//            writer.newLine();
//            writer.flush();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static String getClientUsername() {
        return clientUsername;
    }
}

