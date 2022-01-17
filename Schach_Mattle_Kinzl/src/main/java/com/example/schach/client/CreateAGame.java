package com.example.schach.client;

import com.example.schach.server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAGame implements Initializable {
    public Text IpAddress;
    public Text status;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIpAddress();
        isConnected();

    }

    private void setIpAddress(){
        IpAddress.setText(Server.getIpAddress());
    }

    private void isConnected(){

        if(Server.isIsRunning()){
            status.setStyle("-fx-text-fill: green");
            status.setText("Connected");
        } else {

            status.setText("Waiting for Connection");
            status.setStyle("-fx-text-fill: red");
        }
    }

    public void startServer(ActionEvent actionEvent) {
        Server server = new Server();
        server.startServer();
    }
}
