package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class VoteCasted extends AppCompatActivity {
    Button endCastBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_casted);

        int selectedIndex = getIntent().getIntExtra("selectedIndex", 0);
        String elecID = getIntent().getStringExtra("elecID");

        endCastBtn = findViewById(R.id.endCastBtn);
        endCastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoteCasted.this, ElectionActivity.class);
                intent.putExtra("selectedIndex", selectedIndex);
                intent.putExtra("elecID", elecID);
                startActivity(intent);
            }
        });
    }
}