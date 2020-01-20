package com.destack.decision.ui.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

    private final DateFormat format12Hr = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    private final DateFormat format24Hr = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private final DateFormat formatHour = new SimpleDateFormat("HH", Locale.getDefault());
    private final DateFormat formatMinute = new SimpleDateFormat("mm", Locale.getDefault());

    private EditText minTimeEditText;
    private EditText maxTimeEditText;
    private TextView resultTextView;

    private Date min;
    private Date max;
    private Date result;

    private boolean hr12 = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.time_title);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Set the default times
        min = get24HrTime("00:00");
        max = get24HrTime("23:59");
        result = min;

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
                        min = get24HrTime(hourOfDay + ":" + minute);
                        minTimeEditText.setText(getResultString(min));
                    }
                }, getHour(min), getMinute(min), !hr12).show();
            }
        });

        maxTimeEditText = view.findViewById(R.id.time_max_edittext);
        maxTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        max = get24HrTime(hourOfDay + ":" + minute);
                        maxTimeEditText.setText(getResultString(max));
                    }
                }, getHour(max), getMinute(max), !hr12).show();
            }
        });

        CheckBox modeCheckBox = view.findViewById(R.id.time_mode_checkbox);
        modeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchMode(isChecked);
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

    private void switchMode(boolean isChecked) {
        hr12 = isChecked;
        minTimeEditText.setText(getResultString(min));
        maxTimeEditText.setText(getResultString(max));
        resultTextView.setText(getResultString(result));
    }

    private String getResultString(Date result) {
        return hr12 ? format12Hr.format(result) : format24Hr.format(result);
    }

    private int getHour(Date date) {
        return Integer.valueOf(formatHour.format(date));
    }

    private int getMinute(Date date) {
        return Integer.valueOf(formatMinute.format(date));
    }

    private void generate() {
        // Swap min and max if min > max
        if (min.compareTo(max) > 0) {
            Date temp = min;
            min = max;
            max = temp;
        }

        int difference = (int) (max.getTime() - min.getTime());
        result = new Date(min.getTime() + new Random().nextInt(difference + 60000));
        resultTextView.setText(getResultString(result));
    }

    private Date get24HrTime(String time) {
        try {
            return format24Hr.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
