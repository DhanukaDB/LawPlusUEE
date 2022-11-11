package com.ads.lawplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {
    Button contactQA, contactSupportService, contactDev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        String name = getIntent().getStringExtra("NAME");
        String email = getIntent().getStringExtra("EMAIL");
        String feedback = getIntent().getStringExtra("FEEDBACK");

        TextView nameTextView = findViewById(R.id.txtFeedbackUser);
        TextView emailTextView = findViewById(R.id.txtFeedbackUserMail);
        TextView feedbackTextView = findViewById(R.id.txtUserFeedback);

        nameTextView.setText(name);
        emailTextView.setText(email);
        feedbackTextView.setText(feedback);

        //Add feedback button navigation
        contactQA = findViewById(R.id.btnContact);
        contactSupportService = findViewById(R.id.btnSupportService);
        contactDev = findViewById(R.id.btnDev);

        contactQA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Request send to the QA team. They will contact you as soon as you possible",Toast.LENGTH_LONG).show();
            }
        });
        contactSupportService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Request send to the Support Service unit. They will contact you as soon as you possible",Toast.LENGTH_LONG).show();
            }
        });
        contactDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Request send to the DEV team They will contact you as soon as you possible",Toast.LENGTH_LONG).show();
            }
        });
    }
}