package com.example.assignment.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignment.DataModel.BookingDataModels;
import com.example.assignment.R;

import java.sql.Connection;

public class listAppointmentAdapter extends ArrayAdapter {
        private BookingDataModels[] data;

        public listAppointmentAdapter(Context context, BookingDataModels[] data){
            super(context,0, data);
            this.data = data;
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemview = convertView;

        if(itemview == null){
            itemview = ((Activity)this.getContext()).getLayoutInflater().inflate(R.layout.list_appointment_adapter, null);
        }

        final BookingDataModels bookingDataModels = data[position];
        TextView date = itemview.findViewById(R.id.textViewDate);
        TextView time = itemview.findViewById(R.id.textViewTime);

        date.setText(bookingDataModels.getDate().toString());
        time.setText(bookingDataModels.getTime().toString());

        return itemview;
    }
}
