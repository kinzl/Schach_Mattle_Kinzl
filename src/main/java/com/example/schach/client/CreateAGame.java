package com.example.schach.client;

import com.example.schach.server.MyServerThread;
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
    //public Label label;
    public Button play;
    public Text text;
    public Button startServerID;
    private Server server;

    public void startServer(ActionEvent actionEvent) throws IOException {
        startServerID.setDisable(true);
        //ToDo: Auf verbindung überprüfen
        server = new Server();
        server.activate();
        if (server.hasClient()) {
            //label.setText("Connected");
            text.setText("Connected");
            play.setDisable(false);
        } else {
            //label.setText("Waiting for Connection");
            text.setText("Server started");
            play.setDisable(true);
        }
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
        Scene scene = new Scene(fxmlLoader.load());
        ChessboardController.setStreams(MyServerThread.getWriter(), MyServerThread.getReader());
        stage.setTitle("Server Chessfield");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void Refreh(ActionEvent actionEvent) {
        if(server.hasClient()){
            //label.setText("Client connected");
            text.setText("Client connected");
            play.setDisable(false);
        } else {
            //label.setText("Waiting for Connection");
            text.setText("Waiting for Connection");
            play.setDisable(true);
        }

    }
}
