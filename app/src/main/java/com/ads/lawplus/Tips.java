package com.ads.lawplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Tips extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);


        textView= findViewById(R.id.txt_quotes);
        String dStory = getIntent().getStringExtra( "story");

        textView.setText(dStory);

    }
}