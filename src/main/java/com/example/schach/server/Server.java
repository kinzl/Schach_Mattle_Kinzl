package com.example.schach.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static int PORT = 22;

    public static void startServer() {
        scanPort();
        try(ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));)
        {




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void scanPort(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running){
            System.out.println("Please, type in your port: ");
            try{
                PORT = Integer.parseInt(scanner.nextLine());
                running = false;
            } catch (Exception e){
                System.out.println("Unguilty input, please try again");
            }
        }

    }
}
