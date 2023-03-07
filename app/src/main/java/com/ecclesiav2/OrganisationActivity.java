package com.ecclesiav2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class OrganisationActivity extends AppCompatActivity {
    private ArrayList<Organisation> registeredOrganisations;
    private RecyclerView orgRecView;
    private OrganisationAdapter.RecyclerViewClickListener orgListener;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation);

        //Load current registered organisations
        loadOrganisations();

        //Adding organisations from OrganisationAddActivity
        if (getIntent().hasExtra("Organisation")){
            Organisation organisation = getIntent().getParcelableExtra("Organisation");
            registeredOrganisations.add(organisation);
            saveOrganisations();
        }

        //Setup RecyclerView
        orgRecView = findViewById(R.id.OrgRecView);
        orgRecView.setLayoutManager(new LinearLayoutManager(this));
        setOnClickListener();
        setAdapter();

        //QRScanner button
        Button qrButton = findViewById(R.id.qrButton);
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrganisationActivity.this, OrganisationScanner.class);
                startActivity(intent);
            }
        });

        //Bottom navigation view
        navView = findViewById(R.id.bottom_nav);
        navView.setSelectedItemId(R.id.organisations);
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.organisations:
                        return true;
                    case R.id.elections:
                        Intent intent = new Intent(OrganisationActivity.this, ElectionActivity.class);
                        overridePendingTransition(0,0);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }

    private void saveOrganisations() {
        SharedPreferences sp = getSharedPreferences("OrgSP", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(registeredOrganisations);
        editor.putString("Org", json);
        editor.apply();
    }

    private void loadOrganisations() {
        SharedPreferences sp = getSharedPreferences("OrgSP", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("Org", null);
        Type type = new TypeToken<ArrayList<Organisation>>() {}.getType();
        registeredOrganisations = gson.fromJson(json, type);

        if (registeredOrganisations == null){
            registeredOrganisations = new ArrayList<>();
        }
    }

    private void setAdapter() {
        OrganisationAdapter orgAdapter = new OrganisationAdapter(this, orgListener);
        orgAdapter.setOrganisations(registeredOrganisations);
        orgRecView.setAdapter(orgAdapter);
    }

    private void setOnClickListener() {
        orgListener = new OrganisationAdapter.RecyclerViewClickListener(){
            @Override
            public void onCLick(View v, int position) {
                Intent intent = new Intent(OrganisationActivity.this, OrganisationInfoActivity.class);
                intent.putExtra("org", registeredOrganisations.get(position));
                startActivity(intent);
            }
        };
    }
}