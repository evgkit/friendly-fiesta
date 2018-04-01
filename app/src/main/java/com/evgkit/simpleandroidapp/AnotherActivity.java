package com.evgkit.simpleandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity {

    private TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        Intent intent = this.getIntent();
        messageText = findViewById(R.id.messageText);
        messageText.setText(intent.getStringExtra(getString(R.string.key_message)));
    }
}
