package com.destack.decision.ui.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.destack.decision.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class TimeFragment extends Fragment {

    private final DateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private EditText minTimeEditText;
    private EditText maxTimeEditText;
    private TextView resultTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time, container, false);

        resultTextView = view.findViewById(R.id.time_result_textview);

        minTimeEditText = view.findViewById(R.id.time_min_edittext);
        minTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        minTimeEditText.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
                    }
                }, getHour(minTimeEditText), getMinute(minTimeEditText), true).show();
            }
        });

        maxTimeEditText = view.findViewById(R.id.time_max_edittext);
        maxTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        maxTimeEditText.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
                    }
                }, getHour(maxTimeEditText), getMinute(maxTimeEditText), true).show();
            }
        });

        view.findViewById(R.id.time_generate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate();
            }
        });

        return view;
    }

    private int getHour(EditText editText) {
        return Integer.valueOf(editText.getText().toString().split(":")[0]);
    }

    private int getMinute(EditText editText) {
        return Integer.valueOf(editText.getText().toString().split(":")[1]);
    }

    private void generate() {
        Date min = getTime(minTimeEditText.getText().toString());
        Date max = getTime(maxTimeEditText.getText().toString());

        // Swap min and max if min > max
        if (min.compareTo(max) > 0) {
            Date temp = min;
            min = max;
            max = temp;
        }

        int difference = (int) (max.getTime() - min.getTime());
        Date result = new Date(min.getTime() + new Random().nextInt(difference + 60000));
        resultTextView.setText(format.format(result));
    }


    private Date getTime(String time) {
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
