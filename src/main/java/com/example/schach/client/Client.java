package com.example.schach.client;

import java.io.*;
import java.net.Socket;

public class Client {
    private String IPADDRESS;

    public Client(String IPADDRESS) {
        this.IPADDRESS = IPADDRESS;
    }

    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost",22);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));)
         {




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
