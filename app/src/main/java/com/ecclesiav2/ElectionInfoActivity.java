package com.ecclesiav2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;

public class ElectionInfoActivity extends AppCompatActivity {
    StatusOne statusOne = new StatusOne();
    StatusTwo statusTwo = new StatusTwo();
    StatusThree statusThree = new StatusThree();
    StatusFourTrue statusFourTrue = new StatusFourTrue();
    StatusFourFalse statusFourFalse = new StatusFourFalse();
    StatusFiveTrue statusFiveTrue = new StatusFiveTrue();
    StatusFiveFalse statusFiveFalse = new StatusFiveFalse();
    ResultsPizzaFragment resultsPizzaFragment = new ResultsPizzaFragment();
    ResultsDrinkFragment resultsDrinkFragment = new ResultsDrinkFragment();
    private Election election;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_info);

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Content
        election = getIntent().getExtras().getParcelable("elec");
        setContent();
        setTags();

        //More info button
        ImageButton infoBtn = findViewById(R.id.infoBtn);
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfoDialog();
            }
        });

        //Drop down box
        setDropDown();

        //Begin voting button
        Button startVoteBtn = findViewById(R.id.startVoteBtn);
        Bundle bundle = new Bundle();
        bundle.putParcelable("election", election);
        switch (election.getStatus()){
            case "Joined":
                statusOne.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.containerStatus, statusOne).commit();

                //Show disabled button
                startVoteBtn.setBackgroundColor(Color.GRAY);
                startVoteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ElectionInfoActivity.this, "Voting has not begun yet", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case "Voting started":
                statusTwo.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.containerStatus, statusTwo).commit();

                //Show enabled button
                startVoteBtn.setVisibility(View.VISIBLE);
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
                statusThree.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.containerStatus, statusThree).commit();
                startVoteBtn.setVisibility(View.GONE);
                break;
            case "Vote recorded true":
                statusFourTrue.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.containerStatus, statusFourTrue).commit();
                startVoteBtn.setVisibility(View.GONE);
                break;
            case "Vote recorded false":
                statusFourFalse.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.containerStatus, statusFourFalse).commit();
                startVoteBtn.setVisibility(View.GONE);
                break;
            case "Results calculated true":
                statusFiveTrue.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.containerStatus, statusFiveTrue).commit();
                if (election.getElecId().equals("0")){
                    resultsPizzaFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerResults, resultsPizzaFragment).commit();
                }
                if (election.getElecId().equals("1")){
                    resultsDrinkFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerResults, resultsDrinkFragment).commit();
                }
                startVoteBtn.setVisibility(View.GONE);
                break;
            case "Results calculated false":
                statusFiveFalse.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.containerStatus, statusFiveFalse).commit();
                if (election.getElecId().equals("0")){
                    resultsPizzaFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerResults, resultsPizzaFragment).commit();
                }
                if (election.getElecId().equals("1")){
                    resultsDrinkFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerResults, resultsDrinkFragment).commit();
                }
                startVoteBtn.setVisibility(View.GONE);
                break;
        }
    }

    private void showInfoDialog() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_state_info, null);
        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCancelable(false);

        mView.findViewById(R.id.closeBtn).setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        alertDialog.show();
    }

    private void setDropDown() {
        RelativeLayout detailsLayout = findViewById(R.id.details_layout);
        Button showHideBtn = findViewById(R.id.button_down);
        CardView cardView = findViewById(R.id.card_view);

        TextView elecDescription = findViewById(R.id.card_description);
        elecDescription.setText(election.getDescription());

        TextView elecOptions = findViewById(R.id.card_options);
        elecOptions.setText(election.getOptionsDescriptions());

        showHideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (detailsLayout.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(detailsLayout, new AutoTransition());
                    detailsLayout.setVisibility(View.VISIBLE);
                    showHideBtn.setBackgroundResource(R.drawable.icon_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition(detailsLayout, new AutoTransition());
                    detailsLayout.setVisibility(View.GONE);
                    showHideBtn.setBackgroundResource(R.drawable.icon_arrow_down);
                }
            }
        });
    }

    private void setContent() {
        TextView titleTxt = findViewById(R.id.titleTxt);
        titleTxt.setText(election.getTitle());
        TextView startCastTxt = findViewById(R.id.startCastTxt);
        startCastTxt.setText("Voting starts at: " + election.getStartTime());
        TextView endCastTxt = findViewById(R.id.endCastTxt);
        endCastTxt.setText("Voting ends at: " + election.getEndTime());
    }

    private void setTags(){
        CardView waitingTag = findViewById(R.id.waitingTag);
        CardView votedTag = findViewById(R.id.votedTag);
        CardView activeTag = findViewById(R.id.activeTag);
        CardView closedTag = findViewById(R.id.closedTag);

        if (LocalDateTime.now().isBefore(LocalDateTime.parse(election.getStartTime().replace(" ", "T")))){
            waitingTag.setVisibility(View.VISIBLE);
        } else if (LocalDateTime.now().isAfter(LocalDateTime.parse(election.getEndTime().replace(" ", "T")))){
            closedTag.setVisibility(View.VISIBLE);
        } else {
            activeTag.setVisibility(View.VISIBLE);
        }
        if (election.getSelectedIndex() > 0){
            votedTag.setVisibility(View.VISIBLE);
        }
    }
}