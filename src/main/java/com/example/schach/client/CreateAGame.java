package com.example.schach.client;

import com.example.schach.server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAGame implements Initializable {

    public Text IpAddress;
    public Label label;
    public Button play;
    private Server server;

    public void startServer(ActionEvent actionEvent) throws IOException {
        //ToDo: Auf verbindung überprüfen
        server = new Server();
        server.activate();
        while (server.hasClient()) {
            label.setText("Connected");
        }
        label.setText("Waiting for Connection");

    }

    private void setIpAddress() {
        IpAddress.setText(Server.getIpAddress());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIpAddress();
        play.setDisable(true);
    }

    public void playChess(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Chessfield.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 350);
        stage.setTitle("Chessfield");
        stage.setScene(scene);
        stage.show();
    }
}
