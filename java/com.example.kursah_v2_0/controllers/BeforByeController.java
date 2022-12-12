package com.example.kursah_v2_0;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class BeforByeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView Bus_img;

    @FXML
    private Button No_but;

    @FXML
    private Button Yes_but;

    @FXML
    void initialize() {
        No_but.setOnAction(event -> {
//            Window tmp = No_but.getScene().getWindow();
//            tmp.hide();
//            tmp.setOnHidden(tmp.getOnHidden());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("hello-view.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Main.mainStage.setScene(new Scene(root));

//            Parent root = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.showAndWait();
        });
        Yes_but.setOnAction(event -> {
//            Window tmp = Yes_but.getScene().getWindow();
//            tmp.hide();
//            tmp.setOnHidden(tmp.getOnHidden());

            System.exit(0);
        });
    }

}