package com.ads.lawplus;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class RateDialog extends Dialog{
    private float useRate = 0;
    public RateDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_success);

        final AppCompatButton rateNowButton = findViewById(R.id.rateNow);
        final AppCompatButton laterButton = findViewById(R.id.mayBeLater);
        final RatingBar ratingBar = findViewById(R.id.rate);
        final ImageView ratingImage = findViewById(R.id.ratingImage);

        rateNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                CharSequence text = "Thank you!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        laterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating <= 1){
                    ratingImage.setImageResource(R.drawable.angry);
                }else if(rating <= 2){
                    ratingImage.setImageResource(R.drawable.rate2);
                }else if(rating <= 3){
                    ratingImage.setImageResource(R.drawable.rate3);
                }else if(rating <= 4){
                    ratingImage.setImageResource(R.drawable.rate4);
                }else if(rating <= 5){
                    ratingImage.setImageResource(R.drawable.happy);
                }

                //animate emoji image
                animateImage(ratingImage);

                //selected rating by user
                useRate = rating;
            }
        });
    }

    private void animateImage(ImageView ratingImage){
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratingImage.startAnimation(scaleAnimation);
    }
}
