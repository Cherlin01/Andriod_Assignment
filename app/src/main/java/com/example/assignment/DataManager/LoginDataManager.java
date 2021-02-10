package com.example.assignment.DataManager;

import com.example.assignment.DataBase.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDataManager {

    //DB Connection
    DBConnection connectionClass;
    Connection connect;

    public String GetLoginStatus(String AdminNumber, String Password) {
        //Initialize Database Connection
        connectionClass = new DBConnection();

        //Open connection
        connect = connectionClass.CONN();

        Integer ID;
        try {
            String query = "select * from Account where AdminNo=? and Password = ? ";
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setString(1, AdminNumber);
            stmt.setString(2, Password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String Status = "Pass";

                return Status;
            }
            //Close ResultSet and connection
            rs.close();
            connect.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
