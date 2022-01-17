package com.example.schach.client;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class testFile {
    public static void main(String[] args) {
        try {
            System.out.println(Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
