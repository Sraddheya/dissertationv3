package com.ecclesiav2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    OrganisationFragment organisationFragment = new OrganisationFragment();
    ElectionFragment electionFragment = new ElectionFragment();
    //private ArrayList<Organisation> registeredOrganisations = new ArrayList<>();
    private ArrayList<Organisation> registeredOrganisations;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setRegisteredOrganisations();
        loadOrganisation();

        if (getIntent().hasExtra("Organisation")){
            Organisation organisation = getIntent().getParcelableExtra("Organisation");
            registeredOrganisations.add(organisation);
            saveOrganisation();
            setOrganisationsFragment();
        }

        setOrganisationsFragment();

        //BOTTOM NAV BAR-----------------------------------------
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.organisations:
                        setOrganisationsFragment();
                        return true;
                    case R.id.elections:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, electionFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }

    private void setOrganisationsFragment() {
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


    private void setRegisteredOrganisations() {
        registeredOrganisations.add(new Organisation("0", "org0", "hello world"));
        registeredOrganisations.add(new Organisation("2", "org2", "hello world"));

        LocalDateTime finishedTime = LocalDateTime.of(2023, 02, 15, 9, 24);

        if (LocalDateTime.now().isAfter(finishedTime)){
            registeredOrganisations.get(0).setDescription("hellooooo");
        }
    }
}