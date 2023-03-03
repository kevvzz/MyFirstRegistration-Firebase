package com.example.myfirstregistration_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity {
    TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        show = findViewById(R.id.textView);

        show.setText(MainActivity.name);

    }
}