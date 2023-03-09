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

        Button voteAgainBtn = findViewById(R.id.reCastBtn);
        voteAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent emailIntent = new Intent(Intent.ACTION_SEND);
//                emailIntent.setData(Uri.parse("mailto:"));
//                emailIntent.setType("text/plain");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, "s1908227@ed.ac.uk");
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "TestApp email");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","s1908227@ed.ac.uk", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "AppTest email");
                intent.putExtra(Intent.EXTRA_TEXT, "Hello test");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });
    }
}