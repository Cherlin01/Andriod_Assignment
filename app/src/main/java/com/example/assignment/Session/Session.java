package com.example.assignment.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    //Initialize variable
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    //Create Constructor
    public Session(Context context)
    {
        sharedPreferences = context.getSharedPreferences("AppKey", 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    //Create set login Method
    public void setLogin(boolean login){
        editor.putBoolean("KEY_LOGIN", login);
        editor.commit();
    }

    //Create get login method
    public boolean getLogin(){
        return sharedPreferences.getBoolean("KEY_LOGIN", false);
    }

    //Create set username method
    public void setUsername(String Username)
    {
        editor.putString("KEY_USERNAME", Username);
        editor.commit();
    }

    //Create Get Username method
    public String getUserID()
    {
        return sharedPreferences.getString("KEY_USERNAME", "");
    }
}
