package com.ecclesiav2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class OrganisationFragment extends Fragment{
   private RecyclerView orgRecView;
    private OrganisationAdapter.RecyclerViewClickListener listener;
    private OrganisationData organisationData = new OrganisationData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_organisation, container, false);

        Button qrButton = view.findViewById(R.id.qrButton);
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OrganisationScanner.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //RECYCLER VIEW-------------------------------------------
        orgRecView = view.findViewById(R.id.OrgRecView);
        orgRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        setOnClickListener();
        OrganisationAdapter adapter = new OrganisationAdapter(getContext(), listener);
        adapter.setOrganisations(organisationData.getRegisteredOrganisations());
        orgRecView.setAdapter(adapter);
    }

    //OPEN ACTIVITY ONCLICK
    private void setOnClickListener() {
        listener = new OrganisationAdapter.RecyclerViewClickListener() {
            @Override
            public void onCLick(View v, int position) {
                Intent intent = new Intent(getContext(), OrganisationActivity.class);
                intent.putExtra("NAME", organisationData.getRegisteredOrganisations().get(position).getName());
                intent.putExtra("DESCRIPTION", organisationData.getRegisteredOrganisations().get(position).getDescription());
                startActivity(intent);
            }
        };
    }
}
