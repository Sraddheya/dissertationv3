package com.ecclesiav2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    OrganisationFragment organisationFragment = new OrganisationFragment();
    ElectionFragment electionFragment = new ElectionFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BOTTOM NAV BAR-----------------------------------------
        bottomNavigationView = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, organisationFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.organisations:
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
}