package com.example.kursah_v2_0;

import java.sql.*;

public class DB_IN_OUT extends Config{
    static int OutOfPark=0;
    Connection dbConnection;

    public Connection getDbConnection() throws SQLException, ClassNotFoundException{
        String connStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connStr, dbLogin, dbPass);
        return dbConnection;
    }

    public void Departure(int id) throws SQLException, ClassNotFoundException{
        boolean marker=false;
        String str;
        dbConnection = getDbConnection();
        Statement state = dbConnection.createStatement();
        ResultSet resalt = state.executeQuery("SELECT * FROM `parking`");
        while (resalt.next() && !marker) {
            str = resalt.getString(4);
            int tmp_int =Integer.parseInt(str);
            if (tmp_int==id)
                marker = true;
        }
        if (marker)
            ++OutOfPark;

        String tmp_sql = "INSERT INTO `tookoff` SELECT * FROM `parking`WHERE bus_id =" + id + "";
        PreparedStatement prSt = getDbConnection().prepareStatement(tmp_sql);
        prSt.executeUpdate();

        tmp_sql = "DELETE FROM `parking`WHERE bus_id =" + id + "";
        prSt = getDbConnection().prepareStatement(tmp_sql);
        prSt.executeUpdate();
    }

    public void Arrival (int id) throws SQLException, ClassNotFoundException{
        boolean marker=false;
        String str;
        dbConnection = getDbConnection();
        Statement state = dbConnection.createStatement();
        ResultSet resalt = state.executeQuery("SELECT * FROM `tookoff`");
        while (resalt.next() && !marker) {
            str = resalt.getString(4);
            int tmp_int =Integer.parseInt(str);
            if (tmp_int==id)
                marker=true;
        }
        if (marker)
            OutOfPark--;

        String tmp_sql = "INSERT INTO `parking` SELECT * FROM `tookoff`WHERE bus_id =" + id + "";
        PreparedStatement prSt = getDbConnection().prepareStatement(tmp_sql);
        prSt.executeUpdate();

        tmp_sql = "DELETE FROM `tookoff`WHERE bus_id =" + id + "";
        prSt = getDbConnection().prepareStatement(tmp_sql);
        prSt.executeUpdate();
    }
}
