package com.example.schach.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private BufferedWriter writer;
    private BufferedReader reader;
    private String IPADDRESS;
    private int PORT = 23;
    private boolean running = true;

    private Socket socket;


    private static boolean isClientWhite = true;
    private static boolean isServerBlack = false;

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

        stop = false;

        MyClientThread myClientThread = new MyClientThread(IPADDRESS, PORT);
        pool.execute(myClientThread);

    }

    public void activate() {
        new Thread(() -> runClient()).start();
    }



    public static boolean isIsClientWhite() {
        return isClientWhite;
    }

    public static boolean isIsServerBlack() {
        return isServerBlack;
    }

}
