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
    private static boolean hasClient = false;
    private int port = 23;
    private ServerSocket s = null;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startServer();
    }
    private static ArrayList<ServerThread> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(3);

    public void startServer() throws IOException {
        try {
            s = new ServerSocket(port);
            while (true) {
                Socket s1 = s.accept();
                System.out.println("Server connect");
                ServerThread st1 = new ServerThread(s1);
                clients.add(st1);

                pool.execute(st1);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally{
            s.close();
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
