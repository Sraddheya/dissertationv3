package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        Button joinBtn = findViewById(R.id.joinBtn);
        joinBtn.setOnClickListener(new View.OnClickListener() {
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
        startCastTxt.setText("Voting starts at: " + election.getStartTime());
        TextView endCastTxt = findViewById(R.id.endCastTxt);
        endCastTxt.setText("Voting ends at: " + election.getEndTime());
        TextView questionTxt = findViewById(R.id.questionTxt);
        questionTxt.setText(election.getQuestion());
        TextView descriptionTxt = findViewById(R.id.descriptionTxt);
        descriptionTxt.setText(election.getDescription());
        TextView optionsTxt = findViewById(R.id.optionDescriptionTxt);
        optionsTxt.setText(election.getOptionsDescriptions());
    }

    private void setElections() {
        ArrayList<String> options0 = new ArrayList<>();
        options0.add("None");
        options0.add("Pepperoni");
        options0.add("Mushroom");
        options0.add("Sausage");

        allElections.add(new Election("0", "Favourite Pizza Toppings", "0", "2023-03-14 01:02", "2023-03-14 01:04", "Joined",
                "What is your favourite pizza topping?",
                "In this election, we want to find out what your favourite pizza topping is.",
                options0,
                "\u25CF None: No toppings, only standard tomato base and cheese on top.\n" +
                        "\u25CF Pepperoni: An American variety of spicy salami made from cured pork and beef seasoned with paprika or other chili pepper.\n" +
                        "\u25CF Mushroom: A fleshy, spore-bearing fruiting body of a fungus, typically produced above ground, on soil, or on its food source\n" +
                        "\u25CF Sausage: A meat product usually made from ground meat—often pork, beef, or poultry—along with salt, spices and other flavourings.\n",
                0, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));

        ArrayList<String> options1 = new ArrayList<>();
        options1.add("Water");
        options1.add("Coke");
        options1.add("Sprite");
        options1.add("Fanta");

        allElections.add(new Election("1", "Favourite drink", "0", "2023-03-14 01:02", "2023-03-14 01:04", "Joined",
                "What is your favourite drink?",
                "In this election, we want to find out what your favourite drink is.",
                options1,
                "\u25CF Water: Unflavoured still water.\n" +
                        "\u25CF Coke: A brand of fizzy drink.\n" +
                        "\u25CF Sprite: A brand of fizzy drink.\n" +
                        "\u25CF Fanta: A brand of fizzy drink.",
                1, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
    }
}