package com.example.schach.server;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket socket;

    public ServerThread(Socket socket)  throws IOException {
        this.socket = socket;
    }
    @Override
    public void run() {
        System.out.println("Connected");
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader br = new BufferedReader( new InputStreamReader(socket.getInputStream()));

            bw.write("This is written by the Server");
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
