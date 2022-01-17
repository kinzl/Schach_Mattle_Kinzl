package com.example.schach.client;

import com.example.schach.server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public TextField username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void joinAGame(ActionEvent actionEvent) {

    }

    public void createAGame(ActionEvent actionEvent) {

        Server.startServer();
    }
}
