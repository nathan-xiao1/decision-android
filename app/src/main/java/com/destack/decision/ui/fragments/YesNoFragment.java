package com.destack.decision.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.destack.decision.R;

import java.util.Random;

public class YesNoFragment extends Fragment {

    private boolean yesOrNo;
    private TextView textView;
    private ConstraintLayout constraintLayout;

    public static YesNoFragment newInstance() {
        return new YesNoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_yes_no, container, false);
        constraintLayout = view.findViewById(R.id.main);
        textView = view.findViewById(R.id.yesNoTextView);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateYesNo();
                displayResult(textView);
            }
        });

        if (savedInstanceState != null) {
            yesOrNo = savedInstanceState.getBoolean("yesOrNo");
            displayResult(textView);
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
        textView.setText(yesOrNo ? "Yes" : "No");
        textView.setTextColor(yesOrNo ? getResources().getColor(R.color.colorYes) :
                getResources().getColor(R.color.colorNo));
    }

    private void generateYesNo() {
        this.yesOrNo = new Random().nextBoolean();
    }

}
