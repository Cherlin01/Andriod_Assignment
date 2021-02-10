package com.example.assignment.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.DataModel.BookingDataModels;
import com.example.assignment.Fragment.DetailFragmentBooking;
import com.example.assignment.Fragment.MasterFragmentBookings;
import com.example.assignment.R;
import com.example.assignment.Session.Session;

public class Home extends AppCompatActivity implements MasterFragmentBookings.BookingSelectedListener{

    //Initialize Variables
    TextView Welcome;
    Session session;
    Button btnLogout, btnBook;
    ListView listBookings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Show Master Fragment and Find Elements
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        showMasterFragment();
        Welcome = findViewById(R.id.textView);
        session = new Session(Home.this);
        btnLogout = findViewById(R.id.btnLogout);
        btnBook = findViewById(R.id.btnBookAppt);
        listBookings = findViewById(R.id.listAppointment);


        //Logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Logout Successful!", Toast.LENGTH_SHORT).show();
                session.setLogin(false);
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
            }
        });


        //Set Welcome Text
        Welcome.setText("Welcome back," + session.getUserID() + "!");

        //Intent to Create Appointment
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAppointment = new Intent(Home.this, CreateAppointment.class);
                startActivity(intentAppointment);
            }
        });
    }

    //Show Master Fragment
    protected void showMasterFragment() {
        MasterFragmentBookings newFragmentToShow = new MasterFragmentBookings();
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, newFragmentToShow).commit();
    }

    //Show Appointment Detail Fragment based on Selected ID
    protected void showDetailFragmentOverMasterFragment(String selectedId) {
        DetailFragmentBooking newFragmentToShow = new DetailFragmentBooking();
        Bundle params = new Bundle();
        params.putString("BookingID", selectedId);
        newFragmentToShow.setArguments(params);
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, newFragmentToShow).addToBackStack(null).commit();
    }

    @Override
    public void onBookingSelected(BookingDataModels bookingDataModels) {
        showDetailFragmentOverMasterFragment(bookingDataModels.getId());
    }
}