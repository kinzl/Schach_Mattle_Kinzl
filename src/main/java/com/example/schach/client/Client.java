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

    public Client(String IPADDRESS) {
        this.IPADDRESS = IPADDRESS;
        pool = Executors.newFixedThreadPool(3);
        clientThreads = new ArrayList<>();
    }

    private void runClient() {
        System.out.println("CLIENT: Waiting for Server");

//        stop = false;
//
//        MyClientThread myClientThread = new MyClientThread(IPADDRESS, PORT);
//        pool.execute(myClientThread);

        MyClientThread myClientThread = new MyClientThread(IPADDRESS, PORT);
        myClientThread.run();

    }

    public void activate() {
        new Thread(() -> runClient()).start();
    }

}
