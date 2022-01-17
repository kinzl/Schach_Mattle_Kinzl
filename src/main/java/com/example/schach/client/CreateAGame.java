package com.example.schach.client;

import com.example.schach.server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAGame implements Initializable{


    public Text status;
    public Text IpAddress;
    private Server server = new Server();

    public void startServer(ActionEvent actionEvent) {
        server.startServer();
    }

    private void setIpAddress(){
        IpAddress.setText(Server.getIpAddress());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIpAddress();
    }
}
