package com.example.schach.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private String IPADDRESS;
    private int PORT = 23;
    private final ExecutorService pool;
    private final List<MyClientThread> clientThreads;
    private boolean stop;
    private ChessboardController chessboardController;

    public Client(String IPADDRESS) {
        this.IPADDRESS = IPADDRESS;
        pool = Executors.newFixedThreadPool(3);
        clientThreads = new ArrayList<>();
    }

    private void runClient() {
        System.out.println("CLIENT: Waiting for Server");
        MyClientThread myClientThread = new MyClientThread(IPADDRESS, PORT, chessboardController);
        myClientThread.run();
    }

    public void activate() {
        new Thread(this::runClient).start();
    }

    public void setChessboardController(ChessboardController chessboardController) {
        this.chessboardController = chessboardController;
    }
}
