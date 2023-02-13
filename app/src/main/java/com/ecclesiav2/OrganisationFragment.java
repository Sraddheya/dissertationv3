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

public class OrganisationFragment extends Fragment {

    ArrayList<Organisation> organisations = new ArrayList<>();
    String default_org_descriptions = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer facilisis eros turpis, ut eleifend arcu pharetra quis. Integer dapibus pulvinar finibus. Nullam euismod tellus ac lectus laoreet, sed scelerisque mauris egestas. Vestibulum nec ex et lorem pulvinar fringilla. Nam eleifend, ligula non accumsan elementum, dui est elementum lacus, nec hendrerit odio nisi vel eros. Curabitur eget ipsum dignissim, faucibus libero at, congue massa. Nam pulvinar, tellus quis finibus venenatis, eros eros viverra dolor, ac consectetur erat lorem nec ante. Donec dapibus felis magna, eget consequat massa dignissim eget. Aenean quis nunc consectetur, rutrum mi in, maximus enim. Vivamus et magna non mi ultricies sollicitudin sit amet ut lorem.";
    private RecyclerView orgRecView;
    private OrganisationAdapter.RecyclerViewClickListener listener;
    //private OrganisationAdapter adapter = new OrganisationAdapter(getContext(), listener);

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

        //INITIALISE DATA------------------------------------------
        organisations.add(new Organisation("1", "org1", default_org_descriptions));
        organisations.add(new Organisation("2", "org2", default_org_descriptions));

        //RECYCLER VIEW-------------------------------------------
        orgRecView = view.findViewById(R.id.OrgRecView);
        orgRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        setOnClickListener();
        OrganisationAdapter adapter = new OrganisationAdapter(getContext(), listener);
        adapter.setOrganisations(organisations);
        orgRecView.setAdapter(adapter);
    }

    //OPEN ACTIVITY ONCLICK
    private void setOnClickListener() {
        listener = new OrganisationAdapter.RecyclerViewClickListener() {
            @Override
            public void onCLick(View v, int position) {
                Intent intent = new Intent(getContext(), OrganisationActivity.class);
                intent.putExtra("NAME", organisations.get(position).getName());
                intent.putExtra("DESCRIPTION", organisations.get(position).getDescription());
                startActivity(intent);
            }
        };
    }

}
