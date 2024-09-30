package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quiz.R;

public class ScoreActivity extends AppCompatActivity {

    private TextView scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreView = findViewById(R.id.scoreView);

        // Get the score passed from MainActivity
        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        // Display the score
        scoreView.setText("Your Score: " + score + "/" + totalQuestions);
    }
}
