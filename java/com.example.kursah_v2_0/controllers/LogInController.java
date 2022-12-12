package com.example.kursah_v2_0;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LogInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label ErrIO_label;

    @FXML
    private Label Err_label;

    @FXML
    private Button enter_but;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField pass_field;

    int count=0;

    @FXML
    void initialize() {
        enter_but.setOnAction(event -> {
            String login = login_field.getText().trim();
            Err_label.setText("");
            ErrIO_label.setText("");
            String pass = pass_field.getText().trim();
            if (count<6) {
                ++count;
                if (login.equals("") || !login.equals("root") || pass.equals("") || !pass.equals("root"))
                    Err_label.setText(Err_label.getText() + "Введен неверный логин или пароль!");
                 else {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("AddDelBus.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Parent root = loader.getRoot();
                    Main.mainStage.setScene(new Scene(root));
                }
            }/*else {
                ErrIO_label.setText(ErrIO_label.getText() + "Задержка минута.");
                count=0;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }*/
        });
    }
}
