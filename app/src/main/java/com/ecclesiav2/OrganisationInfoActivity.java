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
    private Organisation organisation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_info);

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //CONTENT
        organisation = getIntent().getExtras().getParcelable("org");
        setContent();
    }

    private void setContent() {
        TextView titleTxt = findViewById(R.id.orgName);
        titleTxt.setText(organisation.getName());
        TextView descriptionTxt = findViewById(R.id.orgDescription);
        descriptionTxt.setText(organisation.getDescription());
    }
}