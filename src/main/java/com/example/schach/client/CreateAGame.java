package com.example.schach.client;

import com.example.schach.server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAGame implements Initializable{

    public Text status;
    public Text IpAddress;

    public void startServer(ActionEvent actionEvent) throws IOException {
        Server server = new Server();
        server.startServer();

        if(Server.hasClient()){
            status.setText("Client found");
            System.out.println("Client found");
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 350);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } else {
            status.setText("No Client connected ...");
            System.out.println("No Client connected ...");
        }
    }

    private void setIpAddress(){
        IpAddress.setText(Server.getIpAddress());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIpAddress();

    }
}
