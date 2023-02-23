package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ElectionAddActivity extends AppCompatActivity {
    private ArrayList<Election> allElections = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_add);

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Init all Organisation
        setElections();

        //Display content
        Intent intent = getIntent();
        int index = intent.getIntExtra("INDEX", 0);

        TextView titleTextView = findViewById(R.id.title);
        TextView statusTextView = findViewById(R.id.status);

        titleTextView.setText(allElections.get(index).getTitle());
        statusTextView.setText(allElections.get(index).getStatus());

        //Add button
        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElectionAddActivity.this, ElectionActivity.class);
                intent.putExtra("Election", allElections.get(index));
                startActivity(intent);
            }
        });

        //Cancel button
        Button cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElectionAddActivity.this, ElectionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setElections() {
        ArrayList<String> options = new ArrayList<>();
        options.add("op1");
        options.add("op2");

        ArrayList<String> optionDescriptions = new ArrayList<>();
        optionDescriptions.add("This is the description");
        optionDescriptions.add("This is the description");

        allElections.add(new Election("0", "el0", "0", "2016-03-04 11:30", "2016-03-04 11:30", "Joined", "Hello description", options, optionDescriptions));
        allElections.add(new Election("1", "el1", "0", "2016-03-04 11:30", "2016-03-04 11:30", "Joined", "Hello description", options, optionDescriptions));
        allElections.add(new Election("2", "el2", "0", "2016-03-04 11:30", "2016-03-04 11:30", "Joined", "Hello description", options, optionDescriptions));
        allElections.add(new Election("3", "el3", "0", "2016-03-04 11:30", "2016-03-04 11:30", "Joined", "Hello description", options, optionDescriptions));
    }
}