package com.example.schach.client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private String IPADDRESS;
    private int PORT;
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost",47800);
        ) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
