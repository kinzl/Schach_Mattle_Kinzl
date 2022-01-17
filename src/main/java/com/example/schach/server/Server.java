package com.example.schach.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server {
    private static int PORT;

    public static void main(String[] args) {
        scanPort();
        try(ServerSocket socket = new ServerSocket(PORT);
        ) {

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
