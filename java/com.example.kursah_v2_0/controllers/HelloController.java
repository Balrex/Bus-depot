package com.example.kursah_v2_0;

import java.io.IOException;
import java.net.URL;
import java.security.Signature;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button No_but;

    @FXML
    private Button Yes_but;

    @FXML
    public ImageView image;

    @FXML
    void initialize() {
        No_but.setOnAction(event -> {
            AddDelBus_BD tmp = new AddDelBus_BD();
            try {
                tmp.SentToParking();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Second-view.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Main.mainStage.setScene(new Scene(root));
        });

        Yes_but.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LogIn.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Main.mainStage.setScene(new Scene(root));
        });
    }

}

