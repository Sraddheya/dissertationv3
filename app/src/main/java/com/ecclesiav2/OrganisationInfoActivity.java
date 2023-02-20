package com.ecclesiav2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class OrganisationInfoActivity extends AppCompatActivity{
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_info);

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //CONTENT
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String description = intent.getStringExtra("DESCRIPTION");

        TextView nameTextView = findViewById(R.id.orgName);
        TextView descriptionTextView = findViewById(R.id.orgDescription);

        nameTextView.setText(name);
        descriptionTextView.setText(description);
    }
}