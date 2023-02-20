package com.ecclesiav2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class OrganisationFragment extends Fragment{
    private RecyclerView orgRecView;
    private OrganisationAdapter.RecyclerViewClickListener listener;
    private ArrayList<Organisation> registeredOrganisations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organisation, container, false);

        //QRScanner button
        Button qrButton = view.findViewById(R.id.qrButton);
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OrganisationScanner.class);
                startActivity(intent);
            }
        });

        //Setup RecyclerView
        orgRecView = view.findViewById(R.id.OrgRecView);
        orgRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        setOnClickListener();
        OrganisationAdapter adapter = new OrganisationAdapter(getContext(), listener);
        registeredOrganisations = getArguments().getParcelableArrayList("DATA");
        adapter.setOrganisations(registeredOrganisations);
        orgRecView.setAdapter(adapter);
        return view;
    }


    //Open activity on click recyclerView item
    private void setOnClickListener() {
        listener = new OrganisationAdapter.RecyclerViewClickListener() {
            @Override
            public void onCLick(View v, int position) {
                Intent intent = new Intent(getContext(), OrganisationInfoActivity.class);
                intent.putExtra("NAME", registeredOrganisations.get(position).getName());
                intent.putExtra("DESCRIPTION", registeredOrganisations.get(position).getDescription());
                startActivity(intent);
            }
        };
    }
}
