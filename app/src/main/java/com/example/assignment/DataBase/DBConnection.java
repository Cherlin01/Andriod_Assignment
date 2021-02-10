package com.example.assignment.DataBase;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    String ip = "SQL5101.site4now.net";
    // This is default if you are using JTDS driver.
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    // Name of your database.
    String db = "DB_A6E005_cherlin01";
    // Username and password are required for security.
    String un = "DB_A6E005_cherlin01_admin";
    String password = "P@ssw0rd";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL;
        try {
            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"+ "database=" + db + ";user=" + un + ";password="+ password + ";";
            conn = DriverManager.getConnection(ConnURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return conn;
    }
}
