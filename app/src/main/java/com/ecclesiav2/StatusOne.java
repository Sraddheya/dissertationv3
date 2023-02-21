package com.ecclesiav2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatusOne extends Fragment {
    String startTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_status_one, container, false);

        //Get startTime
        startTime = getArguments().getString("startTime");

        String joinedText = "Others are still joining the election, you can join the election at " + startTime + ".";

        TextView startText = v.findViewById(R.id.startText);
        startText.setText(joinedText);
        return v;
    }
}