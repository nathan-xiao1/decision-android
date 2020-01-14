package com.destack.decision.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.destack.decision.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RSGFragment extends Fragment {

    private TextView resultTextView;
    private EditText minEditText;
    private EditText maxEditText;
    private EditText quantityEditText;
    private EditText separatorEditText;
    private Animation fadeIn;
    private Animation fadeOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rsg, container, false);

        resultTextView = view.findViewById(R.id.rsg_textview);
        minEditText = view.findViewById(R.id.rsg_min_edittext);
        maxEditText = view.findViewById(R.id.rsg_max_edittext);
        quantityEditText = view.findViewById(R.id.rsg_quantity_edittext);
        separatorEditText = view.findViewById(R.id.rsg_seperator_edittext);

        // Load the fading animations
        fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);

        view.findViewById(R.id.rsg_generate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate();
            }
        });

        return view;

    }

    /**
     *  Get a random set and display it
     */
    private void generate() {

        int min = Integer.parseInt(minEditText.getText().toString());
        int max = Integer.parseInt(maxEditText.getText().toString());
        int quantity = Integer.parseInt(quantityEditText.getText().toString());
        String seperator = separatorEditText.getText().toString();

        // Swap if min is greater than max
        if (min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }

        // Limit the quantity to the max count of unique numbers
        if (quantity > max - min) {
            quantity = max - min;
        }

        List<Integer> setList = generateRandomisedSet(min, max);
        String result = getResultString(setList, quantity, seperator);

        // Play animation and change the text
        resultTextView.startAnimation(fadeOut);
        resultTextView.setText(result);
        resultTextView.startAnimation(fadeIn);

    }

    /**
     * Generate a randomised number set between min and max (inclusive)
     * @param min the lower bound value
     * @param max the upper bound value
     * @return a List of int
     */
    private List<Integer> generateRandomisedSet(int min, int max) {
        List<Integer> setList = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            setList.add(i);
        }
        Collections.shuffle(setList);
        return setList;
    }

    /**
     * Convert a List of integer into a formatted string format
     * @param setList the List of int to be converted
     * @param quantity the number of int to convert
     * @param seperator a String to seperate each int
     * @return a formatted String
     */
    private String getResultString(List<Integer> setList, int quantity, String seperator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < quantity; i++) {
            stringBuilder.append(setList.get(i)).append(seperator).append("\n");
        }
        return stringBuilder.toString().trim();
    }
}
