package com.destack.decision.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.destack.decision.R;
import com.destack.decision.adapters.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChoiceFragment extends Fragment {

    private List<String> itemList = new ArrayList<>();
    private ListViewAdapter arrayAdapter;

    public static ChoiceFragment newInstance() {
        return new ChoiceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choice, container, false);

        ListView listView = view.findViewById(R.id.listView);
        arrayAdapter = new ListViewAdapter(getContext(), itemList);
        listView.setAdapter(arrayAdapter);

        view.findViewById(R.id.choiceContainer).setOnClickListener(new View.OnClickListener() { // TODO REMOVE THIS
            @Override
            public void onClick(View v) {
                itemList.add("");
                arrayAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

}
