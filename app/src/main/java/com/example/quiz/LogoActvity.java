package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.R;

public class LogoActvity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 3000; // Duration for the logo screen (3 seconds)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo); // Link to your logo layout

        // Handler to introduce delay and then start HomeActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start HomeActivity after the splash screen
                Intent intent = new Intent(LogoActvity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Close LogoActivity so the user can't return to it
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

