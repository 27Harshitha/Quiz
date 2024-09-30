package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView questionView, timerView, scoreView;
    private RadioGroup choicesGroup;
    private RadioButton option1, option2, option3, option4;
    private Button submitButton, previousButton;

    private String[] questions = {
            "Who wrote the novel “War and Peace”?",
            "Which river is the longest in the world??",
            "Which one of the following countries is not in Africa?",
            "What is the currency of Japan?",
            "Which is the richest country in the world?",
            "What animal is a symbol of peace and neutrality?",
            "Which chemical element makes up most of the atmosphere of Mars?",
            "Which organ in the human body is responsible for secreting insulin?",
            "In which season do we need more fat?",
            "Who invented the telegraph?"
    };

    private String[][] options = {
            {"Anton Chekhov", "Fyodor Dostoevsky","Leo Tolstoy","Ivan Turgenev"},
            {"Amazon", "Mississippi", "Nile", "Yangtze"},
            {"Morocco", "Yemen", "Sudan", "Algeria"},
            {"Yuan", "Dollar", "Yen", "Euro"},
            {"Qatar","Russia","The USA","The UAE"},
            {"Polar bear","White tiger","White lion","White crane"},
            {"Carbon dioxide","Oxygen","Nitrogen","Hydrogen"},
            {"Liver","Kidney","Pancreas","Stomach"},
            {"Autumn","Spring","Winter","Summer"},
            {"Alexander Graham Bell","Guglielmo Marconi","Thomas Edison","Samuel Morse"}
    };

    private int[] answers = {2, 2, 1, 2, 0,3,0,3,2,3}; // correct answer indices
    private int[] selectedAnswers; // store user-selected answers

    private int score = 0;
    private int currentQuestionIndex = 0;
    private boolean isQuizFinished = false;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionView = findViewById(R.id.question);
        timerView = findViewById(R.id.timer);
        scoreView = findViewById(R.id.score);
        choicesGroup = findViewById(R.id.choicesGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        submitButton = findViewById(R.id.submitButton);
        previousButton = findViewById(R.id.previousButton);

        selectedAnswers = new int[questions.length]; // Initialize the selected answers array
        for (int i = 0; i < selectedAnswers.length; i++) {
            selectedAnswers[i] = -1; // -1 indicates no answer selected
        }

        startQuiz();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isQuizFinished) {
                    if (choicesGroup.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(MainActivity.this, "Please select an answer!", Toast.LENGTH_SHORT).show();
                    } else {
                        checkAnswer();
                        nextQuestion();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Quiz is already finished!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex > 0) {
                    previousQuestion();
                } else {
                    Toast.makeText(MainActivity.this, "This is the first question!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startQuiz() {
        startTimer(60000); // 60 seconds timer
        showQuestion();
    }

    private void startTimer(long duration) {
        countDownTimer = new CountDownTimer(duration, 1000) {
            public void onTick(long millisUntilFinished) {
                timerView.setText("Time: " + millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                isQuizFinished = true;
                submitButton.setEnabled(false);
                moveToScorePage();
            }
        }.start();
    }

    private void showQuestion() {
        questionView.setText(questions[currentQuestionIndex]);
        option1.setText(options[currentQuestionIndex][0]);
        option2.setText(options[currentQuestionIndex][1]);
        option3.setText(options[currentQuestionIndex][2]);
        option4.setText(options[currentQuestionIndex][3]);

        // Restore previously selected answer if available
        if (selectedAnswers[currentQuestionIndex] != -1) {
            ((RadioButton) choicesGroup.getChildAt(selectedAnswers[currentQuestionIndex])).setChecked(true);
        } else {
            choicesGroup.clearCheck(); // Clear the selection if no previous answer
        }
    }

    private void checkAnswer() {
        int selectedOptionId = choicesGroup.getCheckedRadioButtonId();
        RadioButton selectedOption = findViewById(selectedOptionId);
        int selectedAnswerIndex = choicesGroup.indexOfChild(selectedOption);

        // Store the selected answer for the current question
        selectedAnswers[currentQuestionIndex] = selectedAnswerIndex;

        if (selectedAnswerIndex == answers[currentQuestionIndex]) {
            score++;
        }
    }

    private void nextQuestion() {
        if (currentQuestionIndex < questions.length - 1) {
            currentQuestionIndex++;
            showQuestion();
        } else {
            isQuizFinished = true;
            countDownTimer.cancel();
            moveToScorePage();
        }
    }

    private void previousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            showQuestion();
        }
    }

    private void moveToScorePage() {
        // Move to ScoreActivity
        Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("totalQuestions", questions.length);
        startActivity(intent);
        finish(); // Finish the MainActivity so the user can't go back to it
    }
}
