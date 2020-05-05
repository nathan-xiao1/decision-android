package com.destack.decision.ui.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.destack.decision.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;


public class DateFragment extends Fragment {

    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private final DateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
    private final DateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
    private final DateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());

    private TextView resultTextView;
    private EditText minEditText;
    private EditText maxEditText;

    private Date min = new Date();
    private Date max = new Date();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date, container, false);

        resultTextView = view.findViewById(R.id.date_result_textview);
        minEditText = view.findViewById(R.id.date_min_edittext);
        maxEditText = view.findViewById(R.id.date_max_edittext);

        minEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        setMin(year, month, dayOfMonth);
                        minEditText.setText(dateFormat.format(min));
                    }
                }, Integer.parseInt(yearFormat.format(min)), Integer.parseInt(monthFormat.format(min)) - 1, Integer.parseInt(dayFormat.format(min))).show();
            }
        });

        maxEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        setMax(year, month, dayOfMonth);
                        maxEditText.setText(dateFormat.format(max));
                    }
                }, Integer.parseInt(yearFormat.format(max)), Integer.parseInt(monthFormat.format(max)) - 1, Integer.parseInt(dayFormat.format(max))).show();
            }
        });

        view.findViewById(R.id.date_generate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateDate();
            }
        });

        return view;
    }

    private void generateDate() {
        // Swap min and max if min > max
        if (min.compareTo(max) > 0) {
            Date temp = min;
            min = max;
            max = temp;
        }
        long start = min.getTime();
        long end = max.getTime();
        long result = ThreadLocalRandom.current().nextLong(start, end + (1000 * 60 * 60 * 24));
        resultTextView.setText(dateFormat.format(new Date(result)));
    }

    private void setMax(int year, int month, int dayOfMonth) {
        try {
            this.max = dateFormat.parse(dayOfMonth + "/" + month + "/" + year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setMin(int year, int month, int dayOfMonth) {
        try {
            this.min = dateFormat.parse(dayOfMonth + "/" + month + "/" + year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
