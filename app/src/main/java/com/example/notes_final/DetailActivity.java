package com.example.notes_final;

// DetailActivity.java
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        // Retrieve the selected title from the intent
        Intent intent = getIntent();
        String selectedTitle = intent.getStringExtra("selected_title");
        String currentTimeString = intent.getStringExtra("current_time");
        // Display the selected title in the TextView
        ArrayList<String> content=intent.getStringArrayListExtra("custom_texts");
        TextView titleTextView = findViewById(R.id.detailTitleTextView);
        //titleTextView.setText(selectedTitle+" "+currentTimeString);
        StringBuilder stringBuilder = new StringBuilder();

// Iterate through the ArrayList and append each item with its index to the StringBuilder
        for (int i = 0; i < content.size(); i++) {
            String item = content.get(i);
            stringBuilder.append(i+1).append(". ").append(item).append("\n");
        }

// Set the text in the TextView
        titleTextView.setText(stringBuilder.toString());
    }
}
