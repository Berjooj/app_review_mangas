package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.myapplication.R;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        ImageButton voltarActivity = findViewById(R.id.voltarId);
        voltarActivity.setOnClickListener(view -> finish());
    }
}