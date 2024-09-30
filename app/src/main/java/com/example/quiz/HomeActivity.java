package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.R;

public class HomeActivity extends AppCompatActivity {

    private static final int HOME_DISPLAY_LENGTH = 3000; // Duration for the home screen (3 seconds)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Link to the home layout

        // Handler to delay the transition to MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Move to MainActivity after the delay
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close HomeActivity so user can't go back to it
            }
        }, HOME_DISPLAY_LENGTH);
    }
}
