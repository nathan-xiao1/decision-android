package com.destack.decision.ui.fragments;

import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.destack.decision.R;

import java.math.BigInteger;
import java.util.Random;

public class RNGFragment extends Fragment {

    private TextView resultTextView;
    private EditText minEditText;
    private EditText maxEditText;
    private Animation fadeIn;
    private Animation fadeOut;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.rng_title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rng, container, false);

        // Load the fading animations
        fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);

        // Store the relevant reference
        resultTextView = view.findViewById(R.id.rng_result_textview);
        minEditText = view.findViewById(R.id.rng_min_edittext);
        maxEditText = view.findViewById(R.id.rng_max_edittext);

        // Add listener to the generate button
        view.findViewById(R.id.rng_generate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyInput();
                startFadeAnimation(resultTextView, String.valueOf(getRandomNumber()));
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
    }

    /**
     * Generate a random number between the min and max inclusive
     * @return a int
     */
    private long getRandomNumber() {
        long min = Long.parseLong(minEditText.getText().toString());
        long max = Long.parseLong(maxEditText.getText().toString()) + 1;
        if (min > max) {
            long temp = min;
            min = max;
            max = temp;
        }
        return min + ((long) (new Random().nextDouble() * (max - min)));
    }

    /**
     * Play text fade animation
     * @param textView the TextView
     * @param result the text to be displayed
     */
    private void startFadeAnimation(TextView textView, String result) {
        textView.startAnimation(fadeOut);
        textView.setText(result);
        textView.startAnimation(fadeIn);
    }

}
