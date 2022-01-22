package com.example.schach.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String IPADDRESS;
    private int PORT = 23;
    private boolean running = true;
    public static boolean isConnectedWithTheServer = false;

    public Client(String IPADDRESS) {
        this.IPADDRESS = IPADDRESS;
    }

    public void connectToServer() {
        try
         {
             Socket socket = new Socket(IPADDRESS,PORT);
             isConnectedWithTheServer = true;
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


             String s = br.readLine();
             System.out.println(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
