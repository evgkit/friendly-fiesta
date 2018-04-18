package com.evgkit.simpleandroidapp.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.evgkit.simpleandroidapp.R;

public class MainActivity extends AppCompatActivity {

    private EditText inputMessage;
    private Button layoutTestBtn;
    private Button catsBtn;
    private Button showActivityBtn;
    private Button sendEmailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMessage = findViewById(R.id.inputMessage);

        layoutTestBtn = findViewById(R.id.buttonLayout);
        layoutTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LayoutTestActivity.class);
                startActivity(intent);
            }
        });

        catsBtn = findViewById(R.id.buttonCats);
        catsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CatsActivity.class);
                startActivity(intent);
            }
        });

        showActivityBtn = findViewById(R.id.buttonActivity);
        showActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
                intent.putExtra(getString(R.string.key_message), getMessage());
                startActivity(intent);
            }
        });

        sendEmailBtn = findViewById(R.id.buttonEmail);
        sendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getMessage());
                intent.putExtra(Intent.EXTRA_SUBJECT, getMessage());
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"bring_it@list.ru"});
                startActivity(intent);
            }
        });
    }

    @NonNull
    private String getMessage() {
        String message = inputMessage.getText().toString();
        if (message.isEmpty()) {
            message = getString(R.string.hello_text);
        }
        return message;
    }
}
