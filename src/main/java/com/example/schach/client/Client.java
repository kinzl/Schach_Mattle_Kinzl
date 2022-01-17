package com.example.schach.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String IPADDRESS;
    private boolean running = true;

    public Client(String IPADDRESS) {
        this.IPADDRESS = IPADDRESS;
    }

    public void connectToServer() {
        try(Socket socket = new Socket(IPADDRESS,22);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));)
         {

             String s = br.readLine();
             System.out.println(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean canConnect(){
        Socket socket;
        try{
            socket = new Socket(IPADDRESS, 22);
            return true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return false;
    }
}
