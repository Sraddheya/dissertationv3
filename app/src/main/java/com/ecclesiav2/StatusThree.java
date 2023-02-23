package com.ecclesiav2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatusThree extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_status_three, container, false);
        int selectedIndex = getArguments().getInt("selectedIndex");
        TextView text = v.findViewById(R.id.selectedIndex);
        text.setText(Integer.toString(selectedIndex));
        return v;
    }
}