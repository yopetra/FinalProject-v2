package com.example.android.jokesactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        jokeTextView = findViewById(R.id.tv_joke);

        String joke = getIntent().getStringExtra("jokeItem");
        jokeTextView.setText(joke);
    }
}