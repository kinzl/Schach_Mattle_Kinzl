package com.example.schach.client;

import com.example.schach.server.Server;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAGame implements Initializable {

    public Text IpAddress;
    public Button play;
    public Text text;
    public Button startServerID;
    private Server server;
    private Scene scene;

    public void startServer() throws IOException {
        startServerID.setDisable(true);
        server = new Server();

        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Chessfield.fxml"));
        scene = new Scene(fxmlLoader.load());

        ChessboardController controller = fxmlLoader.getController();
        server.setChessboard(controller);

        server.activate();
        if (server.hasClient()) {
            text.setText("Connected");
            play.setDisable(false);
        } else {
            text.setText("Server started");
            play.setDisable(true);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIpAddress();
        play.setDisable(true);
    }

    public void playChess() {
        Stage stage = new Stage();
        stage.setTitle("Server Chessfield");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void Refresh() {
        if (server.hasClient()) {
            text.setText("Client connected");
            play.setDisable(false);
        } else {
            text.setText("Waiting for Connection");
            play.setDisable(true);
        }
    }

    private void setIpAddress() {
        IpAddress.setText(Server.getIpAddress());
    }

}
