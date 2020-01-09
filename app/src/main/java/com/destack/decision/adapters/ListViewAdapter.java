package com.destack.decision.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.destack.decision.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<String> itemList;
    private LayoutInflater layoutInflater;

    public ListViewAdapter(Context context, List<String> itemList) {
        super();
        this.itemList = itemList;
        this.layoutInflater =  LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_view_item, parent, false);
        }

        TextInputLayout textInputLayout = convertView.findViewById(R.id.decisionInput);
        textInputLayout.setHint(String.valueOf(position + 1));

        return convertView;
    }
}
