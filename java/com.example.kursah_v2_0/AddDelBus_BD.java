package com.example.kursah_v2_0;

import javafx.css.Size;

import java.sql.*;

public class AddDelBus_BD extends Config{
    Connection dbConnection;

    //подключение к БД
    public Connection getDbConnection() throws SQLException, ClassNotFoundException{
        String connStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connStr, dbLogin, dbPass);
        return dbConnection;
    }

    private int last_id;
    private int first_id;

    public int getLast_id(){return last_id;}
    public int getFirst_id(){return first_id;}

    {
        try {
            first_id = StartID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    {
        try {
            last_id = EndID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private int StartID() throws SQLException, ClassNotFoundException {
        String str = null;
        int marker=1;
        int star_id=0;
        dbConnection = getDbConnection();
        Statement state = dbConnection.createStatement();
        ResultSet resalt = state.executeQuery("SELECT * FROM `in_park`");
        while (resalt.next() && marker!=0) {
            str = resalt.getString(4);
            marker=0;
        }
        if (str==null)
            return star_id;
        else
            return star_id=Integer.parseInt(str);
    }
    private int EndID() throws SQLException, ClassNotFoundException {
        String str = null;
        int lastID=0;
        dbConnection = getDbConnection();
        Statement state = dbConnection.createStatement();
        ResultSet resalt = state.executeQuery("SELECT * FROM `in_park`");
        while (resalt.next()) {
            str = resalt.getString(4);
        }
        if (str==null)
            return lastID;
        else
            return lastID=Integer.parseInt(str);
    }

    //добавление автобуса в БД
    public void PunIn_BD(String number, String name) throws SQLException, ClassNotFoundException{
        this.last_id += 1;
        String Str_count = String.valueOf(last_id);
        String sql = "INSERT INTO `in_park`(`number`, `name`, `bus_id`) VALUES ('"+number+"','"+name+"','"+Str_count+"')";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.executeUpdate();
    }

    //удаление автобуса по индексу
    public void DelIn_BD(int id) throws SQLException, ClassNotFoundException{
        String tmp_sql = "DELETE FROM `in_park`WHERE bus_id =" + id + "";
        PreparedStatement prSt = getDbConnection().prepareStatement(tmp_sql);
        prSt.executeUpdate();
        if (id == last_id)
            last_id--;
    }

    //очиста всех БД
    public void DeleteAllBusses() throws SQLException, ClassNotFoundException{
        String tmp_sql = "DELETE FROM `in_park`";
        PreparedStatement prSt = getDbConnection().prepareStatement(tmp_sql);
        prSt.executeUpdate();

        tmp_sql = "DELETE FROM `parking`";
        prSt = getDbConnection().prepareStatement(tmp_sql);
        prSt.executeUpdate();

        last_id=0;
        first_id=0;
    }

    public void SentToParking() throws SQLException, ClassNotFoundException{
        String tmp_sql = "DELETE FROM `parking`";
        PreparedStatement prSt = getDbConnection().prepareStatement(tmp_sql);
        prSt.executeUpdate();

        dbConnection=getDbConnection();
        Statement state = dbConnection.createStatement();
        ResultSet resalt = state.executeQuery("SELECT * FROM `in_park`");
        while (resalt.next()){
            int id = Integer.parseInt(resalt.getString(4));
            tmp_sql = "INSERT INTO `parking` SELECT * FROM `in_park`WHERE bus_id =" + id + "";
            prSt = getDbConnection().prepareStatement(tmp_sql);
            prSt.executeUpdate();
        }
    }
}
