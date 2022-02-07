package com.example.schach.client;

import com.example.schach.server.MyServerThread;
import com.example.schach.server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public TextField username;
    public Text status;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void joinAGame(ActionEvent actionEvent) throws IOException {
        if (username.getText().isEmpty()) {
            System.out.println("join a game : Not valid");
            status.setText("Please, enter your username");
        } else {
            MyClientThread.setUsername(username.getText());
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("joinAGame.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Join a Game");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        }
    }

    public void createAGame(ActionEvent actionEvent) throws IOException {
        if (username.getText().isEmpty()) {
            System.out.println("Create a game : Not valid");
            status.setText("Please, enter your username");
        } else {
            MyServerThread.setUsername(username.getText());
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("createAGame.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Create a Game");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }


    }

}
