package com.example.schach.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class MessangerController implements Initializable {

    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    public TextField message;
    public ListView listView;

    public MessangerController(ObjectOutputStream writer, ObjectInputStream reader) {
        this.writer = writer;
        this.reader = reader;
    }

    private ObservableList observableList = FXCollections.observableArrayList();

    public void sendMessage(ActionEvent actionEvent) {
        String s = message.getText();
        try {
            writer.writeObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        observableList.add(s);

    }

    public static void receiveMessage() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(observableList);
    }
}
