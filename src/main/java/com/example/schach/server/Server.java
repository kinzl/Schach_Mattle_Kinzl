package com.example.schach.server;
import com.example.schach.client.HelloController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int PORT;

    public static void main(String[] args) {
        try(ServerSocket socket = new ServerSocket(PORT);
        ) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
