package com.example.objetiva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class SearchPage extends AppCompatActivity {
    private EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        initViews();

    }

    private void initViews() {
        searchBox = findViewById(R.id.searchBox);
    }
}