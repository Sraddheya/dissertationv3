package com.ecclesiav2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    OrganisationFragment organisationFragment = new OrganisationFragment();
    ElectionFragment electionFragment = new ElectionFragment();
    private ArrayList<Organisation> registeredOrganisations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadOrganisation();

        //Adding organisation form OrganisationAddActivity
        if (getIntent().hasExtra("Organisation")){
            Organisation organisation = getIntent().getParcelableExtra("Organisation");
            registeredOrganisations.add(organisation);
            saveOrganisation();
            loadOrganisationsFragment();
        }

        //Default page for opening app
        loadOrganisationsFragment();

        //Bottom nav bar
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.organisations:
                        loadOrganisationsFragment();
                        return true;
                    case R.id.elections:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container, electionFragment).commit();
                        Intent intent = new Intent(MainActivity.this, ElectionActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }

    private void loadOrganisationsFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("DATA", registeredOrganisations);
        organisationFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, organisationFragment).commit();
    }

    private void saveOrganisation(){
        SharedPreferences sp = getSharedPreferences("OrgSP", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(registeredOrganisations);
        editor.putString("Org", json);
        editor.apply();
    }

    private void loadOrganisation(){
        SharedPreferences sp = getSharedPreferences("OrgSP", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("Org", null);
        Type type = new TypeToken<ArrayList<Organisation>>() {}.getType();
        registeredOrganisations = gson.fromJson(json, type);

        if (registeredOrganisations == null){
            registeredOrganisations = new ArrayList<>();
        }
    }
}