package com.destack.decision.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.destack.decision.R;

import java.util.Random;

public class ChoiceFragment extends Fragment {

    private TextView resultTextView;
    private LinearLayout listContainer;

    public static ChoiceFragment newInstance() {
        return new ChoiceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choice, container, false);

        listContainer = view.findViewById(R.id.choice_list_container);
        resultTextView = view.findViewById(R.id.choice_result_textview);
        reset();

        // Listener for button to add a new input row
        view.findViewById(R.id.choice_add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listContainer.addView(createItem(), listContainer.getChildCount() - 1);
            }
        });

        // Listener for button to randomly select an input
        view.findViewById(R.id.choice_decide_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomSelect();
            }
        });

        // Listener for button to reset and reset inputs
        view.findViewById(R.id.choice_reset_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        return view;
    }

    /**
     * Create a view of an item
     * @return the View of an item
     */
    private View createItem() {
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.choice_item, null);
        view.findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listContainer.removeView(view);
            }
        });
        return view;
    }


    /**
     * Reset to initial state
     */
    private void reset() {
        resultTextView.setVisibility(View.GONE);
        listContainer.removeViews(0, listContainer.getChildCount() - 1);
        listContainer.addView(createItem(), listContainer.getChildCount() - 1);
    }

    /**
     * Randomly select an item in the ListView
     */
    private void randomSelect() {
        int count = listContainer.getChildCount() - 1;
        if (count > 0) {
            int randomInt = new Random().nextInt(count);
            EditText decisionInput = listContainer.getChildAt(randomInt).findViewById(R.id.decisionInput);
            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setText(decisionInput.getText());
        } else {
            Toast.makeText(getActivity(), "Enter at-least one choice", Toast.LENGTH_SHORT).show();
        }
    }

}
