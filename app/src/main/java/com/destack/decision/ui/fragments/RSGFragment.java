package com.destack.decision.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private EditText seperatorEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rsg, container, false);

        resultTextView = view.findViewById(R.id.rsg_textview);
        minEditText = view.findViewById(R.id.rsg_min_edittext);
        maxEditText = view.findViewById(R.id.rsg_max_edittext);
        quantityEditText = view.findViewById(R.id.rsg_quantity_edittext);
        seperatorEditText = view.findViewById(R.id.rsg_seperator_edittext);

        view.findViewById(R.id.rsg_generate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate();
            }
        });

        return view;

    }

    private void generate() {

        int min = Integer.parseInt(minEditText.getText().toString());
        int max = Integer.parseInt(maxEditText.getText().toString());
        int quantity = Integer.parseInt(quantityEditText.getText().toString());
        String seperator = seperatorEditText.getText().toString();

        // Swap if min is greater than max
        if (min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }

        if (quantity > max) {
            quantity = max;
        }

        List<Integer> setList = generateRandomisedSet(min, max);
        String result = getResultString(setList, quantity, seperator);

        resultTextView.setText(result);

    }

    private List<Integer> generateRandomisedSet(int min, int max) {
        List<Integer> setList = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            setList.add(i);
        }
        Collections.shuffle(setList);
        return setList;
    }

    private String getResultString(List<Integer> setList, int quantity, String seperator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < quantity; i++) {
            stringBuilder.append(setList.get(i)).append(seperator).append("\n");
        }
        return stringBuilder.toString().trim();
    }
}
