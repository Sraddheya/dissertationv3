package com.ecclesiav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class OrganisationAddActivity extends AppCompatActivity {

    private ArrayList<Organisation> allOrganisations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_add);

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Init all Organisation
        setOrganisations();

        //Display content
        Intent intent = getIntent();
        int index = intent.getIntExtra("INDEX", 0);

        TextView nameTextView = findViewById(R.id.orgTitle);
        TextView descriptionTextView = findViewById(R.id.orgDescription);

        nameTextView.setText(allOrganisations.get(index).getName());
        descriptionTextView.setText(allOrganisations.get(index).getDescription());

        //Add button
        Button joinBtn = findViewById(R.id.joinBtn);
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrganisationAddActivity.this, OrganisationActivity.class);
                intent.putExtra("Organisation", allOrganisations.get(index));
                startActivity(intent);
            }
        });

        //Cancel button
        Button cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrganisationAddActivity.this, OrganisationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setOrganisations() {
        String default_org_descriptions = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer facilisis eros turpis, ut eleifend arcu pharetra quis. Integer dapibus pulvinar finibus. Nullam euismod tellus ac lectus laoreet, sed scelerisque mauris egestas. Vestibulum nec ex et lorem pulvinar fringilla. Nam eleifend, ligula non accumsan elementum, dui est elementum lacus, nec hendrerit odio nisi vel eros. Curabitur eget ipsum dignissim, faucibus libero at, congue massa. Nam pulvinar, tellus quis finibus venenatis, eros eros viverra dolor, ac consectetur erat lorem nec ante. Donec dapibus felis magna, eget consequat massa dignissim eget. Aenean quis nunc consectetur, rutrum mi in, maximus enim. Vivamus et magna non mi ultricies sollicitudin sit amet ut lorem.";

        allOrganisations.add(new Organisation("0", "Computing Society", default_org_descriptions));
    }
}