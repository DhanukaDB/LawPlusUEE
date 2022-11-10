package com.ads.lawplus;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class FeedbackSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_success);

        //rating dialog box
        RateDialog rateDialog = new RateDialog(FeedbackSuccess.this);
        rateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        rateDialog.setCancelable(false);
        rateDialog.show();
    }
}