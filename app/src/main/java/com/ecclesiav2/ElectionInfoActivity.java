package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ElectionInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_info);

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //CONTENT
        Election election = getIntent().getExtras().getParcelable("elec");
        String title = election.getTitle();
        String status = election.getStatus();

        TextView titleTextView = findViewById(R.id.title);
        TextView statusTextView = findViewById(R.id.status);

        titleTextView.setText(title);
        statusTextView.setText(status);
    }
}