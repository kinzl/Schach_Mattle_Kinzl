package com.example.schach.server;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    private static int PORT = 23;
    private static boolean hasClient = false;

    public static void main(String[] args) {
        Server s = new Server();
        s.startServer();
    }
    
    public void startServer() {
        boolean running = true;
        try {
            System.out.println(Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket socket = serverSocket.accept();
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
             hasClient = true;


            System.out.println(hasClient);

            bw.write("This was written by the server");

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static boolean hasClient() {
        return hasClient;
    }

    public static String getIpAddress() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "no Ip Address found";
    }
}
