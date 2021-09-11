package com.example.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class resultActivity extends AppCompatActivity {

    private TextView results;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        results = findViewById(R.id.results);
        returnButton = findViewById(R.id.returnButton);

        String resultTxt = getIntent().getExtras().getString("results");
        results.setText(resultTxt);
        returnButton.setOnClickListener((view) -> {
            finish();
        });
    }
}