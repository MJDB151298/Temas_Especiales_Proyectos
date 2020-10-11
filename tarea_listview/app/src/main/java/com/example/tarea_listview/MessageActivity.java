package com.example.tarea_listview;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra("LISTVIEW_ITEM");
        TextView textView = findViewById(R.id.message_textview);
        textView.setText(textView.getText().toString() + " " + message);
    }
}