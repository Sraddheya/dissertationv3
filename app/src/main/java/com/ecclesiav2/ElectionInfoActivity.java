package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Statement;

public class ElectionInfoActivity extends AppCompatActivity {

    StatusOne statusOne = new StatusOne();
    StatusTwo statusTwo = new StatusTwo();
    StatusThree statusThree = new StatusThree();
    StatusFourTrue statusFourTrue = new StatusFourTrue();
    StatusFourFalse statusFourFalse = new StatusFourFalse();
    StatusFive statusFive = new StatusFive();

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

        //Begin voting button
        Button startVoteBtn = findViewById(R.id.startVoteBtn);

        switch (election.getStatus()){
            case "Joined":
                Bundle bundle = new Bundle();
                bundle.putString("startTime", election.getStartCast());
                statusOne.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, statusOne).commit();

                //Show disabled button
                startVoteBtn.setBackgroundColor(Color.GRAY);
                startVoteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ElectionInfoActivity.this, "Voting has not begin yet", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case "Voting started":
                getSupportFragmentManager().beginTransaction().replace(R.id.container, statusTwo).commit();
                break;
            case "Vote casted":
                getSupportFragmentManager().beginTransaction().replace(R.id.container, statusThree).commit();
                break;
            case "Vote recorded true":
                getSupportFragmentManager().beginTransaction().replace(R.id.container, statusFourTrue).commit();
                break;
            case "Vote recorded false":
                getSupportFragmentManager().beginTransaction().replace(R.id.container, statusFourFalse).commit();
                break;
            case "Results calculated":
                getSupportFragmentManager().beginTransaction().replace(R.id.container, statusFive).commit();
                break;
        }
    }
}