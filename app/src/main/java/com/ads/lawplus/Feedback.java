package com.ads.lawplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Feedback extends AppCompatActivity {

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
    }
}