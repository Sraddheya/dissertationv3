package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrganisationInfoActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //CONTENT------------------------------------------------
        //Intent intent = getIntent();
        String name = getIntent().getStringExtra("NAME");
        String description = getIntent().getStringExtra("DESCRIPTION");

        TextView nameTextView = findViewById(R.id.orgName);
        TextView descriptionTextView = findViewById(R.id.orgDescription);

        nameTextView.setText(name);
        descriptionTextView.setText(description);
    }
}