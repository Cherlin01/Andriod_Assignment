package com.example.assignment.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.DataManager.AppointmentSlotDataManager;
import com.example.assignment.DataManager.BookingDataManager;
import com.example.assignment.DataModel.AppointmentSlotDataModel;
import com.example.assignment.DataModel.BookingDataModels;
import com.example.assignment.R;
import com.example.assignment.Session.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CreateAppointment extends AppCompatActivity {
    //Initialize Variables
    CalendarView calendarDate;
    TextView textViewTime, txtDate;
    Button btnBook, btnSelectDate, btnBacktoAllAppt, btnBacktoSelectDate;
    Spinner spinnerTime;
    Session session;
    ArrayList<String> time = new ArrayList<String>();
    String DateSelected, DateSelect, DateSelectedFormat;
    Date SelectedDateTime, SelectedDateTimes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        //Format Date
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        //Find elements in layout
        btnBook = findViewById(R.id.btnbook);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        spinnerTime = findViewById(R.id.spinnerTime);
        textViewTime = findViewById(R.id.textViewTime);
        txtDate = findViewById(R.id.txtDate);
        calendarDate = findViewById(R.id.calendarDate);
        btnBacktoAllAppt = findViewById(R.id.btnBacktoAllAppt);
        btnBacktoSelectDate = findViewById(R.id.btnBackSelectDate);


        //set Min and Max Date (within 2weeks)
        Calendar twoWeeksLater = Calendar.getInstance();
        Calendar oneDayLater = Calendar.getInstance();
        twoWeeksLater.add(Calendar.DATE, 15);
        oneDayLater.add(Calendar.DATE, 1);
        calendarDate.setMinDate((oneDayLater.getTimeInMillis()));
        calendarDate.setMaxDate(twoWeeksLater.getTimeInMillis());


        //Initialize Session
        session = new Session(CreateAppointment.this);

        //Set Selected Date
        calendarDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                DateSelected = year + "-" + (month + 1) + "-" + (dayOfMonth);
                DateSelect = year + "-" + (month + 1) + "-" + (dayOfMonth + 1);
                try {
                    SelectedDateTime = dateFormat.parse(DateSelect);
                    SelectedDateTimes = dateFormat.parse(DateSelected);
                    DateSelectedFormat = dateFormat.format(SelectedDateTimes);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        //Back to All Appointments
        btnBacktoAllAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAppointment = new Intent(CreateAppointment.this, Home.class);
                startActivity(intentAppointment);
            }
        });

        //Select Date
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.clear();
                AppointmentSlotDataManager appointmentSlotDataManager = new AppointmentSlotDataManager();
                Calendar selectedDate = Calendar.getInstance();

                //Validate Date
                if (SelectedDateTime == null) {
                    Toast.makeText(CreateAppointment.this, "Please Select a Date", Toast.LENGTH_SHORT).show();
                } else {
                    selectedDate.setTime(SelectedDateTime);


                    int Day = selectedDate.get(Calendar.DAY_OF_WEEK);
                    if (Day == Calendar.SATURDAY || Day == Calendar.SUNDAY) {
                        Toast.makeText(CreateAppointment.this, "You cannot book an appointment on Weekends!", Toast.LENGTH_SHORT).show();
                    } else {
                        //Check if User has bookings on that date
                        BookingDataManager bookingDataManager = new BookingDataManager();
                        BookingDataModels bookings = bookingDataManager.GetBookingbyDate(DateSelectedFormat, session.getUserID());

                        if (bookings != null) {
                            Toast.makeText(CreateAppointment.this, "You have an Appointment on this date!", Toast.LENGTH_SHORT).show();
                        } else {
                            //Check if Appointment Slot has that Date, if not, Create new Date Slot
                            if (appointmentSlotDataManager.GetSlotbyDate(DateSelectedFormat) == null)
                                appointmentSlotDataManager.AddAppointmentSlot(DateSelectedFormat);

                            AppointmentSlotDataModel appointmentSlotDataModel = appointmentSlotDataManager.GetSlotbyDate(DateSelectedFormat);


                            //Add Time to Spinner based on availability.
                            spinnerTime.setVisibility(View.VISIBLE);
                            if (Integer.parseInt(appointmentSlotDataModel.getEight()) < 5) {
                                time.add("8am - 10am");
                            }
                            if (Integer.parseInt(appointmentSlotDataModel.getTen()) < 5) {
                                time.add("10am - 12pm");
                            }
                            if (Integer.parseInt(appointmentSlotDataModel.getTwelve()) < 5) {
                                time.add("12pm - 2pm");
                            }
                            if (Integer.parseInt(appointmentSlotDataModel.getTwo()) < 5) {
                                time.add("2pm - 4pm");
                            }
                            if (Integer.parseInt(appointmentSlotDataModel.getFour()) < 5) {
                                time.add("4pm - 6pm");
                            }
                            if (time == null) {
                                time.add("No Time Slot Available");
                            }
                            ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(CreateAppointment.this, android.R.layout.simple_list_item_1, time);

                            //Set Visibility
                            spinnerTime.setAdapter(timeAdapter);
                            txtDate.setText("Selected Date: " + DateSelected);
                            calendarDate.setVisibility(View.GONE);
                            textViewTime.setVisibility(View.VISIBLE);
                            btnBook.setVisibility(View.VISIBLE);
                            btnSelectDate.setVisibility(View.GONE);
                            btnBacktoAllAppt.setVisibility(View.GONE);
                            btnBacktoSelectDate.setVisibility(View.VISIBLE);

                        }
                    }
                }
            }
        });

        //Back to Select Date
        btnBacktoSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerTime.setVisibility(View.GONE);
                txtDate.setText("Select Date:");
                calendarDate.setVisibility(View.VISIBLE);
                textViewTime.setVisibility(View.GONE);
                btnBook.setVisibility(View.GONE);
                btnSelectDate.setVisibility(View.VISIBLE);
                btnBacktoAllAppt.setVisibility(View.VISIBLE);
                btnBacktoSelectDate.setVisibility(View.GONE);
            }
        });

        //Book Appointment
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize data manager
                BookingDataManager bookingDataManager = new BookingDataManager();
                AppointmentSlotDataManager appointmentSlotDataManager = new AppointmentSlotDataManager();
                AppointmentSlotDataModel appointmentSlotDate = appointmentSlotDataManager.GetSlotbyDate(DateSelectedFormat);

                //Get Time Slots
                Integer slotEight = Integer.parseInt(appointmentSlotDate.getEight()),
                        slotTen = Integer.parseInt(appointmentSlotDate.getTen()),
                        slotTwelve = Integer.parseInt(appointmentSlotDate.getTwelve()),
                        slotTwo = Integer.parseInt(appointmentSlotDate.getTwo()),
                        slotFour = Integer.parseInt(appointmentSlotDate.getFour());
                String slotTime = "";
                String selectedTime = spinnerTime.getSelectedItem().toString();

                //Update Selected time slot to +1
                if (selectedTime != "No Time Slot Available") {
                    bookingDataManager.AddBooking(session.getUserID(), DateSelectedFormat, selectedTime);
                    Toast.makeText(CreateAppointment.this, "Appointment Added!", Toast.LENGTH_SHORT).show();

                    if (selectedTime.equals("8am - 10am")) {
                        slotEight = slotEight + 1;
                    }
                    if (selectedTime.equals("10am - 12pm")) {
                        slotTen = slotTen + 1;
                    }
                    if (selectedTime.equals("12pm - 2pm")) {
                        slotTwelve = slotTwelve + 1;
                    }
                    if (selectedTime.equals("2pm - 4pm")) {
                        slotTwo = slotTwo + 1;
                    }
                    if (selectedTime.equals("4pm - 6pm")) {
                        slotFour = slotFour + 1;
                    }

                    appointmentSlotDataManager.UpdateAppointmentSlot(DateSelectedFormat, slotEight, slotTen, slotTwelve, slotTwo, slotFour);

                    //Set Visibility
                    spinnerTime.setVisibility(View.GONE);
                    textViewTime.setVisibility(View.GONE);
                    btnBook.setVisibility(View.GONE);
                    btnSelectDate.setVisibility(View.VISIBLE);
                    calendarDate.setVisibility(View.VISIBLE);
                    txtDate.setText("Select Date");
                    Intent intentAppointment = new Intent(CreateAppointment.this, Home.class);
                    startActivity(intentAppointment);
                } else {
                    Toast.makeText(CreateAppointment.this, "Appointment cannot be added!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}