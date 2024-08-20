package com.example.schach.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JoinAGame implements Initializable {

    public TextField ipAddress;
    public Text status;
    public Button playButton;
    public Button connectButtonID;
    private Client client;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playButton.setDisable(true);
    }

    public void connectToServer(ActionEvent actionEvent) throws IOException {
        if (isValidIpAddress(ipAddress.getText())) {
            client = new Client(ipAddress.getText());

            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Chessfield.fxml"));
            scene = new Scene(fxmlLoader.load());

            ChessboardController controller = fxmlLoader.getController();
            client.setChessboardController(controller);

            client.activate();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (MyClientThread.isConnectedWithTheServer) {
                status.setText("Connected");
                connectButtonID.setDisable(true);
                playButton.setDisable(false);
            } else {
                status.setText("No Server found");
            }
        }
    }


    private boolean isValidIpAddress(String ipAddress) {
        //Checks if the entered ip address is valid
        if (ipAddress.isEmpty()) {
            status.setText("Please enter a Ip Address");
            return false;
        }
        String[] temp = ipAddress.split("\\.");
        if (temp.length == 4 || ipAddress.equals("localhost")) {
            status.setText("Connecting...");
            return true;
        } else {
            status.setText("Invalid IP Address");
        }
        return false;

    }


    public void play(ActionEvent actionEvent) throws IOException {
        //Opens new Window(Chessfield.fxml
        Stage stage = new Stage();

        stage.setTitle("Client Chessfield");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
