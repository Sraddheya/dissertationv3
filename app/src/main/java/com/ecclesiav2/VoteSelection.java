package com.ecclesiav2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class VoteSelection extends AppCompatActivity {

    private Election election;
    private RadioGroup rgp;
    private int selectedIndex;
    private Button castVoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_selection);

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //CONTENT
        election = getIntent().getExtras().getParcelable("elec");
        TextView question = findViewById(R.id.question);
        question.setText(election.getQuestion());

        //Radio buttons
        rgp= findViewById(R.id.radioGroup);
        setVoteOptions();

        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                i = i % election.getOptions().size();
                selectedIndex = i-1;
            }
        });

        //Cast vote button
        castVoteBtn = findViewById(R.id.castVoteBtn);
        castVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showVoteDialog();
            }
        });
    }

    private void showVoteDialog() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_confirm_vote, null);
        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCancelable(false);

        mView.findViewById(R.id.cancelBtn).setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        mView.findViewById(R.id.castBtn).setOnClickListener(v -> {
            Intent intent = new Intent(VoteSelection.this, VoteCasted.class);
            election.setSelectedIndex(selectedIndex);
            intent.putExtra("election", election);
            startActivity(intent);
        });

        alertDialog.show();
    }

    private void setVoteOptions() {
        RadioGroup.LayoutParams rprms;

        for (String option : election.getOptions()){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setTextSize(18);
            radioButton.setTextColor(Color.parseColor("#494848"));
            radioButton.setId(View.generateViewId());
            rprms= new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            rgp.addView(radioButton, rprms);
        }
    }
}