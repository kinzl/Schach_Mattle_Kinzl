package com.example.schach.client;

import com.example.schach.server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class JoinAGame implements Initializable {

    public TextField ipAddress;
    public Text status;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isConnected();
    }

    public void connectToServer(ActionEvent actionEvent) {
        Client client = new Client(ipAddress.getText());
        client.connectToServer();

    }

    private void isConnected(){
        if(Server.isConnected()){
            status.setStyle("-fx-text-fill: green");
            status.setText("Connected");
        } else {

            status.setText("Waiting for Connection");
            status.setStyle("-fx-text-fill: red");
        }
    }


}
