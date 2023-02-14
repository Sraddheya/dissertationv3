package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class OrganisationActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation);

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