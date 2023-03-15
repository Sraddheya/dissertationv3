package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VoteCasted extends AppCompatActivity {
    Button endCastBtn;
    TextView toHelpTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_casted);

        //Back button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Election election = getIntent().getExtras().getParcelable("election");

        TextView optionConfirmation = findViewById(R.id.txt2);
        optionConfirmation.setText("You voted for " + election.getOptions().get(election.getSelectedIndex()));

        toHelpTxt = findViewById(R.id.txt5);
        toHelpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoteCasted.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        endCastBtn = findViewById(R.id.endCastBtn);
        endCastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoteCasted.this, ElectionActivity.class);
                LocalDateTime now = LocalDateTime.now();

                election.setCastTime(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                election.setRecordedTime(now.plusHours(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                intent.putExtra("Election", election);
                intent.putExtra("reCasted", true);
                startActivity(intent);
            }
        });
    }
}