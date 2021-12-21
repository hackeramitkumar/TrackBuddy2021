package com.example.trackbuddy.Splash_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.trackbuddy.R;
import com.example.trackbuddy.walkthrough.WalkThrough_reg;

public class Splash_Screen extends AppCompatActivity {
    ImageView backgroung;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        backgroung = findViewById(R.id.image);
        lottieAnimationView = findViewById(R.id.animation);

        backgroung.animate().translationY(-1600).setDuration(1000).setStartDelay(3500);
        lottieAnimationView.animate().translationY(1600).setDuration(1000).setStartDelay(3500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent =  new Intent(Splash_Screen.this, WalkThrough_reg.class);
                Splash_Screen.this.startActivity(mainIntent);
                Splash_Screen.this.finish();
            }
        },3000);

        getSupportActionBar().hide();

    }
}