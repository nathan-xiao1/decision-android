package com.destack.decision.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.destack.decision.R;

import java.util.Random;

public class ChoiceFragment extends Fragment {

    private LinearLayout listContainer;

    public static ChoiceFragment newInstance() {
        return new ChoiceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choice, container, false);

        listContainer = view.findViewById(R.id.listContainer);
        reset();

        // Listener for button to add a new input row
        view.findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listContainer.addView(createItem(), listContainer.getChildCount() - 1);
            }
        });

        // Listener for button to randomly select an input
        view.findViewById(R.id.decideButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomSelect();
            }
        });

        // Listener for button to clear and reset inputs
        view.findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
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
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_view_item, null);
        view.findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listContainer.removeView(view);
            }
        });
        return view;
    }

    /**
     * Reset container by removing all child views and
     * adding one default view
     */
    private void reset() {
        listContainer.removeViews(0, listContainer.getChildCount() - 1);
        listContainer.addView(createItem(), listContainer.getChildCount() - 1);
    }

    /**
     * Clear all highlighted items
     */
    private void clear() {
        for (int i = 0; i < listContainer.getChildCount(); i++) {
            listContainer.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.colorLightBackground));
        }
    }

    /**
     * Randomly select an item in the ListView
     */
    private void randomSelect() {
        clear();
        int count = listContainer.getChildCount() - 1;
        if (count > 0) {
            int randomInt = new Random().nextInt(count);
            View randomView = listContainer.getChildAt(randomInt);
            randomView.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
        } else {
            Toast.makeText(getActivity(), "Enter at-least one choice", Toast.LENGTH_SHORT).show();
        }
    }

}
