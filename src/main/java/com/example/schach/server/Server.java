package com.example.schach.server;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private boolean hasClient = false;
    private int port = 23;
    private ServerSocket serverSocket = null;
    private static Socket socket;

    private static ArrayList<ServerThread> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newCachedThreadPool();

    public void startServer() throws IOException {
        System.out.println("Server started");
        try {
            serverSocket = new ServerSocket(port);
            ServerThread st1 = null;
                waitForConnection();

                if(socket != null){
                    st1 = new ServerThread(socket);
                }

                clients.add(st1);

                if(st1 != null){
                    pool.execute(st1);
                }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally{
            serverSocket.close();
        }

    }

    private void waitForConnection(){
        new Runnable(){
            @Override
            public void run() {
                System.out.println(" Waiting for someone to connect... \n");
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(" Now connected to " + socket.getInetAddress().getHostName());
            }};
    }

    public boolean hasClient() {
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
