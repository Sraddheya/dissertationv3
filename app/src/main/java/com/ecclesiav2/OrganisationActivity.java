package com.ecclesiav2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private SearchView orgSearchView;
    private OrganisationAdapter orgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation);

        //Welcome slides
        startWelcome();

        //Load current registered organisations
        loadOrganisations();

        //Adding organisations from OrganisationAddActivity
        if (getIntent().hasExtra("Organisation")){
            Organisation organisation = getIntent().getParcelableExtra("Organisation");
            registeredOrganisations.add(organisation);
            saveOrganisations();
        }

        //Show instructions
        if (registeredOrganisations.isEmpty()){

        }

        //Setup RecyclerView
        orgRecView = findViewById(R.id.orgRecView);
        orgRecView.setLayoutManager(new LinearLayoutManager(this));
        setOnClickListener();
        setAdapter();
        showInstructions();

        //QRScanner button
        Button qrButton = findViewById(R.id.qrButton);
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrganisationActivity.this, OrganisationScanner.class);
                startActivity(intent);
            }
        });

        //SEARCH VIEW
//        orgSearchView = findViewById(R.id.orgSearchView);
//        orgSearchView.clearFocus();
//        orgSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterList(newText);
//                return true;
//            }
//        });

        //Bottom navigation view
        navView = findViewById(R.id.bottomNav);
        navView.setSelectedItemId(R.id.organisations);
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.organisations:
                        return true;
                    case R.id.elections:
                        intent = new Intent(OrganisationActivity.this, ElectionActivity.class);
                        overridePendingTransition(0,0);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                    case R.id.help:
                        intent = new Intent(OrganisationActivity.this, HelpActivity.class);
                        overridePendingTransition(0,0);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }

    private void startWelcome() {
        SharedPreferences firstTime = getSharedPreferences("firstTime", 0);
        if (firstTime.getBoolean("firstTime", true)) {
            Intent intent = new Intent(OrganisationActivity.this, Welcome0Activity.class);
            overridePendingTransition(0,0);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            firstTime.edit().putBoolean("firstTime", false).commit();
        }
    }

    private void showInstructions() {
        TextView orgInstructions = findViewById(R.id.orgInstruction);
        if (registeredOrganisations.isEmpty()){
            orgInstructions.setVisibility(View.VISIBLE);
        } else {
            orgInstructions.setVisibility(View.GONE);
        }
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
        orgAdapter = new OrganisationAdapter(this, orgListener);
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

    private void filterList(String text){
        ArrayList<Organisation> filteredList = new ArrayList<>();
        for (Organisation organisation : registeredOrganisations){
            if (organisation.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(organisation);
            }
        }

        if (filteredList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            orgAdapter.setOrganisations(filteredList);
        }

    }
}