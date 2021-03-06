package com.example.assignment.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment.Activity.CreateAppointment;
import com.example.assignment.Activity.Home;
import com.example.assignment.DataManager.AppointmentSlotDataManager;
import com.example.assignment.DataManager.BookingDataManager;
import com.example.assignment.DataModel.AppointmentSlotDataModel;
import com.example.assignment.DataModel.BookingDataModels;
import com.example.assignment.R;
import com.example.assignment.Session.Session;


public class DetailFragmentBooking extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Initialize Variables and Find Elements
        View view = inflater.inflate(R.layout.detail_fragment_bookings, container, false);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        TextView textViewTime = view.findViewById(R.id.textViewTime);
        Button btnDel = view.findViewById(R.id.btnDelete);
        Button btnBack = view.findViewById(R.id.btnBack);
        Bundle params = this.getArguments();
        final String selectedId = params.getString("BookingID");
        final BookingDataManager bookingDataManager = new BookingDataManager();
        final BookingDataModels selectedBooking = bookingDataManager.GetBookingById(selectedId);
        final String selectedTime = selectedBooking.getTime();

        if(selectedBooking != null){
            textViewDate.setText(selectedBooking.getDate());
            textViewTime.setText(selectedBooking.getTime());
        }

        //Back to All Appointments
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAppointment = new Intent(getContext(), Home.class);
                startActivity(intentAppointment);
            }
        });

        //Delete Appointment and Update Slot
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete Appointment
                bookingDataManager.DelBooking(selectedId);
                Toast.makeText(getContext(), "Appointment Deleted!", Toast.LENGTH_SHORT).show();

                //Initialize AppointmentSlotDataManager
                AppointmentSlotDataManager appointmentSlotDataManager = new AppointmentSlotDataManager();
                AppointmentSlotDataModel SlotDate = appointmentSlotDataManager.GetSlotbyDate(selectedBooking.getDate());

                //Get All Time Slots
                Integer SlotEight = Integer.parseInt(SlotDate.getEight()),
                        SlotTen = Integer.parseInt(SlotDate.getTen()),
                        SlotTwelve = Integer.parseInt(SlotDate.getTwelve()),
                        SlotTwo = Integer.parseInt(SlotDate.getTwo()),
                        SlotFour = Integer.parseInt(SlotDate.getFour());


                //Set Selected Time Slot to -1
                if (selectedTime.equals("8am - 10am")) {
                    SlotEight = SlotEight - 1;
                }
                if (selectedTime.equals("10am - 12pm")) {
                    SlotTen = SlotTen - 1;
                }
                if (selectedTime.equals("12pm - 2pm")) {
                    SlotTwelve = SlotTwelve - 1;
                }
                if (selectedTime.equals("2pm - 4pm")) {
                    SlotTwo = SlotTwo - 1;
                }
                if (selectedTime.equals("4pm - 6pm")) {
                    SlotFour = SlotFour - 1;
                }

                //Update Time Slot
                appointmentSlotDataManager.UpdateAppointmentSlot(selectedBooking.getDate(), SlotEight, SlotTen, SlotTwelve, SlotTwo, SlotFour);

                //Intent Back to All Appointment
                Intent intentAppointment = new Intent(getContext(), Home.class);
                startActivity(intentAppointment);
            }
        });


        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
