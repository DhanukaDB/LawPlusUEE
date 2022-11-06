package com.ads.lawplus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AddFeedback extends AppCompatActivity {

    private String name;
    private String email;
    private String feedback;

    public AddFeedback() {
    }

    public AddFeedback(String name, String email, String feedback) {
        this.name = name;
        this.email = email;
        this.feedback = feedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);
    }
}