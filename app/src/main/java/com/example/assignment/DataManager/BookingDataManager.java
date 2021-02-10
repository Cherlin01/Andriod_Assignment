package com.example.assignment.DataManager;

import com.example.assignment.DataBase.DBConnection;
import com.example.assignment.DataModel.AppointmentSlotDataModel;
import com.example.assignment.DataModel.BookingDataModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class BookingDataManager {
    //DB Connection
    DBConnection connClass;
    Connection conn;

    public BookingDataModels[] GetAllBookings(String AdminNo) {

        //Initialize DB Connection
        connClass = new DBConnection();

        //Open Connection
        conn = connClass.CONN();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date Today = Calendar.getInstance().getTime();
        String DateToday = dateFormat.format(Today);
        ArrayList<BookingDataModels> booking = new ArrayList<>();

        try {
            String qry = "SELECT * FROM Bookings WHERE Date>=? AND AdminNo=?";
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, DateToday);
            stmt.setString(2, AdminNo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String ID = rs.getString(1);
                String date = rs.getString(3);
                String time = rs.getString(4);

                BookingDataModels bookingDataModels = new BookingDataModels(ID, date, time);
                booking.add(bookingDataModels);
            }

            //Close Result Set and Connection
            rs.close();
            conn.close();
            return booking.toArray(new BookingDataModels[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BookingDataModels GetBookingbyDate(String SelectedDate, String AdminNo) {
        //Initialize DB Connection
        connClass = new DBConnection();

        //Open Connection
        conn = connClass.CONN();

        try {
            String qry = "SELECT * from Bookings WHERE Date=? AND AdminNo=?";
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, SelectedDate);
            stmt.setString(2, AdminNo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String ID = rs.getString(1);
                String date = rs.getString(3);
                String time = rs.getString(4);

                BookingDataModels bookingDataModels = new BookingDataModels(ID, date, time);
                return bookingDataModels;
            }
            //Close Result Set and Connection
            rs.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BookingDataModels GetBookingById(String selectedId) {
        //Initialize DB Connection
        connClass = new DBConnection();

        //Open Connection
        conn = connClass.CONN();

        try {
            String qry = "SELECT * from Bookings WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, selectedId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String ID = rs.getString(1);
                String date = rs.getString(3);
                String time = rs.getString(4);

                BookingDataModels bookingDataModels = new BookingDataModels(ID, date, time);
                return bookingDataModels;
            }
            //Close Result Set and Connection
            rs.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void AddBooking(String AdminNo, String SelectedDate, String Time) {
        //Initialize DB Connection
        connClass = new DBConnection();

        //Open Connection
        conn = connClass.CONN();

        String InsertQuery = "INSERT INTO Bookings(AdminNo, Date, Time, Attendance) VALUES (?,?,?,?)";
        try {
            PreparedStatement Insertstmt = conn.prepareStatement(InsertQuery);
            Insertstmt.setString(1, AdminNo);
            Insertstmt.setString(2, SelectedDate);
            Insertstmt.setString(3, Time);
            Insertstmt.setString(4, "False");
            Insertstmt.executeUpdate();
            //Close Connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Delete Booking
    public void DelBooking(String ID){
        //Initialize DB Connection
        connClass = new DBConnection();

        //Open Connection
        conn = connClass.CONN();

        //Delete SQL
        String DeleteQuery = "DELETE Bookings WHERE ID=?";
        try{
            PreparedStatement Deletestmt = conn.prepareStatement(DeleteQuery);
            Deletestmt.setString(1, ID);
            Deletestmt.executeUpdate();

            //Close Connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
