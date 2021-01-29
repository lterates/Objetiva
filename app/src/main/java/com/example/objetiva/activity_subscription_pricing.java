package com.example.objetiva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_subscription_pricing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_pricing);

        Button backButton = findViewById(R.id.back_button);
    }

    public void onClick(View view){
        finish();
    }
}