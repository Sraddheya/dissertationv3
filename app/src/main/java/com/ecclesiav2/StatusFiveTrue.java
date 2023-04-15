package com.ecclesiav2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatusFiveTrue extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_status_five_true, container, false);

        Election election = getArguments().getParcelable("election");

        TextView joinedTimeTxt = v.findViewById(R.id.joinedTimeTxt);
        joinedTimeTxt.setText(election.getJoinedTime());
        TextView startTimeTxt = v.findViewById(R.id.startTimeTxt);
        startTimeTxt.setText(election.getStartTime());
        TextView castTimeTxt = v.findViewById(R.id.castTimeTxt);
        castTimeTxt.setText(election.getCastTime());
        TextView recordedTimeTxt = v.findViewById(R.id.recordedTimeTxt);
        recordedTimeTxt.setText(election.getRecordedTime());
        TextView calculatedTimeTxt = v.findViewById(R.id.calculatedTimeTxt);
        calculatedTimeTxt.setText(election.getCalculatedTime());


        return v;
    }
}