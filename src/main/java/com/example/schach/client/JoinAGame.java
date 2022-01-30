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
    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playButton.setDisable(true);

    }

    public void connectToServer(ActionEvent actionEvent) {

        if(isValidIpAddress(ipAddress.getText())){
            client = new Client(ipAddress.getText());
            client.start();
            if(Client.isConnectedWithTheServer){
                status.setText("Connected");
                playButton.setDisable(false);
            } else {
                status.setText("No Server found");
            }

        }
    }


    private boolean isValidIpAddress(String ipAddress){
        //Checks if the entered ip address is valid
        if(ipAddress.isEmpty()){
            status.setText("Please enter Ip Address");
            return false;
        }
        String[] temp = ipAddress.split("\\.");
        if(temp.length == 4 || ipAddress.equals("localhost")) {
            status.setText("Connecting...");
            return true;
        }else {
            status.setText("Invalid IP Address");
        }
        return false;

    }


    public void play(ActionEvent actionEvent) throws IOException {
        //Opens new Window(Chessfield.fxml
        Client client = new Client(ipAddress.getText());
        client.start();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Chessfield.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Chessfield");
        stage.setScene(scene);
        stage.show();
    }
}
