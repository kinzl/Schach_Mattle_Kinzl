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

    public static boolean isIsConnectedWithTheServer() {
        return isConnectedWithTheServer;
    }
}
