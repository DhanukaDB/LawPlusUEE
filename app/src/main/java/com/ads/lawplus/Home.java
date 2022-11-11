package com.ads.lawplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    Button btnbmn,btnque, btnTT, btnHBM, btnBooking;
    TextView feedbackTxt, exploretext,accountText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnbmn = findViewById(R.id.btnbmn);

        btnbmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Home.this, Questions.class));
            }
        });

        btnque = findViewById(R.id.btnque);

        btnque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Home.this, ManageQuestion.class));
            }
        });

        feedbackTxt = findViewById(R.id.feedbackText);

        feedbackTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                startActivity(new Intent(Home.this, FeedbackList.class));
            }
        });

        exploretext = findViewById(R.id.exploretext);
        exploretext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, ArticleList.class));
            }
        });

        accountText = findViewById(R.id.accountText);
        accountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, UserProfile.class));
            }
        });
//
//        btnBooking = findViewById(R.id.btnDoneBookings);
//        btnBooking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(home.this, RetrieveCardActivity.class));
//            }
//        });
    }
}