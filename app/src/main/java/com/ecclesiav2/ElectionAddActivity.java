package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ElectionAddActivity extends AppCompatActivity {
    int index;
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
        index = intent.getIntExtra("INDEX", 0);
        setContent();

        //Add button
        Button addBtn = findViewById(R.id.joinBtn);
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

    private void setContent() {
        Election election = allElections.get(index);

        TextView titleTxt = findViewById(R.id.titleTxt);
        titleTxt.setText(election.getTitle());
        TextView startCastTxt = findViewById(R.id.startCastTxt);
        startCastTxt.setText("Voting starts at: " + election.getStartCast());
        TextView endCastTxt = findViewById(R.id.endCastTxt);
        endCastTxt.setText("Voting ends at: " + election.getEndCast());
        TextView questionTxt = findViewById(R.id.questionTxt);
        questionTxt.setText(election.getQuestion());
        TextView descriptionTxt = findViewById(R.id.descriptionTxt);
        descriptionTxt.setText(election.getDescription());
        TextView statusTxt = findViewById(R.id.statusTxt);
        statusTxt.setText(election.getStatus());
    }

    private void setElections() {
        ArrayList<String> options = new ArrayList<>();
        options.add("op1");
        options.add("op2");

        ArrayList<String> optionDescriptions = new ArrayList<>();
        optionDescriptions.add("This is the description");
        optionDescriptions.add("This is the description");

        allElections.add(new Election("0", "el0", "0", "2016-03-04 11:30", "2016-03-04 11:30", "Joined", "Who am I?", "Hello description", options, optionDescriptions));
        allElections.add(new Election("1", "el1", "0", "2016-03-04 11:30", "2016-03-04 11:30", "Joined", "Who am I?", "Hello description", options, optionDescriptions));
        allElections.add(new Election("2", "el2", "0", "2016-03-04 11:30", "2016-03-04 11:30", "Joined", "Who am I?", "Hello description", options, optionDescriptions));
        allElections.add(new Election("3", "el3", "0", "2016-03-04 11:30", "2016-03-04 11:30", "Joined", "Who am I?", "Hello description", options, optionDescriptions));

        allElections.add(new Election("4", "Favourite Pizza Toppings", "0", "2016-03-04 11:30", "2016-03-04 11:30", "Joined",
                "What is your favourite pizza topping?",
                "In this election, we want to find out what your favourite pizza topping is.",
                options,
                optionDescriptions));
    }
}