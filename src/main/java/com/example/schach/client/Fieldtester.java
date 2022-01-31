package com.example.schach.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Fieldtester extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Chessfield.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("join a game");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e)
        {
            e.printStackTrace();
        }



    }

    public static void main(String[] args) {
        launch();
    }
}
