package com.ads.lawplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LawTipsHome extends AppCompatActivity {

    TextView tvSplash,tvSubSplash;
    Button btn_getStart;
    Animation atg, btgOne, btgTwo;
    ImageView ivSplash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_tips_home);

        tvSplash = findViewById(R.id.tvSplash);
        tvSubSplash = findViewById(R.id.tvSubSplash);
        btn_getStart = findViewById(R.id.btn_getstart);
        ivSplash = findViewById(R.id.ivSplash);



        //load Animation
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        btgOne = AnimationUtils.loadAnimation(this, R.anim.btgone);
        btgTwo = AnimationUtils.loadAnimation(this, R.anim.btgtwo);

        //passing animation
        ivSplash.startAnimation((atg));
        tvSplash.startAnimation(btgOne);
        tvSubSplash.startAnimation(btgOne);
        btn_getStart.startAnimation(btgTwo);
        //passing event
        btn_getStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(   LawTipsHome.this, LawTips.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });



    }
}