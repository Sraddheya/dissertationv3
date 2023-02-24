package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
    private Election election;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_info);

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //CONTENT
        election = getIntent().getExtras().getParcelable("elec");
        setContent();

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

                //Show enabled button
                startVoteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ElectionInfoActivity.this, VoteSelection.class);
                        intent.putExtra("elec", election);
                        startActivity(intent);
                    }
                });
                break;
            case "Vote casted":
                Bundle bundle2 = new Bundle();
                bundle2.putInt("selectedIndex", election.getSelectedIndex());
                statusThree.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, statusThree).commit();
                startVoteBtn.setVisibility(View.GONE);
                break;
            case "Vote recorded true":
                getSupportFragmentManager().beginTransaction().replace(R.id.container, statusFourTrue).commit();
                startVoteBtn.setVisibility(View.GONE);
                break;
            case "Vote recorded false":
                getSupportFragmentManager().beginTransaction().replace(R.id.container, statusFourFalse).commit();
                startVoteBtn.setVisibility(View.GONE);
                break;
            case "Results calculated":
                getSupportFragmentManager().beginTransaction().replace(R.id.container, statusFive).commit();
                startVoteBtn.setVisibility(View.GONE);
                break;
        }
    }

    private void setContent() {
        TextView titleTxt = findViewById(R.id.titleTxt);
        titleTxt.setText(election.getTitle());
        TextView startCastTxt = findViewById(R.id.startCastTxt);
        startCastTxt.setText("Voting starts at: " + election.getStartCast());
        TextView endCastTxt = findViewById(R.id.endCastTxt);
        endCastTxt.setText("Voting ends at: " + election.getEndCast());

        Log.d("status", election.getStatus());
        Log.d("selected", Integer.toString(election.getSelectedIndex()));
    }
}