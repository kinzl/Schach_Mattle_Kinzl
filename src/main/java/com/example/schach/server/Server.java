package com.example.schach.server;

import com.example.schach.client.ChessboardController;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService pool;
    private final List<MyServerThread> clients;
    private final int portNumber;
    private boolean stop;
    private boolean hasClient;
    private ChessboardController chessboard;

    public Server() {
        this.portNumber = 23;
        pool = Executors.newFixedThreadPool(3);
        clients = new ArrayList<>();
    }

    private void runServer() {
        System.out.println("SERVER: Waiting for client");
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            hasClient = true;
            System.out.println("SERVER: client connected");
            MyServerThread myServerThread = new MyServerThread(clientSocket, chessboard);
            myServerThread.run();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        for (MyServerThread st : clients) {
            st.stopServerTread();
        }
        stop = true;
        pool.shutdown();
    }

    public static String getIpAddress() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "No IP Address found";
    }

    public boolean hasClient() {
        return hasClient;
    }

    public void activate() {
        new Thread(this::runServer).start();
    }

    public void setChessboard(ChessboardController chessboard) {
        this.chessboard = chessboard;
    }
}
