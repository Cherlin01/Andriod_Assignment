package com.example.assignment.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment.DataManager.BookingDataManager;
import com.example.assignment.DataModel.BookingDataModels;
import com.example.assignment.R;


public class DetailFragmentBooking extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment_bookings, container,false);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        TextView textViewTime = view.findViewById(R.id.textViewTime);
        Button btnDel = view.findViewById(R.id.btnDelete);
        Bundle params = this.getArguments();
        //String selectedId = params.getString();
        BookingDataManager bookingDataManager = new BookingDataManager();
        //BookingDataModels selectedBooking = bookingDataManager.GetBookingById();



        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
