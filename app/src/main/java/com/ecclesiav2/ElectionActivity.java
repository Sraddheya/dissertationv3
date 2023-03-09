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

public class ElectionActivity extends AppCompatActivity {
    private ArrayList<Election> registeredElections;
    private RecyclerView elecRecView;
    private ElectionAdapter.RecyclerViewClickListener elecListener;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election);

        //Load current registered elections
        loadElections();

        //Adding election from ElectionAddActivity
        if (getIntent().hasExtra("Election")){
            Election election = getIntent().getParcelableExtra("Election");
            registeredElections.add(election);
            saveElections();
        }

        //Update to selected vote
        if (getIntent().hasExtra("selectedIndex")){
            int selectedIndex = getIntent().getIntExtra("selectedIndex", 0);
            int needReCast = getIntent().getIntExtra("needReCast", 0);
            String elecID = getIntent().getStringExtra("elecID");
            for (Election e : registeredElections){
                if (e.getElecId().equals(elecID)){
                    e.setSelectedIndex(selectedIndex);
                    e.setNeedReCast(needReCast);
                    //e.setStatus("Vote casted");
                }
            }
            saveElections();
        }

        //Setup RecyclerView
        elecRecView = findViewById(R.id.elecRecView);
        elecRecView.setLayoutManager(new LinearLayoutManager(this));
        setOnClickListener();
        setAdapter();

        //QRScanner button
        Button qrButton = findViewById(R.id.qrButton);
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElectionActivity.this, ElectionScanner.class);
                startActivity(intent);
            }
        });

        //Bottom navigation view
        navView = findViewById(R.id.bottomNav);
        navView.setSelectedItemId(R.id.elections);
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.organisations:
                        intent = new Intent(ElectionActivity.this, OrganisationActivity.class);
                        overridePendingTransition(0,0);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                    case R.id.elections:
                        return true;
                    case R.id.help:
                        intent = new Intent(ElectionActivity.this, HelpActivity.class);
                        overridePendingTransition(0,0);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }

    private void saveElections() {
        SharedPreferences sp = getSharedPreferences("ElecSP", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(registeredElections);
        editor.putString("Elec", json);
        editor.apply();
    }

    private void loadElections() {
        SharedPreferences sp = getSharedPreferences("ElecSP", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("Elec", null);
        Type type = new TypeToken<ArrayList<Election>>() {}.getType();
        registeredElections = gson.fromJson(json, type);

        if (registeredElections == null){
            registeredElections = new ArrayList<>();
        }
    }

    private void setAdapter() {
        ElectionAdapter elecAdapter = new ElectionAdapter(this, elecListener);
        elecAdapter.setElections(registeredElections);
        elecRecView.setAdapter(elecAdapter);
    }

    private void setOnClickListener() {
        elecListener = new ElectionAdapter.RecyclerViewClickListener(){
            @Override
            public void onCLick(View v, int position) {
                Intent intent = new Intent(ElectionActivity.this, ElectionInfoActivity.class);
                intent.putExtra("elec", registeredElections.get(position));
                startActivity(intent);
            }
        };
    }

}