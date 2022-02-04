package com.example.schach.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MyClientThread implements Runnable {
    private String IPADDRESS;
    private int PORT;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    public static boolean isConnectedWithTheServer = false;
    private static String serverUsername;
    private static String username;

    public MyClientThread(String IPADDRESS, int PORT) {
        this.IPADDRESS = IPADDRESS;
        this.PORT = PORT;
    }

    @Override
    public void run() {
        //ToDO: Methods of Client

        try (
                Socket socket = new Socket("localhost", 23);
                ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
        ) {
            isConnectedWithTheServer = true;

            String s = reader.readObject().toString();
            System.out.println("CLIENT received: " + s);
            writer.writeObject("foo");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void setUsername(String username) {
        MyClientThread.username = username;
    }

    public static String getServerUsername() {
        return serverUsername;
    }

    public static String getUsername() {
        return username;
    }
}
