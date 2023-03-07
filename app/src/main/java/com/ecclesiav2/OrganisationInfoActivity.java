package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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
        TextView titleTxt = findViewById(R.id.orgTitle);
        titleTxt.setText(organisation.getName());
        TextView descriptionTxt = findViewById(R.id.orgDescription);
        descriptionTxt.setText(organisation.getDescription());
    }
}