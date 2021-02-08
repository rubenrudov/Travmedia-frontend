package com.travmedia.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.travmedia.handlers.BackgroundHandling;
import com.travmedia.R;
import java.util.Objects;

public class WelcomeActivity extends AppCompatActivity {
    Button toLogin, toRegister;
    // Random background from the backgrounds array of BackgroundHandling's class
    BackgroundHandling backgroundHandling = new BackgroundHandling();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide(); // Hides the app bar in that screen
        ImageView background = findViewById(R.id.background);
        String backgroundPic = backgroundHandling.getRandomPicture();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        // Glide.with(this).load(backgroundPic).override(width + 100, height + 100).centerCrop().into(background);
        toRegister = findViewById(R.id.toRegister);
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class));
            }
        });
        toLogin = findViewById(R.id.toLogin);
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        });
    }
}
