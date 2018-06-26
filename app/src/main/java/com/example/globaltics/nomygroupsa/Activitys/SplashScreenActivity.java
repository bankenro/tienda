package com.example.globaltics.nomygroupsa.Activitys;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.globaltics.nomygroupsa.R;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //getActionBar().hide();
        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(LoginActivity.class)
                .withSplashTimeOut(7000)
                .withBackgroundResource(android.R.color.white)
                .withHeaderText("\t" + "La mas alta gama de tecnologia en computadoras" + "\n" + " al alcance de tus necesidades")
                .withFooterText("Copyright 2017 - Naomy Group Corporation")
                .withBeforeLogoText("Naomy Group Corporation")
                .withLogo(R.drawable.logo)
                .withAfterLogoText("¿Que servicios necesita?");


        //metodo de la animacion
        myCustomTextViewAnimation(config.getFooterTextView());

        //enviando la fuente
        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "Pacifico.ttf");
        config.getAfterLogoTextView().setTypeface(pacificoFont);

        config.getHeaderTextView().setTextColor(Color.GREEN);
        config.getHeaderTextView().setTextSize(20f);
        config.getHeaderTextView().setGravity(Gravity.CENTER);

        config.getFooterTextView().setTextColor(Color.GREEN);

        //creando la vista
        View easySplashScreenView = config.create();

        setContentView(easySplashScreenView);
    }

    private void myCustomTextViewAnimation(TextView tv) {
        Animation animation = new TranslateAnimation(0, 0, 480, 0);
        animation.setDuration(3200);
        tv.startAnimation(animation);
    }
}
