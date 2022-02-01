package com.example.schach.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MessangerController implements Initializable {

    public TextField message;
    public ListView listView;
    private ObservableList observableList = FXCollections.observableArrayList();

    public void sendMessage(ActionEvent actionEvent) {
        String s = message.getText();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
