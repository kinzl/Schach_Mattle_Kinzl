module com.example.schach {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.schach.client to javafx.fxml;
    exports com.example.schach.client;
}
