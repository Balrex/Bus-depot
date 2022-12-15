package com.example.kursah_v2_0;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddDelBusController implements Initializable {

    @FXML
    public TableView in_park_table;
    @FXML
    public TableColumn inp_IdCol;
    @FXML
    public TableColumn inp_NumberCol;
    @FXML
    public TableColumn inp_NameCol;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddBus_but;

    @FXML
    private Label Clear_label;

    @FXML
    private Button DelAll_but;

    @FXML
    private Button DelBus_but;

    @FXML
    private Label Del_label;

    @FXML
    private Label Err_label;

    @FXML
    private Button GoOut_but;

    @FXML
    private TextField ID_filed;

    @FXML
    private TextField name_filed;

    @FXML
    private TextField number_filed;

    AddDelBus_BD db = new AddDelBus_BD();
    ObservableList<TableData> data = FXCollections.observableArrayList();

    @Override
    public void initialize (URL url, ResourceBundle resourcesBungle){
        AddBus_but.setOnAction(event ->{
            Clear_label.setText("");
            Del_label.setText("");
            Err_label.setText("");
            String number = number_filed.getText();
            String name = name_filed.getText();
            Pattern patternNumber = Pattern.compile("[Т]\\d{1,5}||[М]\\d{1,5}||\\d{1,5}||\\d{1,5}[к]");
            Matcher matcherNumber = patternNumber.matcher(number);
            Pattern patternName = Pattern.compile("([А-Я]{1}[а-я]{1,45})\\s([A-Я]{1}\\W[A-Я]{1}\\W)");
            Matcher matcherName = patternName.matcher(name);
            AddDelBus_BD tmp = new AddDelBus_BD();
            if (matcherName.matches()==true && matcherNumber.matches()==true && !number.equals("")) {
                try {
                    tmp.PunIn_BD(number, name);
                    Del_label.setText(Del_label.getText() + "Автобус добавлен в парк");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                TableData tmp_data = new TableData(number,name, String.valueOf(tmp.getLast_id()));
                data.add(tmp_data);
                inp_IdCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
                inp_NumberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
                inp_NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
                in_park_table.setItems(data);
            }else
                Err_label.setText(Err_label.getText() + "Данные введены неверно");
        });

        GoOut_but.setOnAction(event -> {
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

        DelBus_but.setOnAction(event ->{
            boolean marker=true;
            String id = ID_filed.getText();
            Pattern patternID = Pattern.compile("\\d{1,5}");
            Matcher matcherID = patternID.matcher(id);
            int ID=-1;
            if (matcherID.matches()==true)
                ID = Integer.parseInt(id);
            else
                marker=false;
            AddDelBus_BD tmp = new AddDelBus_BD();

            try {
                addInf(0);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (marker && ID>= tmp.getFirst_id() && ID<=tmp.getLast_id()) {
                Err_label.setText("");
                Del_label.setText("");
                Clear_label.setText("");
                try {
                    tmp.DelIn_BD(ID);
                    Del_label.setText(Del_label.getText() + "Автобус удален из парка");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }else{
                Clear_label.setText("");
                Del_label.setText("");
                Err_label.setText("");
                Err_label.setText(Err_label.getText() + "Данные введены неверно");
            }

            try {
                addInf(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            inp_IdCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            inp_NumberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
            inp_NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            in_park_table.setItems(data);
        });

        DelAll_but.setOnAction(event ->{
            AddDelBus_BD tmp = new AddDelBus_BD();
            Del_label.setText("");
            Err_label.setText("");
            Clear_label.setText("");
            Clear_label.setText(Clear_label.getText()+"БД очищена");
            try {
                addInf(0);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            try {
                tmp.DeleteAllBusses();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            addInf(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        inp_IdCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        inp_NumberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
        inp_NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        in_park_table.setItems(data);
    }
    private void addInf(int choice) throws SQLException, ClassNotFoundException {
        db.dbConnection = db.getDbConnection();
        Statement state = db.dbConnection.createStatement();
        ResultSet resalt = state.executeQuery("SELECT * FROM `in_park`");
        if (choice==1) {
            while (resalt.next()) {
                TableData tmp_data = new TableData(resalt.getString(3), resalt.getString(2), resalt.getString(4));
                data.add(tmp_data);
            }
        }else
            while (resalt.next())
                in_park_table.getItems().clear();
    }
}
