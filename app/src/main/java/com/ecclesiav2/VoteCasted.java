package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VoteCasted extends AppCompatActivity {
    Button endCastBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_casted);

        int selectedIndex = getIntent().getIntExtra("cast", 0);

        endCastBtn = findViewById(R.id.endCastBtn);
        endCastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoteCasted.this, ElectionActivity.class);
                intent.putExtra("cast", selectedIndex);
                startActivity(intent);
            }
        });
    }
}