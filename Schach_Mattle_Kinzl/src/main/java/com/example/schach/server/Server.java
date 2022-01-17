package com.example.schach.server;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    private static int PORT = 22;
    private static boolean isRunning = false;


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
            isRunning = true;


                System.out.println(isRunning);

                bw.write("This was written by the server");

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

        }
    }

    public static boolean isIsRunning() {
        return isRunning;
    }

    public static String getIpAddress() {
        try {

            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }
}
