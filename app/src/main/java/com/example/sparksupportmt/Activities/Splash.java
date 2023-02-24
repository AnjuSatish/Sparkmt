package com.example.sparksupportmt.Activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sparksupportmt.R;

public class Splash extends AppCompatActivity {
    ImageView iv_logo_splash;
    TextView tv_logo_splash,tv_logo_splash2;
    long animTime=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_splash);
        iv_logo_splash=findViewById(R.id.iv_logo_splash);
        tv_logo_splash=findViewById(R.id.tv_logo_splash);
        tv_logo_splash2=findViewById(R.id.tv_logo_splash2);
        ObjectAnimator animatorY=ObjectAnimator.ofFloat(iv_logo_splash,"y",400f);
        ObjectAnimator animatorname=ObjectAnimator.ofFloat(tv_logo_splash,"x",200f);
        animatorY.setDuration(animTime);
        animatorname.setDuration(animTime);
        AnimatorSet animatorset=new AnimatorSet();
        animatorset.playTogether(animatorY,animatorname);
        animatorset.start();

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        },4000);
    }
}