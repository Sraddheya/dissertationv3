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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupOrganisations();

        //CONTENT------------------------------------------------
        //Intent intent = getIntent();
        int index = getIntent().getIntExtra("INDEX", 0);

        TextView nameTextView = findViewById(R.id.orgName);
        TextView descriptionTextView = findViewById(R.id.orgDescription);

        nameTextView.setText(allOrganisations.get(index).getName());
        descriptionTextView.setText(allOrganisations.get(index).getDescription());

        //BUTTON-------------------------------------------------
        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrganisationAddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button cancelBtn = findViewById(R.id.cancelBtn);
    }

    private void setupOrganisations() {
        String default_org_descriptions = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer facilisis eros turpis, ut eleifend arcu pharetra quis. Integer dapibus pulvinar finibus. Nullam euismod tellus ac lectus laoreet, sed scelerisque mauris egestas. Vestibulum nec ex et lorem pulvinar fringilla. Nam eleifend, ligula non accumsan elementum, dui est elementum lacus, nec hendrerit odio nisi vel eros. Curabitur eget ipsum dignissim, faucibus libero at, congue massa. Nam pulvinar, tellus quis finibus venenatis, eros eros viverra dolor, ac consectetur erat lorem nec ante. Donec dapibus felis magna, eget consequat massa dignissim eget. Aenean quis nunc consectetur, rutrum mi in, maximus enim. Vivamus et magna non mi ultricies sollicitudin sit amet ut lorem.";

        allOrganisations.add(new Organisation("0", "org0", default_org_descriptions));
        allOrganisations.add(new Organisation("1", "org1", default_org_descriptions));
        allOrganisations.add(new Organisation("2", "org2", default_org_descriptions));
        allOrganisations.add(new Organisation("3", "org3", default_org_descriptions));
    }
}