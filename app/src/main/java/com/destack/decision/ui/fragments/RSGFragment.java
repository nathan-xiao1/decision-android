package com.destack.decision.ui.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.destack.decision.R;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RSGFragment extends Fragment {

    private TextView resultTextView;
    private EditText minEditText;
    private EditText maxEditText;
    private EditText quantityEditText;
    private EditText separatorEditText;
    private Animation fadeIn;
    private Animation fadeOut;
    private int quantityMax = 100;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.rsg_title);
    }


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
                verifyInput();
                resultTextView.startAnimation(fadeOut);
                resultTextView.setText(generate());
                resultTextView.startAnimation(fadeIn);

            }
        });

        return view;

    }

    /**
     *  Check if input values are within range of a long's min and max
     */
    private void verifyInput() {
        BigInteger min = new BigInteger(minEditText.getText().toString());
        BigInteger max = new BigInteger(maxEditText.getText().toString());
        if (max.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
            maxEditText.setText(String.valueOf(Long.MAX_VALUE));
        }
        if (min.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
            minEditText.setText(String.valueOf(Long.MAX_VALUE));
        }
        if (Integer.parseInt(quantityEditText.getText().toString()) > quantityMax) {
            quantityEditText.setText(String.valueOf(quantityMax));
        }
    }

    /**
     *  Get a random set and display it
     */
    private String generate() {

        long min = Long.parseLong(minEditText.getText().toString());
        long max = Long.parseLong(maxEditText.getText().toString()) + 1;
        int quantity = Integer.parseInt(quantityEditText.getText().toString());
        String seperator = separatorEditText.getText().toString();

        // Swap if min is greater than max
        if (min > max) {
            long tmp = min;
            min = max;
            max = tmp;
        }

        // Limit the quantity to the max count of unique numbers
        if (quantity > max - min) {
            quantity = (int) (max - min);
        }

        List<Long> setList = generateRandomisedSet(min, max, quantity);
        return getResultString(setList, quantity, seperator);

    }

    /**
     * Generate a randomised number set between min and max (inclusive)
     * @param min the lower bound value
     * @param max the upper bound value
     * @return a List of int
     */
    private List<Long> generateRandomisedSet(long min, long max, int quantity) {
        int i = 0;
        List<Long> setList = new ArrayList<>();
        while (i < quantity) {
            long rand = min + ((long) (new Random().nextDouble() * (max - min)));
            if (!setList.contains(rand)) {
                setList.add(rand);
                i++;
            }
        }
//        Collections.shuffle(setList);
        return setList;
    }

    /**
     * Convert a List of integer into a formatted string format
     * @param setList the List of int to be converted
     * @param quantity the number of int to convert
     * @param seperator a String to seperate each int
     * @return a formatted String
     */
    private String getResultString(List<Long> setList, int quantity, String seperator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < quantity - 1; i++) {
            stringBuilder.append(setList.get(i)).append(seperator).append("\n");
        }
        stringBuilder.append(setList.get(setList.size() - 1));
        return stringBuilder.toString().trim();
    }
}
