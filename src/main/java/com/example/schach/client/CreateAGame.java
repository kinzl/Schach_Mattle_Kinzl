package com.example.schach.client;

import com.example.schach.server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAGame implements Initializable {
    public Text status;
    public Text IpAddress;
    public Label label;


    public void startServer(ActionEvent actionEvent) throws IOException {

        Server server = new Server();
        server.startServer();
        while (server.hasClient()) {
            status.setText("Connected");
            label.setText("Connected");
            label.setTextFill(Color.GREEN);
            break;
        }
        status.setText("Waiting for Connection...");
        label.setText("Waiting for Connection...");
        label.setTextFill(Color.RED);
    }

    private void setIpAddress() {
        IpAddress.setText(Server.getIpAddress());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIpAddress();
    }
}
