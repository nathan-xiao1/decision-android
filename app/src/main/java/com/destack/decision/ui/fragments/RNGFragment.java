package com.destack.decision.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.destack.decision.R;

import java.util.Random;

public class RNGFragment extends Fragment {

    private TextView resultTextView;
    private EditText minEditText;
    private EditText maxEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rng, container, false);

        // Store the relevant reference
        resultTextView = view.findViewById(R.id.rng_result_textview);
        minEditText = view.findViewById(R.id.rng_min_edittext);
        maxEditText = view.findViewById(R.id.rng_max_edittext);

        // Add listener to
        view.findViewById(R.id.rng_generate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.setText(String.valueOf(getRandomNumber()));
            }
        });

        return view;
    }

    /**
     * Generate a random number between
     * @return
     */
    private int getRandomNumber() {
        int min = Integer.parseInt(minEditText.getText().toString());
        int max = Integer.parseInt(maxEditText.getText().toString());
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        return new Random().nextInt(max - min + 1) + min;
    }

}
