package com.example.assignment.DataManager;

import com.example.assignment.DataBase.DBConnection;
import com.example.assignment.DataModel.AppointmentSlotDataModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentSlotDataManager {
    //DB Connection
    DBConnection connClass;
    Connection conn;


    public AppointmentSlotDataModel GetSlotbyDate(String SelectedDate) {
        //Initialize DB Connection
        connClass = new DBConnection();

        //Open Connection
        conn = connClass.CONN();

        try {
            String qry = "SELECT * from AppointmentSlot WHERE Date=?";
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, SelectedDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String Date = rs.getString(2);
                String Eight = rs.getString(3);
                String Ten = rs.getString(4);
                String Twelve = rs.getString(5);
                String Two = rs.getString(6);
                String Four = rs.getString(7);

                AppointmentSlotDataModel appointmentSlotDataModel = new AppointmentSlotDataModel(Date, Eight, Ten, Twelve, Two, Four);
                return appointmentSlotDataModel;
            }
            //Close Result Set and Connection
            rs.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void AddAppointmentSlot(String SelectedDate){
        //Initialize DB Connection
        connClass = new DBConnection();

        //Open Connection
        conn = connClass.CONN();

        String InsertQuery = "INSERT INTO AppointmentSlot(Date, Eight, Ten, Twelve, Two, Four) VALUES (?,?,?,?,?,?)";
        try{
            PreparedStatement Insertstmt = conn.prepareStatement(InsertQuery);
            Insertstmt.setString(1, SelectedDate);
            Insertstmt.setString(2, "0");
            Insertstmt.setString(3, "0");
            Insertstmt.setString(4, "0");
            Insertstmt.setString(5, "0");
            Insertstmt.setString(6, "0");
            Insertstmt.executeUpdate();

            //Close Connection
            conn.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void UpdateAppointmentSlot(String SelectedDate, int Slot1, int Slot2, int Slot3, int Slot4, int Slot5){
        //Initialize DB Connection
        connClass = new DBConnection();

        //Open Connection
        conn = connClass.CONN();

        String UpdateQuery = "UPDATE AppointmentSlot SET Eight=?, Ten=?, Twelve=?, Two=?, Four=? WHERE Date=?";
        try{
            PreparedStatement Updatestmt = conn.prepareStatement(UpdateQuery);
            Updatestmt.setInt(1, Slot1);
            Updatestmt.setInt(2, Slot2);
            Updatestmt.setInt(3, Slot3);
            Updatestmt.setInt(4, Slot4);
            Updatestmt.setInt(5, Slot5);
            Updatestmt.setString(6, SelectedDate);
            Updatestmt.executeUpdate();

            //Close Connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
