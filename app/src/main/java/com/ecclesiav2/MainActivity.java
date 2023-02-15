package com.ecclesiav2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    OrganisationFragment organisationFragment = new OrganisationFragment();
    ElectionFragment electionFragment = new ElectionFragment();
    private ArrayList<Organisation> registeredOrganisations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settupRegisteredOrganisations();

        //BOTTOM NAV BAR-----------------------------------------
        bottomNavigationView = findViewById(R.id.bottom_nav);
        Bundle b=new Bundle();
        b.putParcelableArrayList("DATA", registeredOrganisations);
        organisationFragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, organisationFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.organisations:
                        Bundle b=new Bundle();
                        b.putParcelableArrayList("DATA", registeredOrganisations);
                        organisationFragment.setArguments(b);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, organisationFragment).commit();
                        return true;
                    case R.id.elections:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, electionFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }

    private void settupRegisteredOrganisations() {
        registeredOrganisations.add(new Organisation("0", "org0", "hello world"));
        registeredOrganisations.add(new Organisation("2", "org2", "hello world"));

        LocalDateTime finishedTime = LocalDateTime.of(2023, 02, 15, 9, 24);

        if (LocalDateTime.now().isAfter(finishedTime)){
            registeredOrganisations.get(0).setDescription("hellooooo");
        }
    }
}