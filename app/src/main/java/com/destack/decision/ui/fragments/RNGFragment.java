package com.destack.decision.ui.fragments;

import android.animation.ValueAnimator;
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

        // Add listener to the generate button
        view.findViewById(R.id.rng_generate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = Integer.valueOf(resultTextView.getText().toString());
                startCountAnimation(resultTextView, current, getRandomNumber());
            }
        });

        return view;
    }

    /**
     * Generate a random number between the min and max inclusive
     * @return a int
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

    /**
     * Set text and play 'counting' animation
     * @param textView the TextView for the text
     * @param start the initial/starting value
     * @param end the final/ending value
     */
    private void startCountAnimation(final TextView textView, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(150);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }

}
