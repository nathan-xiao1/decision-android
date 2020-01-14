package com.destack.decision.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.destack.decision.R;

import java.util.Random;

public class YesNoFragment extends Fragment {

    private boolean yesOrNo;
    private TextView resultTextView;
    private Button generateButton;
    private LinearLayout layout_container;
    private Animation fadeIn;
    private Animation fadeOut;

    public static YesNoFragment newInstance() {
        return new YesNoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_yes_no, container, false);
        generateButton = view.findViewById(R.id.yes_no_generate_button);
        resultTextView = view.findViewById(R.id.yes_no_textview);
        layout_container = view.findViewById(R.id.yes_no_container);

        // Load the fading animations
        fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateYesNo();
                displayResult(resultTextView);
            }
        });

        if (savedInstanceState != null) {
            yesOrNo = savedInstanceState.getBoolean("yesOrNo");
            displayResult(resultTextView);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("yesOrNo", yesOrNo);
    }

    private void displayResult(TextView textView) {
        textView.startAnimation(fadeOut);
        textView.setText(yesOrNo ? "Yes" : "No");
        textView.startAnimation(fadeIn);
        textView.setTextColor(yesOrNo ? getResources().getColor(R.color.colorYes) :
                getResources().getColor(R.color.colorNo));

    }

    private void generateYesNo() {
        this.yesOrNo = new Random().nextBoolean();
    }

}
