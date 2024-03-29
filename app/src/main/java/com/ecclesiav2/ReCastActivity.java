package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReCastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_cast);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Election election = getIntent().getExtras().getParcelable("election");

        Button voteAgainBtn = findViewById(R.id.reCastBtn);
        voteAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReCastActivity.this, VoteSelection.class);
                election.setNeedReCast(0);
                intent.putExtra("elec", election);
                startActivity(intent);
            }
        });
    }
}