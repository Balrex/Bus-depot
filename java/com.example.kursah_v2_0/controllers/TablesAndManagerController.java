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

public class TablesAndManagerController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Arrival_but;

    @FXML
    private Button Departure_but;

    @FXML
    private Button End_but;

    @FXML
    private Label IDErr_label;

    @FXML
    private TextField IdIn_filed;

    @FXML
    private TextField IdOut_filed;

    @FXML
    public TableColumn<TableData, String> in_IdCol;
    @FXML
    public TableColumn<TableData, String> in_NumberCol;
    @FXML
    public TableColumn<TableData,String> in_NameCol;
    @FXML
    public TableColumn<TableData, String> off_IdCol;
    @FXML
    public TableColumn<TableData, String> off_NumberCol;
    @FXML
    public TableColumn<TableData, String> off_NameCol;

    @FXML
    private TableView<TableData> parking_table;

    @FXML
    private TableView<TableData> tookoff_table;
    DB_IN_OUT db = new DB_IN_OUT();
    ObservableList<TableData> data1 = FXCollections.observableArrayList();
    ObservableList<TableData> data2 = FXCollections.observableArrayList();

    @Override
    public void initialize (URL url, ResourceBundle resourcesBungle){
        Departure_but.setOnAction(event ->{
            boolean marker=true;
            IDErr_label.setText("");
            String id = IdOut_filed.getText();
            Pattern patternID = Pattern.compile("\\d{1,5}");
            Matcher matcherID = patternID.matcher(id);
            int ID=-1;
            if (matcherID.matches()==true)
                ID = Integer.parseInt(id);
            else
                marker=false;
            AddDelBus_BD tmp_orig = new AddDelBus_BD();

            try {
                addInf1(0);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (marker && ID>=tmp_orig.getFirst_id() && ID<=tmp_orig.getLast_id()){
                DB_IN_OUT tmp = new DB_IN_OUT();
                try {
                    tmp.Departure(ID);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }else
                IDErr_label.setText(IDErr_label.getText()+"Вы ввели неверное значение");

            try {
                addInf1(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            in_IdCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            in_NumberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
            in_NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            parking_table.setItems(data1);

            try {
                addInf2(0);
                addInf2(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            off_IdCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            off_NumberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
            off_NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            tookoff_table.setItems(data2);
        });

        Arrival_but.setOnAction(event ->{
            boolean marker=true;
            IDErr_label.setText("");
            String id = IdIn_filed.getText();
            Pattern patternID = Pattern.compile("\\d{1,5}");
            Matcher matcherID = patternID.matcher(id);
            int ID=-1;
            if (matcherID.matches()==true)
                ID = Integer.parseInt(id);
            else
                marker=false;
            AddDelBus_BD tmp_orig = new AddDelBus_BD();

            try {
                addInf2(0);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (marker && ID>=tmp_orig.getFirst_id() && ID<=tmp_orig.getLast_id()){
                DB_IN_OUT tmp = new DB_IN_OUT();
                try {
                    tmp.Arrival(ID);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }else
                IDErr_label.setText(IDErr_label.getText()+"Вы ввели неверное значение");

            try {
                addInf2(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            off_IdCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            off_NumberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
            off_NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            tookoff_table.setItems(data2);

            try {
                addInf1(0);
                addInf1(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            in_IdCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            in_NumberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
            in_NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            parking_table.setItems(data1);
        });

        End_but.setOnAction(event -> {
            IDErr_label.setText("");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BeforBye.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Main.mainStage.setScene(new Scene(root));
        });

        try {
            addInf1(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        in_IdCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        in_NumberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
        in_NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        parking_table.setItems(data1);

        try {
            addInf2(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        off_IdCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        off_NumberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
        off_NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tookoff_table.setItems(data2);
    }

    private void addInf1(int action) throws SQLException, ClassNotFoundException {
        db.dbConnection = db.getDbConnection();
        Statement state = db.dbConnection.createStatement();
        ResultSet resalt = state.executeQuery("SELECT * FROM `parking`");
        if (action == 1) {
            while (resalt.next()) {
                TableData tmp_data = new TableData(resalt.getString(3), resalt.getString(2), resalt.getString(4));
                data1.add(tmp_data);
            }
        } else {
            while (resalt.next()) {
                parking_table.getItems().clear();
            }
        }
    }
    private void addInf2(int action) throws SQLException, ClassNotFoundException {
        db.dbConnection = db.getDbConnection();
        Statement state = db.dbConnection.createStatement();
        ResultSet resalt = state.executeQuery("SELECT * FROM `tookoff`");
        if (action == 1) {
            while (resalt.next()) {
                TableData tmp_data = new TableData(resalt.getString(3), resalt.getString(2), resalt.getString(4));
                data2.add(tmp_data);
            }
        } else
            while (resalt.next())
                tookoff_table.getItems().clear();
    }
}
