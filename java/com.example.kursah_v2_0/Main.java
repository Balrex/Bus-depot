package com.example.kursah_v2_0;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setTitle("Bus depot.");
        stage.setScene(new Scene(root, 700, 500));
        stage.show();
        mainStage = stage;
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage mainStage = null;
}
