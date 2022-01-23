package com.example.schach.client;

import com.example.schach.server.Server;
import com.example.schach.server.ServerThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateAGame implements Initializable{
    public Text status;
    public Text IpAddress;


    public void startServer(ActionEvent actionEvent) throws IOException {
        status.setText("Waiting for Connection...");
        ServerSocket serverSocket = new ServerSocket(23);
        Socket socket = serverSocket.accept();
        status.setText("Connected");
    }

    private void setIpAddress(){
        IpAddress.setText(Server.getIpAddress());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIpAddress();
    }
}
