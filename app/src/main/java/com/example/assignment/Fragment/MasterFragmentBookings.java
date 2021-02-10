package com.example.assignment.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment.Activity.Home;
import com.example.assignment.Adapter.listAppointmentAdapter;
import com.example.assignment.DataManager.BookingDataManager;
import com.example.assignment.DataModel.BookingDataModels;
import com.example.assignment.R;
import com.example.assignment.Session.Session;

public class MasterFragmentBookings extends Fragment {
    public interface BookingSelectedListener{
        void onBookingSelected(BookingDataModels bookingDataModels);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.master_fragment_bookings, container, false);
        Session session = new Session(getContext());
        ListView listViewBooking = view.findViewById(R.id.listAppointment);
        final BookingDataManager bookingDataManager = new BookingDataManager();
        final BookingDataModels[] bookings = bookingDataManager.GetAllBookings(session.getUserID());

        listAppointmentAdapter adapter = new listAppointmentAdapter(getActivity(), bookings);
        listViewBooking.setAdapter(adapter);
        listViewBooking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookingDataModels selectedId = bookings[position];
                Activity parentActivity = getActivity();
                if(parentActivity instanceof BookingSelectedListener){
                    ((BookingSelectedListener)parentActivity).onBookingSelected(selectedId);
                }
            }
        });
        return view;
    }
}
