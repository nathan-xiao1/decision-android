package com.destack.decision.ui.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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


public class DateFragment extends Fragment {

    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    private TextView resultTextView;
    private EditText minEditText;
    private EditText maxEditText;

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

                    }
                }, 2012, 12, 12).show();
            }
        });


        return view;
    }

    public void getInput() {
        try {
            Date min = dateFormat.parse(minEditText.getText().toString());
            Date max = dateFormat.parse(maxEditText.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
