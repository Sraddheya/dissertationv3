package com.ecclesiav2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        // Inflate the layout for this fragment
        registeredOrganisations = getArguments().getParcelableArrayList("DATA");
        View view = inflater.inflate(R.layout.fragment_organisation, container, false);
        //FOR TESTING
        //settupRegisteredOrganisations();

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

//    private void settupRegisteredOrganisations() {
//        registeredOrganisations.add(new Organisation("0", "org0", "hello world"));
//        registeredOrganisations.add(new Organisation("2", "org2", "hello world"));
//
//        LocalDateTime finishedTime = LocalDateTime.of(2023, 02, 15, 9, 24);
//
//        if (LocalDateTime.now().isAfter(finishedTime)){
//            registeredOrganisations.get(0).setDescription("hellooooo");
//        }
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //RECYCLER VIEW-------------------------------------------
        orgRecView = view.findViewById(R.id.OrgRecView);
        orgRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        setOnClickListener();
        OrganisationAdapter adapter = new OrganisationAdapter(getContext(), listener);
        adapter.setOrganisations(registeredOrganisations);
        orgRecView.setAdapter(adapter);
    }

    //OPEN ACTIVITY ONCLICK
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
